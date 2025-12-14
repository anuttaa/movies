package com.example.movies.mappers;

import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.RoleDto;
import com.example.movies.entities.Actor;
import com.example.movies.entities.Director;
import com.example.movies.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toRole(RoleDto roleDto);

    @Mapping(target = "discography", ignore = true)
    ActorDto actorToActorDto(Actor actor);

    @Mapping(target = "awards", ignore = true)
    @Mapping(target = "movies", ignore = true)
    DirectorDto directorToDirectorDto(Director director);
}
