package com.example.movies.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
        int id,
        String name,
        String surname,
        String login,
        String password,
        List<MovieDto> favourites
) {
}
