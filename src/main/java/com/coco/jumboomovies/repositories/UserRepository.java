package com.coco.jumboomovies.repositories;

import com.coco.jumboomovies.entities.Chat;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT u FROM User u WHERE lower(u.name) like %:name% OR lower(u.surname) LIKE %:name%")
    List<User> findByNameOrSurnameContaining(@Param("name") String name);

    @Transactional(readOnly = true)
    @Query("SELECT u.favourites FROM User u WHERE u.id = :userId")
    List<Movie> findFavouriteMoviesByUserId(@Param("userId") int userId);

    @Transactional(readOnly = true)
    @Query("select u.chats from User u where u.login = :sender_login")
    List<Chat> getChatsByUserLogin(@Param("sender_login") String userLogin);

    Optional<User> findByLogin(String login);
}
