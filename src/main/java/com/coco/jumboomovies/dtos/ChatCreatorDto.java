package com.coco.jumboomovies.dtos;

import java.util.List;

public record ChatCreatorDto(
        String chatName,
        List<String> users
) {
}
