package com.example.movies.controllers;

import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.DirectorDto;
import com.example.movies.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies/directors")
@CrossOrigin(origins = "http://localhost:3000")
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<DirectorDto>> getDirectors(
            @RequestParam(required = false) String name
    ) {
        if (name != null) {
            return ResponseEntity.ok(directorService.getDirectorsByName(name));
        }
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable int id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDto> updateDirectorById(@PathVariable int id, @RequestBody DirectorDto directorDto) {
        return ResponseEntity.ok(directorService.updateDirector(id, directorDto));
    }
}

