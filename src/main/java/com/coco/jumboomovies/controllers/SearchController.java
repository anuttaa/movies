package com.coco.jumboomovies.controllers;

import com.coco.jumboomovies.logging.LogExecutionTime;
import com.coco.jumboomovies.services.ActorService;
import com.coco.jumboomovies.services.DirectorService;
import com.coco.jumboomovies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movies/search")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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
