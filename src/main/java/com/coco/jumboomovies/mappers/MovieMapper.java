package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.Movie;
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
