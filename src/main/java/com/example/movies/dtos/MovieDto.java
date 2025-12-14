package com.example.movies.dtos;

import com.example.movies.model.Genre;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record MovieDto(
        int id,
        String name,
        String poster,
        String description,
        Double rate,
        DirectorDto director,
        String duration,
        List<AwardDto> awards,
        int year,
        Set<Genre> genres
) {
}
