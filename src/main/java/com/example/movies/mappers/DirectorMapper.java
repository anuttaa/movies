package com.example.movies.mappers;

import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
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
