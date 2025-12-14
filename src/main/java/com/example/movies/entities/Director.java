package com.example.movies.entities;

import com.example.movies.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "directors")
@Data
@AttributeOverride(name = "name", column = @Column(name = "director_full_name"))
@AttributeOverride(name = "id", column = @Column(name = "director_id"))
public class Director extends NamedEntity {

    @ManyToMany
    @JoinTable(
            name = "directors_awards",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private List<Award> awards;

    @JsonIgnore
    @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
    private List<Movie> movies;
}
/*create table if not exists directors
(
    director_id serial primary key ,
    director_full_name varchar(128) unique not null ,
    director_award int not null
    );*/
