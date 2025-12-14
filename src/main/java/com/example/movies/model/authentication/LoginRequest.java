package com.example.movies.model.authentication;

public record LoginRequest(
        String login, //username
        String password
) {
}
