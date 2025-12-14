package com.example.movies.dtos;

public record RoleDto(
        ActorDto actor,
        MovieDto movie,
        String character
) {
}
