package com.example.movies.mappers;

import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @org.mapstruct.Mappings({
            @Mapping(target = "director", ignore = true),
            @Mapping(target = "awards", ignore = true)
    })
    MovieDto toDto(Movie movie);

    Movie toMovie(MovieDto movieDto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    DirectorDto directorToDirectorDto(Director director);
  
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    Director directorDtoToDirector(DirectorDto directorDto);
}
