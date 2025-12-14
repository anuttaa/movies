-- Populate the movies_actors table
-- The Godfather
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Godfather'),
  (SELECT actor_id FROM actors WHERE full_name = 'Al Pacino'),
  'Michael Corleone'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Godfather'),
  (SELECT actor_id FROM actors WHERE full_name = 'Marlon Brando'),
  'Vito Corleone'
);

-- The Shawshank Redemption
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Shawshank Redemption'),
  (SELECT actor_id FROM actors WHERE full_name = 'Tim Robbins'),
  'Andy Dufresne'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Shawshank Redemption'),
  (SELECT actor_id FROM actors WHERE full_name = 'Morgan Freeman'),
  'Ellis Boyd "Red" Redding'
);

-- The Dark Knight
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Dark Knight'),
  (SELECT actor_id FROM actors WHERE full_name = 'Christian Bale'),
  'Bruce Wayne / Batman'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Dark Knight'),
  (SELECT actor_id FROM actors WHERE full_name = 'Heath Ledger'),
  'The Joker'
);

-- Inception
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Inception'),
  (SELECT actor_id FROM actors WHERE full_name = 'Leonardo DiCaprio'),
  'Dom Cobb'
),
(
  (SELECT movie_id FROM movies WHERE title = 'Inception'),
  (SELECT actor_id FROM actors WHERE full_name = 'Elliot Page'),
  'Ariadne'
);

-- The Lord of the Rings: The Fellowship of the Ring
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'),
  (SELECT actor_id FROM actors WHERE full_name = 'Elijah Wood'),
  'Frodo Baggins'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'),
  (SELECT actor_id FROM actors WHERE full_name = 'Viggo Mortensen'),
  'Aragorn'
);

-- Forrest Gump
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Forrest Gump'),
  (SELECT actor_id FROM actors WHERE full_name = 'Tom Hanks'),
  'Forrest Gump'
);

-- The Matrix
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Matrix'),
  (SELECT actor_id FROM actors WHERE full_name = 'Keanu Reeves'),
  'Neo'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Matrix'),
  (SELECT actor_id FROM actors WHERE full_name = 'Carrie-Anne Moss'),
  'Trinity'
);

-- The Silence of the Lambs
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Silence of the Lambs'),
  (SELECT actor_id FROM actors WHERE full_name = 'Jodie Foster'),
  'Clarice Starling'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Silence of the Lambs'),
  (SELECT actor_id FROM actors WHERE full_name = 'Anthony Hopkins'),
  'Dr. Hannibal Lecter'
);

-- Star Wars: Episode IV - A New Hope
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Star Wars: Episode IV - A New Hope'),
  (SELECT actor_id FROM actors WHERE full_name = 'Mark Hamill'),
  'Luke Skywalker'
),
(
  (SELECT movie_id FROM movies WHERE title = 'Star Wars: Episode IV - A New Hope'),
  (SELECT actor_id FROM actors WHERE full_name = 'Carrie Fisher'),
  'Princess Leia'
);

-- Jurassic Park
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Jurassic Park'),
  (SELECT actor_id FROM actors WHERE full_name = 'Sam Neill'),
  'Dr. Alan Grant'
),
(
  (SELECT movie_id FROM movies WHERE title = 'Jurassic Park'),
  (SELECT actor_id FROM actors WHERE full_name = 'Laura Dern'),
  'Dr. Ellie Sattler'
);

-- The Avengers
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Avengers'),
  (SELECT actor_id FROM actors WHERE full_name = 'Robert Downey Jr.'),
  'Tony Stark / Iron Man'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Avengers'),
  (SELECT actor_id FROM actors WHERE full_name = 'Tom Holland'),
  'Spider-Man'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Avengers'),
  (SELECT actor_id FROM actors WHERE full_name = 'Scarlett Johansson'),
  'Natasha Romanoff'
);

-- Interstellar
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Interstellar'),
  (SELECT actor_id FROM actors WHERE full_name = 'Matthew McConaughey'),
  'Cooper'
),
(
  (SELECT movie_id FROM movies WHERE title = 'Interstellar'),
  (SELECT actor_id FROM actors WHERE full_name = 'Anne Hathaway'),
  'Brand'
);

-- Spirited Away
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Spirited Away'),
  (SELECT actor_id FROM actors WHERE full_name = 'Rumi Hiiragi'),
  'Chihiro Ogino'
);

-- Parasite
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'Parasite'),
  (SELECT actor_id FROM actors WHERE full_name = 'Song Kang-ho'),
  'Kim Ki-taek'
),
(
  (SELECT movie_id FROM movies WHERE title = 'Parasite'),
  (SELECT actor_id FROM actors WHERE full_name = 'Jang Hye-jin'),
  'Kim Chung-sook'
);

-- The Prestige
INSERT INTO movies_actors (movie_id, actor_id, role)
VALUES (
  (SELECT movie_id FROM movies WHERE title = 'The Prestige'),
  (SELECT actor_id FROM actors WHERE full_name = 'Hugh Jackman'),
  'Robert Angier'
),
(
  (SELECT movie_id FROM movies WHERE title = 'The Prestige'),
  (SELECT actor_id FROM actors WHERE full_name = 'Christian Bale'),
  'Alfred Borden'
);
