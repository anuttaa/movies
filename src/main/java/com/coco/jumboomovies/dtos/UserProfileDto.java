package com.coco.jumboomovies.dtos;

import java.util.List;

public record UserProfileDto (
        int id,
        String name,
        String surname,
        String login,
        String avatar,
        List<MovieDto> favourites,
        List<ChatDto> chats
){
}
