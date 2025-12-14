--change type in movies table

--changeset Volkov Pavel:1
ALTER TABLE movies
alter column duration TYPE varchar(8);

