package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @org.mapstruct.Mappings({
            @Mapping(target = "movies", ignore = true),
            @Mapping(target = "awards", ignore = true)
    })
    DirectorDto toDto(Director director);

    Director toDirector(DirectorDto directorDTO);

    @Mapping(target = "director", ignore = true)
    MovieDto movieToMovieDto(Movie movie);
}
