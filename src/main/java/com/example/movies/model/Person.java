package com.example.movies.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public class Person extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
