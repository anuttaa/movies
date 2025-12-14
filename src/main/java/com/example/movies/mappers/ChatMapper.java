package com.example.movies.mappers;

import com.example.movies.dtos.ChatDto;
import com.example.movies.entities.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatDto toDto(Chat chat);

    Chat toChat(ChatDto chatDto);
}
