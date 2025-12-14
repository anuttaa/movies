package com.coco.jumboomovies.service;

import com.coco.jumboomovies.dtos.ReviewDto;
import com.coco.jumboomovies.dtos.UserDto;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.entities.Review;
import com.coco.jumboomovies.entities.User;
import com.coco.jumboomovies.mappers.ReviewMapper;
import com.coco.jumboomovies.repositories.ReviewRepository;
import com.coco.jumboomovies.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceUnitTests {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    private Movie movie;

    private User user;

    private UserDto userDto;

    private List<Review> reviews;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1);
        movie.setName("American Pie");

        user = new User();
        user.setId(1);
        user.setName("Pavel");
        user.setSurname("Volkov");
        userDto = new UserDto(1,user.getName(), user.getSurname(), null);

        reviews = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Review review = new Review();
            review.setId(i);
            review.setReviewText("Review " + i);
            review.setRating(i);
            review.setMovie(new Movie());
            review.setUser(user);
            reviews.add(review);
        }
    }

    @Test
    @DisplayName("")
    void getAllReviews() {
        //Arrange
        when(reviewRepository.findAll()).thenReturn(reviews);
        when(reviewMapper.toDto(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            //System.out.println(review.getId() + " with text: " + review.getReviewText() + " Has been mapped");
            return new ReviewDto(review.getRating(), review.getReviewText(), null, null);
        });

        List<ReviewDto> allReviews = reviewService.getAllReviews();

        assertEquals(1000, allReviews.size(), "The number of ReviewDto objects should match the number of Review entities");
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            ReviewDto reviewDto = allReviews.get(i);

            assertEquals(review.getRating(), reviewDto.rating(), "Rating should match for Review ID " + review.getId());
            assertEquals(review.getReviewText(), reviewDto.reviewText(), "Review text should match for Review ID " + review.getId());
        }

        verify(reviewRepository, times(1)).findAll();
        verify(reviewMapper, times(1000)).toDto(any(Review.class));
    }

    @Test
    @DisplayName("Get Reviews for Existing Movies")
    void getMovieReviews_WhenMovieExists() {
        //Arrange
        List<Review> reviews = new ArrayList<>(List.of(
                new Review(4, "Shit", LocalDate.now(), null, movie),
                new Review(9, "Dope", LocalDate.now(), null, movie),
                new Review(8, "Great", LocalDate.now(), null, movie)
        ));
        when(reviewRepository.findByMovieId(movie.getId())).thenReturn(reviews);
        when(reviewMapper.toDto(any(Review.class))).thenAnswer(invocation -> {
            Review review = invocation.getArgument(0);
            System.out.println("With text: " + review.getReviewText() + " Has been mapped");
            return new ReviewDto(review.getRating(), review.getReviewText(), null, null);
        });

        //Act
        List<ReviewDto> reviewsForMovie = reviewService.getMovieReviews(movie.getId());

        //Assert
        assertEquals(3, reviewsForMovie.size(), "The number of ReviewDto objects should match the number of Review entities");
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            ReviewDto reviewDto = reviewsForMovie.get(i);

            assertEquals(review.getRating(), reviewDto.rating(), "Rating should match for Review ID " + review.getId());
            assertEquals(review.getReviewText(), reviewDto.reviewText(), "Review text should match for Review ID " + review.getId());
        }
        verify(reviewRepository, times(1)).findByMovieId(movie.getId());
        verify(reviewMapper, times(3)).toDto(any(Review.class));
    }

    @Test
    @DisplayName("Gets Reviews for a Non-Existent Movie")
    void getMovieReviews_whenNonExistentMovie() {
        when(reviewRepository.findByMovieId(movie.getId())).thenReturn(Collections.emptyList());

        List<ReviewDto> reviewsForNonExistentMovie = reviewService.getMovieReviews(movie.getId());

        assertEquals(reviewsForNonExistentMovie.size(), 0);
        verify(reviewRepository, times(1)).findByMovieId(movie.getId());
        verify(reviewMapper, times(0)).toDto(any(Review.class));
    }

    @Test
    @DisplayName("Getting All Reviews Created By User With Id")
    void getAllUserReviews_withExistentUser() {
        //Arrange
        when(reviewRepository.findAllByUserId(user.getId())).thenReturn(reviews);
        lenient().when(reviewMapper.toDto(any(Review.class))).thenReturn(new ReviewDto(9.0, "Text", userDto, null));

        //Act
        List<ReviewDto> userReviews = reviewService.getUserReviews(user.getId());

        //Assert
        assertEquals(1000, userReviews.size());
        verify(reviewMapper, times(1000)).toDto(any(Review.class));
        verify(reviewRepository).findAllByUserId(user.getId());
    }

    @Test
    @DisplayName("Getting All Reviews Created By User: user doesn't exist")
    void getAllUserReviews_withNonExistentUser() {
        //Arrange
        int randomId = 999;
        when(reviewRepository.findAllByUserId(randomId)).thenReturn(Collections.emptyList());

        //Act
        List<ReviewDto> userReviews = reviewService.getUserReviews(randomId);

        //Assert
        assertEquals(0, userReviews.size());
        verify(reviewMapper, times(0)).toDto(any(Review.class));
        verify(reviewRepository).findAllByUserId(randomId);
    }

}
