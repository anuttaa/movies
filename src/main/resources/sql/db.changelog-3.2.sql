--changeset Telpuk Nikifor:1
ALTER TABLE movies
    alter column duration TYPE varchar(8);

--changeset Telpuk Nikifor:2
ALTER TABLE movies
    ALTER COLUMN director TYPE INT USING director::INTEGER;

--changeset Telpuk Nikifor:3
ALTER TABLE movies
    ALTER COLUMN director DROP NOT NULL;

--changeset Telpuk Nikifor:4
ALTER TABLE movies
    ADD CONSTRAINT fk_movies_director FOREIGN KEY (director)
        REFERENCES directors (director_id)
        ON DELETE SET NULL;
