package com.coco.jumboomovies.controllersMockTests;

import com.coco.jumboomovies.controllers.ActorController;
import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.entities.Actor;
import com.coco.jumboomovies.exceptions.GlobalExceptionHandler;
import com.coco.jumboomovies.exceptions.ResourceNotFoundException;
import com.coco.jumboomovies.services.ActorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class ActorControllerTests {

    private ActorDto actorDto;
    private Actor actor;
    private MockMvc mockMvc;

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController actorController;

    private JacksonTester<ActorDto> jsonActorDto;
    private JacksonTester<List<Map<String, Object>>> jsonMovieResponse;

    @BeforeEach
    void setUp() {
        actor = new Actor();
        actor.setName("Pavel Volkov");
        actor.setBiography("Homel boy");
        actor.setId(1);
        actorDto = ActorDto.builder()
                .id(1)
                .name("Pavel Volkov")
                .biography("Homel boy")
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(actorController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveActorByIdWhenExists() throws Exception {
        //given
        given(actorService.getActorById(1)).willReturn(actorDto);
        //when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/actors/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonActorDto.write(actorDto).getJson()
        );
    }

    @Test
    public void shouldReturnNullWhenActorWithIdNotExists() throws Exception {
        int nonExistentActorId = 999;

        // Given
        given(actorService.getActorById(nonExistentActorId))
                .willThrow(new ResourceNotFoundException("Actor not found with ID: " + nonExistentActorId));

        // When
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/actors/" + nonExistentActorId)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("Actor not found with ID: " + nonExistentActorId);
    }


    @Test
    public void shouldReturnDiscographyWhenActorExists() throws Exception {
        //given
        MovieDto movieDto1 = MovieDto.builder()
                .name("Movie1")
                .build();
        MovieDto movieDto2 = MovieDto.builder()
                .name("Movie2")
                .build();
        Map<MovieDto, String> movieDtos = Map.of(
                movieDto1, "character1",
                movieDto2, "character2"
                );
        given(actorService.findMoviesByActorId(1)).willReturn(movieDtos);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/actors/1/movies")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        // Expected response serialization using JacksonTester
        List<Map<String, Object>> expectedResponse = movieDtos.entrySet()
                .stream()
                .map(entry -> Map.of(
                        "movie", entry.getKey(),
                        "character", entry.getValue()
                ))
                .collect(Collectors.toList());

        // Use JacksonTester to serialize the expected response
        String expectedJson = jsonMovieResponse.write(expectedResponse).getJson();

        // Assert that the actual response JSON equals the expected JSON
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
