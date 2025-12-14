package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findByChatName(String chatName);
}
