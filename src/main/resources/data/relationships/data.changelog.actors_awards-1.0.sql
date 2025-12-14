-- Populate the actors_awards table
INSERT INTO actors_awards (actor_id, award_id) VALUES
-- Tom Hanks
((SELECT actor_id FROM actors WHERE full_name='Tom Hanks'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Actor')),
((SELECT actor_id FROM actors WHERE full_name='Tom Hanks'), (SELECT award_id FROM awards WHERE award_name='Golden Globe for Best Actor')),
((SELECT actor_id FROM actors WHERE full_name='Tom Hanks'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Leading Actor')),

-- Leonardo DiCaprio
((SELECT actor_id FROM actors WHERE full_name='Leonardo DiCaprio'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Cinematography')),

-- Meryl Streep
((SELECT actor_id FROM actors WHERE full_name='Meryl Streep'), (SELECT award_id FROM awards WHERE award_name='Golden Globe for Best Actress')),
((SELECT actor_id FROM actors WHERE full_name='Meryl Streep'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Director')),

-- Chadwick Boseman
((SELECT actor_id FROM actors WHERE full_name='Chadwick Boseman'), (SELECT award_id FROM awards WHERE award_name='Peoples Choice Award for Favorite Movie')),

-- Scarlett Johansson
((SELECT actor_id FROM actors WHERE full_name='Scarlett Johansson'), (SELECT award_id FROM awards WHERE award_name='Critics Choice Movie Award for Best Sci-Fi/Horror Movie')),

-- Viola Davis
((SELECT actor_id FROM actors WHERE full_name='Viola Davis'), (SELECT award_id FROM awards WHERE award_name='Saturn Award for Best Science Fiction Film')),

-- Rami Malek
((SELECT actor_id FROM actors WHERE full_name='Rami Malek'), (SELECT award_id FROM awards WHERE award_name='Golden Globe for Best Actor')),

-- Jennifer Lawrence
((SELECT actor_id FROM actors WHERE full_name='Jennifer Lawrence'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Film')),

-- Brad Pitt
((SELECT actor_id FROM actors WHERE full_name='Brad Pitt'), (SELECT award_id FROM awards WHERE award_type='Cannes' AND year='2019-05-25')),

-- Lupita Nyong'o
((SELECT actor_id FROM actors WHERE full_name='Lupita Nyong''o'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Special Visual Effects')),

-- Zendaya
((SELECT actor_id FROM actors WHERE full_name='Zendaya'), (SELECT award_id FROM awards WHERE award_name='AFI Movie of the Year')),

-- Mahershala Ali
((SELECT actor_id FROM actors WHERE full_name='Mahershala Ali'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Actor')),

-- Timothée Chalamet
((SELECT actor_id FROM actors WHERE full_name='Timothée Chalamet'), (SELECT award_id FROM awards WHERE award_name='Critics Choice Movie Award for Best Sci-Fi/Horror Movie'));
