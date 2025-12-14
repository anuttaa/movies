package com.coco.jumboomovies.services;

import com.coco.jumboomovies.entities.ConfirmationToken;
import com.coco.jumboomovies.entities.User;
import com.coco.jumboomovies.model.Role;
import com.coco.jumboomovies.model.authentication.LoginRequest;
import com.coco.jumboomovies.model.authentication.LoginResponse;
import com.coco.jumboomovies.model.authentication.RegisterRequest;
import com.coco.jumboomovies.repositories.UserRepository;
import com.coco.jumboomovies.security.jwt.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;

import static com.coco.jumboomovies.model.AppConstants.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    public void register(RegisterRequest request, Locale userLocale) {
        if (userRepository.findByLogin(request.login()).isPresent()) {
            throw new RuntimeException("User with this username already exists");
        }
        var user = new User();
        user.setName(request.name());
        user.setEmailVerified(false);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        user.setSurname(request.surname());
        user.setLogin(request.login());
        userRepository.save(user);

        ConfirmationToken token = confirmationTokenService.generateConfirmationToken(user);
        emailService.sendEmail(user.getLogin(), token.getToken(), userLocale);
    }

    public String login(LoginRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.password())
        );
        String login = request.login();
        var accessToken = jwtTokenUtil.generateAccessToken(login, authentication.getAuthorities());
        var refreshToken = jwtTokenUtil.generateRefreshToken(login, authentication.getAuthorities());

        addTokenToCookie(ACCESS_TOKEN, accessToken, response);
        addTokenToCookie(REFRESH_TOKEN, refreshToken, response);

        return accessToken;
    }

    public LoginResponse refreshAccessToken(String refreshToken, HttpServletResponse response) {

        if (jwtTokenUtil.validateToken(refreshToken)) {
            String username = jwtTokenUtil.getUsernameFromToken(refreshToken);

            Collection<? extends GrantedAuthority> authorities = jwtTokenUtil.getAuthorities(refreshToken);
            String newAccessToken = jwtTokenUtil.generateAccessToken(username, authorities);
            String newRefreshToken = jwtTokenUtil.generateRefreshToken(username, authorities);
            addTokenToCookie(ACCESS_TOKEN, newAccessToken, response);
            addTokenToCookie(REFRESH_TOKEN, newRefreshToken, response);
            return new LoginResponse(newAccessToken, newRefreshToken);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    // Helper method to add a JWT token to a cookie
    private void addTokenToCookie(String cookieName, String token, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, token);
        cookie.setPath("/jumboo_movies");
        cookie.setHttpOnly(true);  // Prevent JavaScript access
        cookie.setMaxAge(-1);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
