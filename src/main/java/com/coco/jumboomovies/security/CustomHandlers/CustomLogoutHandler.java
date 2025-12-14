package com.coco.jumboomovies.security.CustomHandlers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        // Clear the access token cookie
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setPath("/movies");
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(0); // Expire immediately
        response.addCookie(accessTokenCookie);

        // Clear the refresh token cookie (if used)
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setPath("/movies");
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(0); // Expire immediately
        response.addCookie(refreshTokenCookie);

        SecurityContextHolder.clearContext();
    }
}
