package com.example.movies.controllers;

import com.example.movies.dtos.ActorDto;
import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.MovieDto;
import com.example.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<MovieDto>> getAllMovies(@RequestParam(required = false) String name) {
        if (name != null) {
            return new ResponseEntity<>(movieService.getAllMoviesByName(name), HttpStatus.OK);
        }
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

    @PostMapping("/add")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        log.info("POST /movies/movies/add payload: name={}, year={}", movieDto.name(), movieDto.year());
        MovieDto createdMovie = movieService.addMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovieById(@PathVariable int id, @RequestBody MovieDto movieDto) {
        log.info("PUT /movies/movies/{} payload: name={}, year={}, rate={}", id, movieDto.name(), movieDto.year(), movieDto.rate());
        return new ResponseEntity<>(movieService.updateMovie(id, movieDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable int id) {
        log.info("DELETE /movies/movies/{}", id);
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
