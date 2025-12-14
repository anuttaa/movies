--liquibase formatted sql
--images creation

--changeset Telpuk Nikifor:1
ALTER TABLE users
    ADD avatar VARCHAR(150);

--changeset Telpuk Nikifor:2
ALTER TABLE movies
    ADD poster VARCHAR(150);