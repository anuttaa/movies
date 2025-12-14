package com.example.movies.repositories;

import com.example.movies.entities.Award;
import com.example.movies.entities.Director;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Integer> {
    Optional<Director> findDirectorByName(String name);

    @Transactional(readOnly = true)
    @Query("SELECT d.awards FROM Director d WHERE d.id = :directorId")
    List<Award> findAwardsByDirectorId(@Param("directorId") Integer directorId);

    @Transactional(readOnly = true)
    List<Director> findByNameContainingIgnoreCase(String name);
}
