package com.coco.jumboomovies.service;

import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.dtos.RoleDto;
import com.coco.jumboomovies.entities.Actor;
import com.coco.jumboomovies.entities.Award;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.entities.Role;
import com.coco.jumboomovies.mappers.ActorMapper;
import com.coco.jumboomovies.mappers.MovieMapper;
import com.coco.jumboomovies.repositories.ActorRepository;
import com.coco.jumboomovies.repositories.MovieRepository;
import com.coco.jumboomovies.repositories.RoleRepository;
import com.coco.jumboomovies.services.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorServiceUnitTests {

    @InjectMocks
    private ActorService actorService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private ActorMapper actorMapper;

    private Actor actor;
    private Actor actor2;
    private ActorDto actorDto;
    private ActorDto actor2Dto;
    private Movie movie;

    @BeforeEach
    void setUp() {
        actor = new Actor();
        actor.setId(1);
        actor.setName("John Doe");
        actor.setBirthday(LocalDate.parse("2005-07-12"));
        actor.setBiography("Born in Luninets, great actor");

        actor2 = new Actor();
        actor2.setId(2);
        actor2.setName("John Potter");

        actorDto = new ActorDto(1, actor.getName(), actor.getBirthday(), actor.getBiography(), null, null);
        actor2Dto = new ActorDto(2, actor2.getName(), actor2.getBirthday(), actor2.getBiography(), null, null);

        Award award = new Award();

        award.setId(1);
        award.setName("Best Actor");
        award.setAwardType("Actor award");

        movie = new Movie();
        movie.setId(1);
        movie.setName("Movie");
        movie.setDirector(new Director());
        movie.setYear(1990);
    }

    @Test
    @DisplayName("Find actors by name containing substring")
    void testFindActorsByName() {
        String name = "John";
        when(actorRepository.findByNameContainingIgnoreCase(name)).thenReturn(List.of(actor, actor2));
        when(actorMapper.toDto(actor)).thenReturn(actorDto);
        when(actorMapper.toDto(actor2)).thenReturn(actor2Dto);

        List<ActorDto> result = actorService.findActorsByName(name);

        Assertions.assertEquals(2, result.size());
        verify(actorRepository).findByNameContainingIgnoreCase(name);
    }

    @Test
    @DisplayName("Testing findActorsByName if there is no such name")
    void testFindActorsByNameNoMatches() {
        String name = "Non existing person";
        when(actorRepository.findByNameContainingIgnoreCase(name))
                .thenReturn(Collections.emptyList());

        List<ActorDto> result = actorService.findActorsByName(name);

        assertTrue(result.isEmpty(), "Expected result to be empty");
        verify(actorRepository).findByNameContainingIgnoreCase(name);
    }

    @Test
    @DisplayName("Efficient mapping of multiple actors to DTOs")
    void testFindActorsByNamePerformance() {
        String name = "John";
        int id = 0;
        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Actor actor = new Actor();
            actor.setId(i);
            actor.setName("John " + i);
            actors.add(actor);
        }
        when(actorRepository.findByNameContainingIgnoreCase(name)).thenReturn(actors);
        lenient().when(actorMapper.toDto(any(Actor.class)))
                .thenReturn(new ActorDto(id++, "John", null, null, null, null));

        List<ActorDto> result = actorService.findActorsByName(name);

        Assertions.assertEquals(1000, result.size());
        verify(actorMapper, times(1000)).toDto(any(Actor.class));
        verify(actorRepository).findByNameContainingIgnoreCase(name);
    }


    @Test
    @DisplayName("Testing save actor without roles")
    void testSaveActor() {
        when(actorRepository.findByNameAndBirthday(actorDto.name(), actorDto.birthday())).thenReturn(null);
        when(actorMapper.toDto(actor)).thenReturn(actorDto);
        when(actorMapper.toActor(actorDto)).thenReturn(actor);
        when(actorRepository.save(actor)).thenReturn(actor);

        ActorDto savedActor = actorService.saveActor(actorDto);

        assertNotNull(savedActor);
        Assertions.assertEquals(actorDto.name(), savedActor.name());
        Assertions.assertEquals(actorDto.birthday(), savedActor.birthday());

        verify(actorRepository).findByNameAndBirthday(actorDto.name(), actorDto.birthday());
        verify(actorMapper).toActor(actorDto);
        verify(actorRepository).save(actor);
        verify(actorMapper).toDto(actor);
        verify(roleRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Test saving an actor with roles and movies")
    void testSaveActorWithRole() {
        MovieDto movieDto = MovieDto.builder().name("Example Movie").description("great movie").rate(9.0).year(2006).build();
        RoleDto roleDto = new RoleDto(actorDto, movieDto, "character");
        Role role = new Role(actor, movie, "character");
        actor.setDiscography(Set.of(role));
        ActorDto actorDto1 = new ActorDto(1,actor.getName(), actor.getBirthday(), actor.getBiography(), Set.of(roleDto), null);

        // Mock responses
        when(actorRepository.findByNameAndBirthday(actorDto1.name(), actorDto1.birthday())).thenReturn(null);
        when(actorMapper.toActor(actorDto1)).thenReturn(actor);
        when(actorMapper.toDto(actor)).thenReturn(actorDto1);
        when(actorRepository.save(actor)).thenReturn(actor);
        when(movieMapper.toMovie(movieDto)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);

        ActorDto savedActor = actorService.saveActor(actorDto1);

        assertNotNull(savedActor);
        Assertions.assertEquals(actorDto1.name(), savedActor.name());

        verify(actorRepository).findByNameAndBirthday(actorDto1.name(), actorDto1.birthday());
        verify(actorRepository).save(actor);
        verify(movieRepository).save(movie);
        verify(roleRepository, times(1)).save(any(Role.class));
    }


}
