package com.example.movies.service;

import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.entities.*;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
import com.example.movies.mappers.DirectorMapper;
import com.example.movies.repositories.DirectorRepository;
import com.example.movies.services.DirectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceUnitTests {

    @InjectMocks
    private DirectorService directorService;

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private DirectorMapper directorMapper;

    private Director director1;
    private Director director2;
    private DirectorDto directorDto1;
    private DirectorDto directorDto2;
    private Movie movie1;
    private Movie movie2;
    private MovieDto movieDto1;
    private MovieDto movieDto2;

    @BeforeEach
    void setUp() {
        director1 = new Director();
        director1.setId(1);
        director1.setName("Nikifor Telpuk");

        director2 = new Director();
        director2.setId(2);
        director2.setName("Pavel Volkov");

        movie1 = new Movie();
        movie1.setId(1);
        movie1.setName("Pulp Fiction");
        movie1.setYear(1994);

        movie2 = new Movie();
        movie2.setId(2);
        movie2.setName("The Prestige");
        movie2.setYear(2000);

        movieDto1 = MovieDto.builder().id(1).name(movie1.getName()).build();
        movieDto2 = MovieDto.builder().id(2).name(movie2.getName()).build();
        List<Movie> movieList1 = new ArrayList<>();
        movieList1.add(movie1);
        List<Movie> movieList2 = new ArrayList<>();
        movieList2.add(movie2);

        List<MovieDto> movieDtoList1 = new ArrayList<>();
        movieDtoList1.add(movieDto1);
        List<MovieDto> movieDtoList2 = new ArrayList<>();
        movieDtoList2.add(movieDto2);

        director1.setMovies(movieList1);
        director2.setMovies(movieList2);

        directorDto1 = new DirectorDto(director1.getName(), null, movieDtoList1);
        directorDto2 = new DirectorDto(director2.getName(), null, movieDtoList2);
    }

    @Test
    @DisplayName("Find director by name containing substring")
    void testFindActorsByName() {
        String name = "Pavel";
        when(directorRepository.findByNameContainingIgnoreCase(name)).thenReturn(List.of(director2));
        when(directorMapper.toDto(director2)).thenReturn(directorDto2);

        List<DirectorDto> result = directorService.getDirectorsByName(name);

        assertEquals(1, result.size());
        verify(directorRepository).findByNameContainingIgnoreCase(name);
    }

    @Test
    @DisplayName("Testing findDirectorByName if there is no such name")
    void testFindDirectorsByName_NoMatches() {
        String name = "Non existing director";
        when(directorRepository.findByNameContainingIgnoreCase(name)).thenReturn(Collections.emptyList());

        List<DirectorDto> result = directorService.getDirectorsByName(name);

        assertTrue(result.isEmpty(), "Expected result to be empty");
        verify(directorRepository).findByNameContainingIgnoreCase(name);
    }

    @Test
    @DisplayName("Efficient mapping of multiple directors to DTOs")
    void testFindActorsByName_Performance() {
        String name = "Nikifor";

        List<Director> directors = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Director director = new Director();
            director.setId(i);
            director.setName("Nikifor " + i);
            directors.add(director);
        }
        when(directorRepository.findByNameContainingIgnoreCase(name)).thenReturn(directors);
        lenient().when(directorMapper.toDto(any(Director.class)))
                .thenReturn(new DirectorDto("Nikifor", null, null));

        List<DirectorDto> result = directorService.getDirectorsByName(name);

        assertEquals(1000, result.size());
        verify(directorMapper, times(1000)).toDto(any(Director.class));
        verify(directorRepository).findByNameContainingIgnoreCase(name);
    }
}
