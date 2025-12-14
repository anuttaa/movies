--liquibase formatted sql
    --create links

--changeset Volkov Pavel:1
alter table favourites
add constraint fk_favourites_users
foreign key (user_id) references users(user_id)
on delete cascade;

--changeset Volkov Pavel:2
alter table favourites
add constraint fk_favourites_movies
foreign key (movie_id) references movies(movie_id)
on delete cascade;

--changeset Volkov Pavel:3
alter table review
add constraint fk_review_users
foreign key (user_id) references users(user_id)
on delete cascade;

--changeset Volkov Pavel:4
alter table review
add constraint fk_review_movies
foreign key (movie_id) references movies(movie_id)
on delete cascade;

--changeset Volkov Pavel:5
alter table movies_actors
add constraint fk_moviesActors_movies
foreign key (movie_id) references movies(movie_id)
on delete cascade;

--changeset Volkov Pavel:6
alter table movies_actors
add constraint fk_moviesActors_actors
foreign key (actor_id) references actors(actor_id)
on delete cascade;

--changeset Volkov Pavel:7
alter table actors_awards
add constraint fk_actorsAwards_actors
foreign key (actor_id) references actors(actor_id)
on delete cascade;

--changeset Volkov Pavel:8
alter table actors_awards
add constraint fk_actorsAwards_awards
foreign key (award_id) references awards(award_id)
on delete cascade;

--changeset Volkov Pavel:9
alter table movies_awards
add constraint fk_moviesAwards_awards
foreign key (award_id) references awards(award_id)
on delete cascade;

--changeset Volkov Pavel:10
alter table movies_awards
add constraint fk_moviesAwards_movies
foreign key (movie_id) references movies(movie_id)
on delete cascade;