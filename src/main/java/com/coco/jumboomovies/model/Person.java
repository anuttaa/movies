package com.coco.jumboomovies.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
