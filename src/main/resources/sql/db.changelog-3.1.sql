--changeset Telpuk Nikifor:1
alter table actors
    drop column award_id;


--changeset Telpuk Nikifor:2
alter table movies
    drop column award_id;

--changeset Telpuk Nikifor:3
alter table directors
    drop column director_award;

--changeset Telpuk Nikifor:4
alter table favourites
    drop column favourites_id;