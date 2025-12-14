package com.coco.jumboomovies.dtos;

public record RoleDto(
        ActorDto actor,
        MovieDto movie,
        String character
) {
}
