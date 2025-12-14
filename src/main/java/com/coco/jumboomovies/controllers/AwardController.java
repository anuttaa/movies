package com.coco.jumboomovies.controllers;

import com.coco.jumboomovies.logging.LogExecutionTime;
import com.coco.jumboomovies.dtos.AwardDto;
import com.coco.jumboomovies.services.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jumboo_movies/awards")
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
