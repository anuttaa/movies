package com.example.movies.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    THRILLER("Thriller"),
    ANIME("Anime"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    ENTERTAINMENT("Entertainment"),
    FAITH_AND_SPIRITUALITY("Faith and Spirituality"),
    FANTASY("Fantasy"),
    GAME_SHOW("Game Show"),
    LGBTQ("LGBTQ"),
    HEALTH_AND_WELLNESS("Health and Wellness"),
    HISTORY("History"),
    HOLIDAY("Holiday"),
    HORROR("Horror"),
    HOUSE_AND_GARDEN("House and Garden"),
    KIDS_AND_FAMILY("Kids and Family"),
    MUSIC("Music"),
    MYSTERY("Mystery"),
    SCIFI("Scifi"),
    ROMANCE("Romance"),
    MUSICAL("Musical");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Genre fromJson(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        for (Genre g : Genre.values()) {
            if (g.displayName.equalsIgnoreCase(trimmed)) {
                return g;
            }
            if (g.name().equalsIgnoreCase(trimmed.replace(' ', '_').replace('-', '_'))) {
                return g;
            }
        }
        throw new IllegalArgumentException("Unknown genre: " + value);
    }
}
