package com.coco.jumboomovies.model;

import com.fasterxml.jackson.annotation.JsonValue;

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
}
