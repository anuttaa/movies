package com.coco.jumboomovies.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    private String name;
}
