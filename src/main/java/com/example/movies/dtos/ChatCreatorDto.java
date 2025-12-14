package com.example.movies.dtos;

import java.util.List;

public record ChatCreatorDto(
        String chatName,
        List<String> users
) {
}
