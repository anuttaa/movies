package com.example.movies.dtos;

import lombok.Builder;

@Builder
public record ReviewDto(
        Double rating,
        String reviewText,
        UserDto user,
        MovieDto movie
) {
}
