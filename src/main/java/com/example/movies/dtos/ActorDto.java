package com.example.movies.dtos;

import lombok.Builder;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record ActorDto(
        int  id,
        String name,
        LocalDate birthday,
        String biography,
        Set<RoleDto> discography,
        List<AwardDto> awards
) {
}
