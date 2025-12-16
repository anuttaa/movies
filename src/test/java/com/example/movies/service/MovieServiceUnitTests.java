package com.example.movies.service;

import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.RoleDto;
import com.example.movies.entities.Actor;
import com.example.movies.entities.Award;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
import com.example.movies.entities.Role;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.mappers.RoleMapper;
import com.example.movies.repositories.DirectorRepository;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.RoleRepository;
import com.example.movies.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceUnitTests {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private RoleMapper roleMapper;

    private MovieDto movieDto;
    private Movie movie;
    private Director director;
    private Role role;
    private ActorDto actorDto;
    private Award award;
    private Actor actor;
    private RoleDto roleDto;

    @BeforeEach
    public void setUp() {
        director = new Director();
        DirectorDto directorDto = new DirectorDto(1, "Director", List.of(), List.of());
        movie = new Movie("Inception", 9.0, null, director, "2010-07-16", null, null, 2012,null);
        movieDto = new MovieDto(1,"Inception", null, "new film", 9.0, directorDto, "2010-07-16", List.of(), 2012,null);
        movie.setId(1);
        award = new Award();

        award.setId(1);
        award.setName("Best Movie");
        award.setAwardType("Film award");

        director.setId(1);
        director.setName("Director");
        director.setMovies(List.of(movie));
        actor = new Actor();
        actor.setId(1);
        actor.setName("Actor");
        actorDto = new ActorDto(1, "Actor", null, null, null, null);
        role = new Role();
        String character = "John Doe";
        role.setActor(actor);
        role.setMovie(movie);
        role.setCharacter(character);
        roleDto = new RoleDto(actorDto, movieDto, character);
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        when(movieMapper.toDto(movie)).thenReturn(movieDto);

        // Act
        List<MovieDto> result = movieService.getAllMovies();

        // Assert
        assertEquals(1, result.size());
        assertEquals(movieDto, result.getFirst());
        verify(movieRepository).findAll();
        verify(movieMapper).toDto(movie);
    }

    @Test
    void testGetAllMoviesByName() {
        when(movieRepository.findMoviesByNameContainingIgnoreCase("Inception")).thenReturn(List.of(movie));
        when(movieMapper.toDto(movie)).thenReturn(movieDto);
        List<MovieDto> result = movieService.getAllMoviesByName("Inception");
        assertEquals(1, result.size());
        assertEquals(movieDto, result.getFirst());
        verify(movieRepository).findMoviesByNameContainingIgnoreCase("Inception");
        verify(movieMapper).toDto(movie);
    }

    @Test
    @DisplayName("tests that we don't save director again if it exists")
    void testAddMovieWhenDirectorExists() {

        when(movieMapper.toMovie(movieDto)).thenReturn(movie);
        when(movieMapper.toDto(movie)).thenReturn(movieDto);
        when(directorRepository.findDirectorByName(director.getName())).thenReturn(Optional.of(director));
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        MovieDto savedMovie = movieService.addMovie(movieDto);

        assertEquals(movieDto, savedMovie);
        verify(directorRepository).findDirectorByName(director.getName());
        verify(movieRepository).save(movie);

        //Ensures that directorRepository.save was not called, since the director already exists.
        verify(directorRepository, never()).save(any(Director.class));
    }

    @Test
    void testAddMovieWhenDirectorDoesNotExist() {

        Director newDirector = new Director();
        DirectorDto newDirectorDto = new DirectorDto(0, "new Director", null, null);
        Movie movieWithNewDirector = new Movie("Interstellar", 8.6, null, newDirector, "2014-11-07", null, null, 169,null);
        MovieDto movieDtoWithNewDirector = MovieDto.builder().name("Interstellar").description("Sci-Fi").director(newDirectorDto).build();

        when(movieMapper.toMovie(movieDtoWithNewDirector)).thenReturn(movieWithNewDirector);
        when(movieMapper.toDto(movieWithNewDirector)).thenReturn(movieDtoWithNewDirector);
        when(directorRepository.findDirectorByName(newDirector.getName())).thenReturn(Optional.empty());
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movieWithNewDirector);

        MovieDto savedMovie = movieService.addMovie(movieDtoWithNewDirector);

        assertEquals(movieDtoWithNewDirector, savedMovie);
        verify(directorRepository).findDirectorByName(newDirector.getName());
        verify(directorRepository).save(newDirector); // New director should be saved
        verify(movieRepository).save(movieWithNewDirector);
        verify(movieMapper).toDto(movieWithNewDirector);
    }

    @Test
    void testGetAllActorsFromMovie() {
        when(roleRepository.findRolesByMovieId(movie.getId())).thenReturn(List.of(role));
        when(roleMapper.toDto(role)).thenReturn(roleDto);

        List<ActorDto> result = movieService.getAllActorsFromMovie(movie.getId());

        assertEquals(1, result.size());
        assertEquals(actorDto, result.getFirst());
        verify(roleRepository).findRolesByMovieId(movie.getId());
        verify(roleMapper).toDto(role);
    }

    @Test
    void testGetAllActorsFromMovieNoRolesFound() {
        when(roleRepository.findRolesByMovieId(movie.getId())).thenReturn(Collections.emptyList());

        List<ActorDto> result = movieService.getAllActorsFromMovie(movie.getId());

        assertTrue(result.isEmpty(), "Expected an empty list when no roles are found");
        verify(roleRepository).findRolesByMovieId(movie.getId());
        verifyNoInteractions(roleMapper);
    }

    @Test
    @DisplayName("Test addMovie throws NullPointerException when Director in MovieDTO is null")
    void testAddMovieNullPointerExceptionWhenDirectorIsNull() {

        MovieDto movieDtoWithNullDirector = MovieDto.builder()
                .id(1)
                .name("Inception")
                .rate(8.8)
                .year(2000)
                .description("Sci-fi")
                .build();

        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> movieService.addMovie(movieDtoWithNullDirector),
                "Expected addMovie to throw NullPointerException when director is null"
        );

        verifyNoInteractions(directorRepository); // Ensure no interaction with directorRepository
        verifyNoInteractions(movieRepository); // Ensure no interaction with movieRepository
        verifyNoInteractions(movieMapper); // Ensure no mapping attempts
    }
}

