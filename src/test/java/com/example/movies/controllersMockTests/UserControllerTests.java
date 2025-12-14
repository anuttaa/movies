package com.example.movies.controllersMockTests;


import com.example.movies.controllers.UserController;
import com.example.movies.dtos.UserDto;
import com.example.movies.entities.User;
import com.example.movies.exceptions.GlobalExceptionHandler;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.services.UserService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User user;
    private MockMvc mockMvc;
    private UserDto userDto;
    private JacksonTester<UserDto> userDtoJson;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Pavel");
        user.setSurname("Volkov");
        user.setId(1);
        userDto = UserDto.builder()
                .id(1)
                .name("Pavel")
                .surname("Volkov")
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void shouldRetrieveUserByIdWhenExists() throws Exception {
        //given
        given(userService.findUserById(1)).willReturn(userDto);
        //when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/movies/users/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                userDtoJson.write(userDto).getJson()
        );
    }

    @Test
    public void shouldReturnNullWhenActorWithIdNotExists() throws Exception {
        int nonExistentActorId = 999;

        // Given
        given(userService.findUserById(nonExistentActorId))
                .willThrow(new ResourceNotFoundException("User not found with ID: " + nonExistentActorId));

        // When
        MockHttpServletResponse response = mockMvc.perform(
                        get("/movies/users/" + nonExistentActorId)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("User not found with ID: " + nonExistentActorId);
    }

    @Test
    public void shouldSaveUser() throws Exception {
        //Arrange
        given(userService.createUser(any(UserDto.class))).willReturn(userDto);

        MockHttpServletResponse response = mockMvc.perform(
                post("/movies/users/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson.write(userDto).getJson())
        ).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("\"id\":1");
        assertThat(response.getContentAsString()).contains("\"name\":\"Pavel\"");
        assertThat(response.getContentAsString()).contains("\"surname\":\"Volkov\"");
    }
}
