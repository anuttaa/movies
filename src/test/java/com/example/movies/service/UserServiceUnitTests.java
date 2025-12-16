package com.example.movies.service;

import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.UserDto;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
import com.example.movies.entities.User;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.mappers.UserMapper;
import com.example.movies.repositories.UserRepository;
import com.example.movies.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MovieMapper movieMapper;

    private User user1;
    private UserDto userDto1;

    private User user2;
    private UserDto userDto2;

    private Movie movie1;
    private MovieDto movie1Dto;
    private Movie movie2;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setName("Joe");
        user1.setSurname("Black");
        user1.setLogin("joeblack@gmail.com");
        user1.setPassword("password");

        user2 = new User();
        user2.setId(2);
        user2.setName("Joe");
        user2.setSurname("Biden");
        user2.setLogin("president@gmail.com");
        user2.setPassword("democratiya");

        movie1 = new Movie();
        movie1.setId(1);
        movie1.setName("Movie1");
        movie1.setDirector(new Director());
        movie1.setYear(1990);
        movie1Dto = MovieDto.builder().id(1).year(1990).name("Movie1").build();
        List<Movie> movies1 = new ArrayList<>();
        movies1.add(movie1);

        List<MovieDto> moviesDTO1 = new ArrayList<>();
        moviesDTO1.add(movie1Dto);

        movie2 = new Movie();
        movie2.setId(2);
        movie2.setName("Movie2");
        movie2.setDirector(new Director());
        movie2.setYear(1991);
        List<Movie> movies2 = new ArrayList<>();
        movies2.add(movie1);

        List<MovieDto> moviesDTO2 = new ArrayList<>();
        moviesDTO2.add(movieMapper.toDto(movie2));

        user1.setFavourites(movies1);
        user2.setFavourites(movies2);

        userDto1 = new UserDto(1, user1.getName(), user1.getSurname(), user1.getLogin(), user1.getPassword(), moviesDTO1);
        userDto2 = new UserDto(2, user2.getName(), user2.getSurname(), user2.getLogin(), user2.getPassword(), moviesDTO2);

        userList = new ArrayList<>(List.of(user1, user2));
    }

    @Test
    @DisplayName("Find user by name")
    void testFindUserByName() {
        String name = "Joe";
        when(userRepository.findByNameOrSurnameContaining(name)).thenReturn(userList);
        when(userMapper.toDto(user1)).thenReturn(userDto1);
        when(userMapper.toDto(user2)).thenReturn(userDto2);

        List<UserDto> userDTOList = userService.findUserByName(name);

        assertEquals(2, userDTOList.size());
        verify(userRepository).findByNameOrSurnameContaining(name);
    }

    @Test
    @DisplayName("Testing findUsersByName if there is no such name")
    void testFindActorsByName_NoMatches() {
        String name = "Non existing person";
        when(userRepository.findByNameOrSurnameContaining(name)).thenReturn(Collections.emptyList());

        List<UserDto> result = userService.findUserByName(name);

        assertTrue(result.isEmpty(), "Expected result to be empty");
        verify(userRepository).findByNameOrSurnameContaining(name);
    }

    @Test
    @DisplayName("Create a new user")
    void testCreateUser() {
        when(userMapper.toUser(userDto1)).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);
        when(userMapper.toDto(user1)).thenReturn(userDto1);

        UserDto result = userService.createUser(userDto1);

        assertEquals(userDto1, result);
        verify(userMapper).toUser(userDto1);
        verify(userRepository).save(user1);
        verify(userMapper).toDto(user1);
    }

    @Test
    @DisplayName("Get user's favorite movies")
    void testGetUserFavoriteMovies() {
        int userId = 1;
        when(userRepository.findFavouriteMoviesByUserId(userId)).thenReturn(user1.getFavourites());
        when(movieMapper.toDto(movie1)).thenReturn(movie1Dto);

        List<MovieDto> result = userService.getUserFavoriteMovies(userId);

        assertEquals(1, result.size());
        verify(userRepository).findFavouriteMoviesByUserId(userId);
        verify(movieMapper).toDto(movie1);
    }
}
