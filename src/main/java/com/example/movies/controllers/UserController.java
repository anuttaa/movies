package com.example.movies.controllers;

import com.example.movies.dtos.UserProfileDto;
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
    @PreAuthorize("hasAuthority('CREATE')")
    @LogExecutionTime
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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
}
