package com.example.movies.security.jwt;

import com.example.movies.entities.User;
import com.example.movies.model.AppConstants;
import com.example.movies.model.BaseEntity;
import com.example.movies.repositories.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final UserRepository userRepository;
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecretKey;

    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return generateToken(username, authorities, AppConstants.ACCESS_TOKEN_EXPIRATION_TIME);
    }

    // Generate refresh token
    public String generateRefreshToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return generateToken(username, authorities, AppConstants.REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private String generateToken(String username, Collection<? extends GrantedAuthority> authorities, int time) {
        String authoritiesString = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Optional<User> user = userRepository.findByLogin(username);
        int id = user.map(BaseEntity::getId).orElse(0);
        return Jwts.builder()
                .subject(username)
                .claim("id", id)
                .claim("authorities", authoritiesString)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + time))
                .signWith(getSignKey())
                .compact();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = extractAllClaimsFromToken(token);
        String authoritiesString = claims.get("authorities", String.class);

        return authoritiesString != null ? Arrays.stream(authoritiesString.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    // Validate token and provide specific checks
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token);
            return !isTokenExpired(token);  // Token is valid
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT token is expired");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token format");
        } catch (UnsupportedJwtException ex) {
            System.out.println("JWT token is unsupported");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;  // Token is invalid for any of the above reasons
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return getClaim(token, Claims::getExpiration).before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
}
