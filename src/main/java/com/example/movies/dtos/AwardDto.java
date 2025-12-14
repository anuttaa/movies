package com.example.movies.dtos;

import java.time.LocalDate;

public record AwardDto(
        String name,
        String awardType,
        String awardDescription,
        LocalDate year
) {
}
