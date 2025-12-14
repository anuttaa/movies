package com.example.movies.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    private String name;
}
