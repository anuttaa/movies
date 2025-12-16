package com.example.movies.controllers;

import com.example.movies.dtos.UserProfileDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.UserDto;
import com.example.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(userService.findUserByName(name));
        }
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/add")
    @LogExecutionTime
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileDto(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserProfileById(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserProfileDto> updateUser(
            @ModelAttribute UserDto userDto,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
         return ResponseEntity.ok(userService.updateUser(userDto,avatar));
    }

    @GetMapping("/{id}/favourites")
    public ResponseEntity<List<MovieDto>> getFavourites(@PathVariable int id) {
        return ResponseEntity.ok(userService.getFavourites(id));
    }

    @PostMapping("/{id}/favourites/{movieId}")
    public ResponseEntity<List<MovieDto>> addFavourite(@PathVariable int id, @PathVariable int movieId) {
        return ResponseEntity.ok(userService.addFavourite(id, movieId));
    }

    @PostMapping("/favourites/{movieId}")
    public ResponseEntity<List<MovieDto>> addFavouriteForCurrent(@PathVariable int movieId, org.springframework.security.core.Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String login = authentication.getName();
        return ResponseEntity.ok(userService.addFavouriteForLogin(login, movieId));
    }

    @DeleteMapping("/{id}/favourites/{movieId}")
    public ResponseEntity<List<MovieDto>> removeFavourite(@PathVariable int id, @PathVariable int movieId) {
        return ResponseEntity.ok(userService.removeFavourite(id, movieId));
    }
}
