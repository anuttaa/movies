INSERT INTO movie_genres (movie_id, genre) VALUES
-- The Godfather
((SELECT movie_id FROM movies WHERE title='The Godfather'), 'CRIME'),
((SELECT movie_id FROM movies WHERE title='The Godfather'), 'DRAMA'),

-- The Shawshank Redemption
((SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'), 'DRAMA'),

-- The Dark Knight
((SELECT movie_id FROM movies WHERE title='The Dark Knight'), 'ACTION'),
((SELECT movie_id FROM movies WHERE title='The Dark Knight'), 'CRIME'),
((SELECT movie_id FROM movies WHERE title='The Dark Knight'), 'DRAMA'),

-- Inception
((SELECT movie_id FROM movies WHERE title='Inception'), 'ACTION'),
((SELECT movie_id FROM movies WHERE title='Inception'), 'SCI-FI'),
((SELECT movie_id FROM movies WHERE title='Inception'), 'THRILLER'),

-- The Lord of the Rings: The Fellowship of the Ring
((SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring'), 'ADVENTURE'),
((SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring'), 'FANTASY'),
((SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring'), 'DRAMA'),

-- Forrest Gump
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), 'DRAMA'),
((SELECT movie_id FROM movies WHERE title='Forrest Gump'), 'ROMANCE'),

-- The Matrix
((SELECT movie_id FROM movies WHERE title='The Matrix'), 'ACTION'),
((SELECT movie_id FROM movies WHERE title='The Matrix'), 'SCI-FI'),

-- The Silence of the Lambs
((SELECT movie_id FROM movies WHERE title='The Silence of the Lambs'), 'CRIME'),
((SELECT movie_id FROM movies WHERE title='The Silence of the Lambs'), 'THRILLER'),
((SELECT movie_id FROM movies WHERE title='The Silence of the Lambs'), 'DRAMA'),

-- Star Wars: Episode IV - A New Hope
((SELECT movie_id FROM movies WHERE title='Star Wars: Episode IV - A New Hope'), 'ADVENTURE'),
((SELECT movie_id FROM movies WHERE title='Star Wars: Episode IV - A New Hope'), 'SCI-FI'),
((SELECT movie_id FROM movies WHERE title='Star Wars: Episode IV - A New Hope'), 'FANTASY'),

-- Jurassic Park
((SELECT movie_id FROM movies WHERE title='Jurassic Park'), 'ADVENTURE'),
((SELECT movie_id FROM movies WHERE title='Jurassic Park'), 'SCI-FI'),
((SELECT movie_id FROM movies WHERE title='Jurassic Park'), 'THRILLER'),

-- The Avengers
((SELECT movie_id FROM movies WHERE title='The Avengers'), 'ACTION'),
((SELECT movie_id FROM movies WHERE title='The Avengers'), 'ADVENTURE'),
((SELECT movie_id FROM movies WHERE title='The Avengers'), 'SCI-FI'),

-- Interstellar
((SELECT movie_id FROM movies WHERE title='Interstellar'), 'SCI-FI'),
((SELECT movie_id FROM movies WHERE title='Interstellar'), 'DRAMA'),
((SELECT movie_id FROM movies WHERE title='Interstellar'), 'ADVENTURE'),

-- Spirited Away
((SELECT movie_id FROM movies WHERE title='Spirited Away'), 'ANIMATION'),
((SELECT movie_id FROM movies WHERE title='Spirited Away'), 'FANTASY'),
((SELECT movie_id FROM movies WHERE title='Spirited Away'), 'ADVENTURE'),

-- Parasite
((SELECT movie_id FROM movies WHERE title='Parasite'), 'DRAMA'),
((SELECT movie_id FROM movies WHERE title='Parasite'), 'THRILLER'),

-- The Prestige
((SELECT movie_id FROM movies WHERE title='The Prestige'), 'DRAMA'),
((SELECT movie_id FROM movies WHERE title='The Prestige'), 'MYSTERY'),
((SELECT movie_id FROM movies WHERE title='The Prestige'), 'SCI-FI');
