package com.coco.jumboomovies.model.authentication;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String message
) {
    public LoginResponse(String message) {
        this(null, null, message);
    }
    public LoginResponse(String message, String accessToken) {
        this(accessToken, null, message);
    }
}
