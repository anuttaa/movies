package com.example.movies.controllers;

import com.example.movies.logging.LogExecutionTime;
import com.example.movies.dtos.ReviewDto;
import com.example.movies.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @LogExecutionTime
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping("/movie/{movieId}")
    public List<ReviewDto> getMovieReviews(@PathVariable int movieId) {
        return reviewService.getMovieReviews(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewDto> getUserReviews(@PathVariable int userId) {
        return reviewService.getUserReviews(userId);
    }

    @GetMapping("/user/{userId}/movie/{movieId}")
    public List<ReviewDto> getUserReviewsForMovie(
            @PathVariable int userId,
            @PathVariable int movieId
    ) {
        return reviewService.getUserReviewsForMovie(movieId, userId);
    }
}

