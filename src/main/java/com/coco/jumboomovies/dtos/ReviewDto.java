package com.coco.jumboomovies.dtos;

import lombok.Builder;

@Builder
public record ReviewDto(
        Double rating,
        String reviewText,
        UserDto user,
        MovieDto movie
) {
}
