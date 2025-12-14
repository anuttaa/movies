package com.example.movies.controllers;

import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.AwardDto;
import com.example.movies.services.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/awards")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AwardController {

    private final AwardService awardService;


    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<AwardDto>> getAwards() {
        return ResponseEntity.ok(awardService.getAllAwards());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<AwardDto>> getAwardsByType(@PathVariable String type) {
        return ResponseEntity.ok(awardService.getAllAwardsByType(type));
    }
}
