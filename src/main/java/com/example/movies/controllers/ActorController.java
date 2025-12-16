package com.example.movies.controllers;

import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ActorDto> getActorById(@PathVariable int id) {
       return ResponseEntity.ok(actorService.getActorById(id));
    }

    @GetMapping
    public ResponseEntity<List<ActorDto>> getActors(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(actorService.findActorsByName(name));
        }
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Map<String, Object>>> getActorsMovies(@PathVariable int id) {
        Map<MovieDto, String> moviesWithRoles = actorService.findMoviesByActorId(id);
        List<Map<String, Object>> response = moviesWithRoles.entrySet()
                .stream()
                .map(entry -> Map.of(
                        "movie", entry.getKey(),
                        "character", entry.getValue()
                ))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorDto actorDto) {
        ActorDto savedActor = actorService.saveActor(actorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> updateMovieById(@PathVariable int id, @RequestBody ActorDto actorDto) {
        return new ResponseEntity<>(actorService.updateActor(id, actorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {
        actorService.deleteActorById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/biography")
    public ResponseEntity<ActorDto> updateActorBiography(@PathVariable int id, @RequestBody ActorDto actorDto) {
        return ResponseEntity.ok(actorService.updateActorBiography(id, actorDto));
    }

    // Update awards
    @PutMapping("/{id}/awards")
    public ResponseEntity<ActorDto> updateActorAwards(@PathVariable int id, @RequestBody ActorDto actorDto) {
        return ResponseEntity.ok(actorService.updateActorAwards(id, actorDto));
    }

    // Update discography
    @PutMapping("/{id}/discography")
    public ResponseEntity<ActorDto> updateActorDiscography(@PathVariable int id, @RequestBody ActorDto actorDto) {
        return ResponseEntity.ok(actorService.updateActorDiscography(id, actorDto));
    }
}
