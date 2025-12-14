package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Transactional(readOnly = true)
    List<Movie> findMoviesByDirectorId(int id);

    @Transactional(readOnly = true)
    List<Movie> findMoviesByNameContainingIgnoreCase(String  name);

    Movie findMovieByNameAndYear(String name, int year);
}
