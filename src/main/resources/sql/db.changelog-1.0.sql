--liquibase formatted sql
    --create tables

--changeset Volkov Pavel:1
create table if not exists movies
(
    movie_id serial primary key ,
    title varchar(128) not null ,
    description text ,
    rate decimal ,
    director varchar(128) not null ,
    duration timestamp not null ,
    award_id int ,
    release_year smallint not null
);
--rollback DROP TABLE movies

--changeset Volkov Pavel:2
create table if not exists users
(
    user_id serial primary key ,
    name varchar(256) not null ,
    surname varchar(256) not null ,
    login varchar(256) not null unique ,
    password text
);
--rollback DROP TABLE users

--changeset Volkov Pavel:3
create table if not exists review
(
    review_id serial primary key ,
    user_id int not null ,
    movie_id int not null ,
    rating decimal ,
    review_text text ,
    created_at date
);
--rollback DROP TABLE review

--changeset Volkov Pavel:4
create table if not exists favourites
(
    favourites_id serial primary key ,
    movie_id int not null ,
    user_id int not null
);
--rollback DROP TABLE favourites

--changeset Volkov Pavel:5
create table if not exists movies_actors
(
    movie_id int not null ,
    actor_id int not null ,
    role varchar(64) ,
    primary key (movie_id, actor_id)
);
--rollback DROP TABLE movies_actors

--changeset Volkov Pavel:6
create table if not exists actors
(
    actor_id serial primary key ,
    full_name varchar(256) not null ,
    birthday date ,
    biography text ,
    award_id int
);
--rollback DROP TABLE actors

--changeset Volkov Pavel:7
create table if not exists movies_awards
(
    movie_id int not null ,
    award_id int not null ,
    primary key (movie_id, award_id)
);
--rollback DROP TABLE movies_awards

--changeset Volkov Pavel:8
create table if not exists actors_awards
(
    award_id int not null ,
    actor_id int not null ,
    primary key (award_id, actor_id)
);
--rollback DROP TABLE actors_awards

--changeset Volkov Pavel:9
create table if not exists awards
(
    award_id serial primary key ,
    award_name varchar(64) not null ,
    award_type varchar(64) ,
    year date
);
--rollback DROP TABLE awards