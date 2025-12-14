package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    List<Review> findByMovieId(int movieId);

    List<Review> findByMovieIdAndUserId(int movieId, int userId);

    @Transactional(readOnly = true)
    List<Review> findAllByUserId(int userId);
}
