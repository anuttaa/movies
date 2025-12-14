package com.coco.jumboomovies.entities;

import com.coco.jumboomovies.model.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "review_id"))
public class Review extends BaseEntity {
    private double rating;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
/*create table if not exists review
(
    review_id serial primary key ,
    user_id int not null ,
    movie_id int not null ,
    rating decimal ,
    review_text text ,
    created_at date
);*/
