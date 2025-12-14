package com.coco.jumboomovies.model.authentication;

public record LoginRequest(
        String login, //username
        String password
) {
}
