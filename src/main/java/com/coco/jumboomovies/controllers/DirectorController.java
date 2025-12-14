package com.coco.jumboomovies.controllers;

import com.coco.jumboomovies.logging.LogExecutionTime;
import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.services.DirectorService;
import org.springframework.http.HttpStatus;
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
}

