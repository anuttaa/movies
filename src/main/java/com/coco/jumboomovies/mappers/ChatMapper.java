package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.ChatDto;
import com.coco.jumboomovies.entities.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatDto toDto(Chat chat);

    Chat toChat(ChatDto chatDto);
}
