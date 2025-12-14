package com.coco.jumboomovies.controllersMockTests;

import com.coco.jumboomovies.controllers.MovieController;
import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.exceptions.GlobalExceptionHandler;
import com.coco.jumboomovies.exceptions.ResourceNotFoundException;
import com.coco.jumboomovies.services.MovieService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
public class MovieControllerTests {

    private MockMvc mockMvc;
    private Movie movie;
    private Movie movieWithCast;
    private MovieDto movieDto;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private JacksonTester<MovieDto> jsonMovieDto;
    private JacksonTester<List<ActorDto>> jsonActorDtoList;

    @BeforeEach
    public void setUp() {
        movie = new Movie();
        movie.setId(1);
        movie.setName("American Pie");
        movie.setYear(1990);
        movieDto = MovieDto.builder()
                .id(1)
                .name("American Pie")
                .year(1990)
                .build();
        movieWithCast = new Movie();
        movieWithCast.setId(2);
        movieWithCast.setName("Cast");
        movieWithCast.setYear(2000);

        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveMovieByIdWhenExists() throws Exception {
        //given
        given(movieService.getMovieById(1)).willReturn(movieDto);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/movies/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonMovieDto.write(movieDto).getJson()
        );
    }

    @Test
    public void shouldReturnNotFoundWhenMovieByIdNotExists() throws Exception {
        // given
        int nonExistentId = 999;
        given(movieService.getMovieById(nonExistentId))
                .willThrow(new ResourceNotFoundException("Movie not found with ID: " + nonExistentId));

        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/movies/" + nonExistentId)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("Movie not found with ID: " + nonExistentId);
    }

    @Test
    public void shouldReturnMovieCastWhenMovieExists() throws Exception {
        //given
        ActorDto actorDto1 = ActorDto.builder()
                .id(1)
                .name("Actor1")
                .build();
        ActorDto actorDto2 = ActorDto.builder()
                .id(2)
                .name("Actor2")
                .build();
        List<ActorDto> actorDtos = List.of(actorDto1, actorDto2);
        given(movieService.getAllActorsFromMovie(2)).willReturn(actorDtos);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/jumboo_movies/movies/2/actors")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonActorDtoList.write(actorDtos).getJson()
        );
    }
}
