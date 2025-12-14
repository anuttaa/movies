package com.example.movies.repositories;

import com.example.movies.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByActorIdAndMovieId(int actorId, int movieId);

    @Transactional(readOnly = true)
    List<Role> findRolesByActorId(Integer actorId);

    @Transactional(readOnly = true)
    List<Role> findRolesByMovieId(Integer movieId);
}
