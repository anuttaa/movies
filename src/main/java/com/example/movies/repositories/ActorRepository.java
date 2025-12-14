package com.example.movies.repositories;

import com.example.movies.entities.Actor;
import com.example.movies.entities.Award;
import com.example.movies.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT a.discography FROM Actor a WHERE a.id = :actorId")
    Set<Role> findDiscographyByActorId(Integer actorId);

    @Transactional(readOnly = true)
    List<Actor> findByNameContainingIgnoreCase(String name);

    @Query("SELECT a.awards FROM Actor a WHERE a.id = :id")
    List<Award> findAwardsByActorId(@Param("id") int id);

    @Query("SELECT a FROM Actor a JOIN a.awards aw WHERE aw.awardType = :awardType")
    List<Actor> findAllByAwardType(@Param("awardType") String awardType);

    Actor findByNameAndBirthday(String name, LocalDate birthday);

    Actor findByName(String name);
}
