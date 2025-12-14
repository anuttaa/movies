package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.dtos.RoleDto;
import com.coco.jumboomovies.entities.Actor;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    @Mapping(target = "discography", ignore = true)
    ActorDto toDto(Actor actor);

    @Mapping(target = "actor", ignore = true)
    RoleDto roleToRoleDto(Role role);

    @Mapping(target = "director", ignore = true)
    MovieDto movieToMovieDto(Movie movie);
  
    Actor toActor(ActorDto actorDto);
}
