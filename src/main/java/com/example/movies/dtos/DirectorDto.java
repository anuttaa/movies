package com.example.movies.dtos;

import java.util.List;

public record DirectorDto(
        int id,
        String name,
        List<AwardDto> awards,
        List<MovieDto> movies
){
}
