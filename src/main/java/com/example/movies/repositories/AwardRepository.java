package com.example.movies.repositories;

import com.example.movies.entities.Award;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface AwardRepository extends CrudRepository<Award, Integer> {
    @Transactional(readOnly = true)
    List<Award> findAllByAwardTypeIgnoreCase(String awardType);
}
