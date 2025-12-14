package com.example.movies.mappers;

import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.UserDto;
import com.example.movies.entities.Director;
import com.example.movies.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserDto userDto);

    @Mappings({
        @Mapping(target = "movies", ignore = true),
        @Mapping(target = "awards", ignore = true),
    })
    DirectorDto directorToDirectorDto(Director director);
}
