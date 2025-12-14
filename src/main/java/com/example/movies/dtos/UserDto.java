package com.example.movies.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
        int id,
        String name,
        String surname,
        List<MovieDto> favourites
) {
}
