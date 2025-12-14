package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiverChatId(int id);
}
