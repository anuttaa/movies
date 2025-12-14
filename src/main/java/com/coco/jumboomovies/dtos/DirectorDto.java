package com.coco.jumboomovies.dtos;

import java.util.List;

public record DirectorDto(
        String name,
        List<AwardDto> awards,
        List<MovieDto> movies
){
}
