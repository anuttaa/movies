package com.example.movies.repositories;

import com.example.movies.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findByToken(String token);
}
