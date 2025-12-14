package com.example.movies.model.authentication;

public record RegisterRequest(
        String name,
        String surname,
        String login,
        String password
) {
}
