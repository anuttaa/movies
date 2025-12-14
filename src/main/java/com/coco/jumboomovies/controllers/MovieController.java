package com.coco.jumboomovies.controllers;

import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.logging.LogExecutionTime;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jumboo_movies/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int id) {
       return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<ActorDto>> getMovieActors(@PathVariable int movieId) {
        return ResponseEntity.ok(movieService.getAllActorsFromMovie(movieId));
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("/add")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        MovieDto createdMovie = movieService.addMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovieById(@PathVariable int id, @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.updateMovie(id, movieDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable int id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}