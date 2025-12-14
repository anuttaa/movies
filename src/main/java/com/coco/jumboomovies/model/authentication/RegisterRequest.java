package com.coco.jumboomovies.model.authentication;

public record RegisterRequest(
        String name,
        String surname,
        String login,
        String password
) {
}
