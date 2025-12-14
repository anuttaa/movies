package com.example.movies.entities;

import com.example.movies.model.NamedEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "awards")
@AttributeOverride(name = "name", column = @Column(name = "award_name"))
@AttributeOverride(name = "id", column = @Column(name = "award_id"))
public class Award extends NamedEntity {

    private String awardType;

    private String awardDescription;

    private LocalDate year;
}
/*create table if not exists awards
(
    award_id serial primary key ,
    award_name varchar(64) not null ,
    award_type varchar(64) ,
    award_description text,
    year date
);*/
