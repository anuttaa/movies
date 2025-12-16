package com.example.movies.controllers;

import com.example.movies.logging.LogExecutionTime;
import com.example.movies.services.ActorService;
import com.example.movies.services.DirectorService;
import com.example.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movies/search")
@RequiredArgsConstructor
public class SearchController {

    private final ActorService actorService;
    private final MovieService movieService;
    private final DirectorService directorService;

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<?> search(@RequestParam() String name) {
        Map<String, Object> searchResults = new HashMap<>();
        searchResults.put("actors", actorService.findActorsByName(name));
        searchResults.put("movies", movieService.getAllMoviesByName(name));
        searchResults.put("directors", directorService.getDirectorsByName(name));
        return ResponseEntity.ok(searchResults);
    }
}
