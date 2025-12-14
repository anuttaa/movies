package com.coco.jumboomovies.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Permission {

    READ("READ"),
    UPDATE("UPDATE"),
    CREATE("CREATE"),
    DELETE("DELETE")
    ;

    private final String permission;
}
