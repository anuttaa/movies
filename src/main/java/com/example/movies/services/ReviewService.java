package com.example.movies.services;

import com.example.movies.dtos.ReviewDto;
import com.example.movies.entities.Movie;
import com.example.movies.entities.Review;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.mappers.ReviewMapper;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieRepository movieRepository;

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = (List<Review>) reviewRepository.findAll();
        return reviews.stream().map(reviewMapper::toDto).toList();
    }

    public Optional<ReviewDto> getReviewById(int id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto);
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toReview(reviewDto);
        review.setCreationDate(LocalDate.now());
        updateMovieRating(reviewDto.user().id(), reviewDto.movie().id(), reviewDto.rating());
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewDto> getMovieReviews(int id) {
        return reviewRepository.findByMovieId(id).stream().map(reviewMapper::toDto).toList();
    }

    public List<ReviewDto> getUserReviews(int id) {
        return reviewRepository.findAllByUserId(id)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public List<ReviewDto> getUserReviewsForMovie(int movieId, int userId) {
        return reviewRepository.findByMovieIdAndUserId(movieId, userId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public void updateMovieRating(int userId, int movieId, double rating) {
        List<Double> ratings = new java.util.ArrayList<>(
                reviewRepository.findByMovieIdAndUserId(movieId, userId)
                .stream()
                .map(Review::getRating).toList());
        ratings.add(rating);
        double averageRating = ratings.stream()
                .mapToDouble(rate -> rate)
                .average().orElse(0.0);

        double roundedRating = new BigDecimal(averageRating)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + movieId));
        movie.setRate(roundedRating);
        movieRepository.save(movie);
    }
}
