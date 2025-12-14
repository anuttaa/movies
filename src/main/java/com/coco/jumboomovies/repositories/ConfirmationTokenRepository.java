package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
