package com.coco.jumboomovies.entities;

import com.coco.jumboomovies.model.Genre;
import com.coco.jumboomovies.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "name", column = @Column(name = "title"))
@AttributeOverride(name = "id", column = @Column(name = "movie_id"))
public class Movie extends NamedEntity {

    private String description;
    private Double rate;
    private String poster;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "director", referencedColumnName = "director_full_name")
    private Director director;

    @Column(name = "duration", nullable = false)
    private String duration;

    @ManyToMany
    @JoinTable(
            name = "movies_awards",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private List<Award> awards;

    // One movie can have many roles (actors playing characters)
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Role> roles;

    @Column(name = "release_year")
    private int year;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    @jakarta.persistence.Convert(converter = com.coco.jumboomovies.model.GenreConverter.class)
    private Set<Genre> genres;
}
/*create table if not exists movies
(
    movie_id serial primary key ,
    name varchar(128) not null ,
    description text ,
    rate decimal ,
    director varchar(128) not null ,
    duration timestamp not null ,
    release_year smallint not null
);*/
