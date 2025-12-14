package com.example.movies.entities;

import com.example.movies.model.NamedEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actors")
@AttributeOverride(name = "name", column = @Column(name = "full_name"))
@AttributeOverride(name = "id", column = @Column(name = "actor_id"))
public class Actor extends NamedEntity {

    private LocalDate birthday;
    private String biography;

    @ToString.Exclude
    @OneToMany(mappedBy = "actor")
    private Set<Role> discography;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "actors_awards",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private List<Award> awards;
}

