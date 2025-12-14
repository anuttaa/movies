package com.example.movies.mappers;

import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.RoleDto;
import com.example.movies.entities.Actor;
import com.example.movies.entities.Movie;
import com.example.movies.entities.Role;
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
