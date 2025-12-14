--changeset Telpuk Nikifor:1
create table if not exists movie_genres
(
    movie_id int not null,
    genre varchar(50) not null,
    primary key (movie_id, genre),
    foreign key (movie_id) references movies (movie_id) on delete cascade
);
--rollback DROP TABLE movie_genres