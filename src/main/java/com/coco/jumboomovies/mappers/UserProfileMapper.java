package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.UserProfileDto;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileDto toDto(User user);

    User toUser(UserProfileDto userProfileDtoDto);

    @Mappings({
            @Mapping(target = "movies", ignore = true),
            @Mapping(target = "awards", ignore = true),
    })
    DirectorDto directorToDirectorDto(Director director);
}
