-- Populate the movies_awards table
INSERT INTO movies_awards (movie_id, award_id) VALUES
-- The Shawshank Redemption
((SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Film')),
((SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Picture')),

-- Forrest Gump
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Actor')),
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Director')),
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), (SELECT award_id FROM awards WHERE award_name='Golden Globe for Best Motion Picture - Drama')),
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), (SELECT award_id FROM awards WHERE award_name='Golden Globe for Best Actor')),
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Leading Actor')),

-- The Dark Knight
((SELECT movie_id FROM movies WHERE title='The Dark Knight'), (SELECT award_id FROM awards WHERE award_name='Academy Award for Best Cinematography')),
((SELECT movie_id FROM movies WHERE title='The Dark Knight'), (SELECT award_id FROM awards WHERE award_name='Critics Choice Movie Award for Best Sci-Fi/Horror Movie')),

-- The Lord of the Rings: The Fellowship of the Ring
((SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring'), (SELECT award_id FROM awards WHERE award_name='AFI Movie of the Year')),

-- The Matrix
((SELECT movie_id FROM movies WHERE title='The Matrix'), (SELECT award_id FROM awards WHERE award_name='BAFTA Award for Best Special Visual Effects')),
((SELECT movie_id FROM movies WHERE title='The Matrix'), (SELECT award_id FROM awards WHERE award_name='Saturn Award for Best Science Fiction Film')),

-- Parasite
((SELECT movie_id FROM movies WHERE title='Parasite'), (SELECT award_id FROM awards WHERE award_type='Cannes' AND year='2019-05-25')),

-- Inception
((SELECT movie_id FROM movies WHERE title='Inception'), (SELECT award_id FROM awards WHERE award_name='Best Director Award')),

-- The Avengers
((SELECT movie_id FROM movies WHERE title='The Avengers'), (SELECT award_id FROM awards WHERE award_name='Peoples Choice Award for Favorite Movie'));
