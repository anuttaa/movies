package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.UserDto;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.User;
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
