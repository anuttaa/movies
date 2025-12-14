--liquibase formatted sql
--create director table

--changeset Telpuk Nikifor:1
create table if not exists directors
(
    director_id serial primary key ,
    director_full_name varchar(128) unique not null ,
    director_award int not null
    );

--add description for award
--changeset Volkov Pavel:2
alter table awards
    add column award_description text;

--changeset Telpuk Nikifor:3
create table if not exists directors_awards
(
    director_id int not null ,
    award_id int not null ,
    primary key (director_id, award_id)
    );

--changeset Telpuk Nikifor:4
alter table directors_awards
    add constraint fk_directorsAwards_directors
        foreign key (director_id) references directors(director_id)
            on delete cascade;

--changeset Telpuk Nikifor:5
alter table directors_awards
    add constraint fk_directorsAwards_awards
        foreign key (award_id) references awards(award_id)
            on delete cascade;


