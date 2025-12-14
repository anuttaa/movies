package com.example.movies.mappers;

import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.UserProfileDto;
import com.example.movies.entities.Director;
import com.example.movies.entities.User;
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
