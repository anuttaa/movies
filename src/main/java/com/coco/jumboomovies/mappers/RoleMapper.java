package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.RoleDto;
import com.coco.jumboomovies.entities.Actor;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.Role;
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
