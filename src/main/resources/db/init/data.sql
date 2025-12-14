-- Seed test data (no author mentions)

-- Directors
INSERT INTO directors (director_full_name)
SELECT 'Christopher Nolan'
WHERE NOT EXISTS (SELECT 1 FROM directors WHERE director_full_name = 'Christopher Nolan');
INSERT INTO directors (director_full_name)
SELECT 'Peter Jackson'
WHERE NOT EXISTS (SELECT 1 FROM directors WHERE director_full_name = 'Peter Jackson');
INSERT INTO directors (director_full_name)
SELECT 'Frank Darabont'
WHERE NOT EXISTS (SELECT 1 FROM directors WHERE director_full_name = 'Frank Darabont');

-- Awards
INSERT INTO awards (award_name, award_type, award_description, year)
SELECT 'Oscar', 'Academy', 'Academy Awards', '2000-01-01'
WHERE NOT EXISTS (SELECT 1 FROM awards WHERE award_name = 'Oscar');
INSERT INTO awards (award_name, award_type, award_description, year)
SELECT 'Golden Globe', 'Golden', 'Golden Globe Awards', '2005-01-01'
WHERE NOT EXISTS (SELECT 1 FROM awards WHERE award_name = 'Golden Globe');
INSERT INTO awards (award_name, award_type, award_description, year)
SELECT 'BAFTA', 'British', 'British Academy Film Awards', '2010-01-01'
WHERE NOT EXISTS (SELECT 1 FROM awards WHERE award_name = 'BAFTA');

-- Actors
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Leonardo DiCaprio', '1974-11-11', 'American actor'
WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Leonardo DiCaprio');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Joseph Gordon-Levitt', '1981-02-17', 'American actor'
WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Joseph Gordon-Levitt');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Morgan Freeman', '1937-06-01', 'American actor'
WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Morgan Freeman');

-- Movies
INSERT INTO movies (title, description, rate, poster, director, duration, release_year)
SELECT 'Inception', 'A mind-bending heist within dreams.', 8.8,
   'https://example.com/inception.jpg', 'Christopher Nolan', '2h 28m', 2010
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Inception');
INSERT INTO movies (title, description, rate, poster, director, duration, release_year)
SELECT 'The Shawshank Redemption', 'Hope is a good thing.', 9.3,
   'https://example.com/shawshank.jpg', 'Frank Darabont', '2h 22m', 1994
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Shawshank Redemption');
INSERT INTO movies (title, description, rate, poster, director, duration, release_year)
SELECT 'The Lord of the Rings: The Fellowship of the Ring', 'Journey to destroy the One Ring.', 8.8,
   'https://example.com/lotr1.jpg', 'Peter Jackson', '2h 58m', 2001
WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'The Lord of the Rings: The Fellowship of the Ring');

-- Movies Awards
INSERT INTO movies_awards (movie_id, award_id)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'),
       (SELECT award_id FROM awards WHERE award_name='BAFTA')
WHERE NOT EXISTS (
  SELECT 1 FROM movies_awards
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND award_id = (SELECT award_id FROM awards WHERE award_name='BAFTA')
);
INSERT INTO movies_awards (movie_id, award_id)
SELECT (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'),
       (SELECT award_id FROM awards WHERE award_name='Oscar')
WHERE NOT EXISTS (
  SELECT 1 FROM movies_awards
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption')
    AND award_id = (SELECT award_id FROM awards WHERE award_name='Oscar')
);

-- Actors Awards
INSERT INTO actors_awards (actor_id, award_id)
SELECT (SELECT actor_id FROM actors WHERE full_name='Leonardo DiCaprio'),
       (SELECT award_id FROM awards WHERE award_name='Oscar')
WHERE NOT EXISTS (
  SELECT 1 FROM actors_awards
  WHERE actor_id = (SELECT actor_id FROM actors WHERE full_name='Leonardo DiCaprio')
    AND award_id = (SELECT award_id FROM awards WHERE award_name='Oscar')
);

-- Directors Awards
INSERT INTO directors_awards (director_id, award_id)
SELECT (SELECT director_id FROM directors WHERE director_full_name='Christopher Nolan'),
       (SELECT award_id FROM awards WHERE award_name='BAFTA')
WHERE NOT EXISTS (
  SELECT 1 FROM directors_awards
  WHERE director_id = (SELECT director_id FROM directors WHERE director_full_name='Christopher Nolan')
    AND award_id = (SELECT award_id FROM awards WHERE award_name='BAFTA')
);

-- Roles (movies_actors)
INSERT INTO movies_actors (movie_id, actor_id, role)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'),
       (SELECT actor_id FROM actors WHERE full_name='Leonardo DiCaprio'), 'Cobb'
WHERE NOT EXISTS (
  SELECT 1 FROM movies_actors
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND actor_id = (SELECT actor_id FROM actors WHERE full_name='Leonardo DiCaprio')
);
INSERT INTO movies_actors (movie_id, actor_id, role)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'),
       (SELECT actor_id FROM actors WHERE full_name='Joseph Gordon-Levitt'), 'Arthur'
WHERE NOT EXISTS (
  SELECT 1 FROM movies_actors
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND actor_id = (SELECT actor_id FROM actors WHERE full_name='Joseph Gordon-Levitt')
);
INSERT INTO movies_actors (movie_id, actor_id, role)
SELECT (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'),
       (SELECT actor_id FROM actors WHERE full_name='Morgan Freeman'), 'Red'
WHERE NOT EXISTS (
  SELECT 1 FROM movies_actors
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption')
    AND actor_id = (SELECT actor_id FROM actors WHERE full_name='Morgan Freeman')
);

-- Users
INSERT INTO users (name, surname, login, password, role, is_email_verified, avatar)
SELECT 'Pavel', 'Volkov', 'pavel@example.com', 'password', 'USER', FALSE, NULL
WHERE NOT EXISTS (SELECT 1 FROM users WHERE login = 'pavel@example.com');
INSERT INTO users (name, surname, login, password, role, is_email_verified, avatar)
SELECT 'Nikifor', 'Telpuk', 'nikifor@example.com', 'password', 'GUEST', FALSE, NULL
WHERE NOT EXISTS (SELECT 1 FROM users WHERE login = 'nikifor@example.com');

-- Reviews
INSERT INTO review (user_id, movie_id, rating, review_text)
SELECT (SELECT user_id FROM users WHERE login='pavel@example.com'),
       (SELECT movie_id FROM movies WHERE title='Inception'), 9.0, 'Amazing concept'
WHERE NOT EXISTS (
  SELECT 1 FROM review
  WHERE user_id = (SELECT user_id FROM users WHERE login='pavel@example.com')
    AND movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
);
INSERT INTO review (user_id, movie_id, rating, review_text)
SELECT (SELECT user_id FROM users WHERE login='nikifor@example.com'),
       (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'), 10.0, 'Masterpiece'
WHERE NOT EXISTS (
  SELECT 1 FROM review
  WHERE user_id = (SELECT user_id FROM users WHERE login='nikifor@example.com')
    AND movie_id = (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption')
);

-- Favourites
INSERT INTO favourites (movie_id, user_id)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'),
       (SELECT user_id FROM users WHERE login='pavel@example.com')
WHERE NOT EXISTS (
  SELECT 1 FROM favourites
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND user_id = (SELECT user_id FROM users WHERE login='pavel@example.com')
);

-- Chats and messages
INSERT INTO chats (chat_name)
SELECT 'General'
WHERE NOT EXISTS (SELECT 1 FROM chats WHERE chat_name = 'General');
INSERT INTO chats_users (chat_id, user_id)
SELECT (SELECT chat_id FROM chats WHERE chat_name='General'),
       (SELECT user_id FROM users WHERE login='pavel@example.com')
WHERE NOT EXISTS (
  SELECT 1 FROM chats_users
  WHERE chat_id = (SELECT chat_id FROM chats WHERE chat_name='General')
    AND user_id = (SELECT user_id FROM users WHERE login='pavel@example.com')
);
INSERT INTO messages (sender_id, receiver_chat_id, message, date)
SELECT (SELECT user_id FROM users WHERE login='pavel@example.com'),
       (SELECT chat_id FROM chats WHERE chat_name='General'),
       'Welcome to the chat!', NOW()
WHERE NOT EXISTS (
  SELECT 1 FROM messages
  WHERE sender_id = (SELECT user_id FROM users WHERE login='pavel@example.com')
    AND receiver_chat_id = (SELECT chat_id FROM chats WHERE chat_name='General')
    AND message = 'Welcome to the chat!'
);

-- Movie genres
INSERT INTO movie_genres (movie_id, genre)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'), 'SCI-FI'
WHERE NOT EXISTS (
  SELECT 1 FROM movie_genres
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND genre = 'SCI-FI'
);
INSERT INTO movie_genres (movie_id, genre)
SELECT (SELECT movie_id FROM movies WHERE title='Inception'), 'ACTION'
WHERE NOT EXISTS (
  SELECT 1 FROM movie_genres
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='Inception')
    AND genre = 'ACTION'
);
INSERT INTO movie_genres (movie_id, genre)
SELECT (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption'), 'DRAMA'
WHERE NOT EXISTS (
  SELECT 1 FROM movie_genres
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='The Shawshank Redemption')
    AND genre = 'DRAMA'
);
INSERT INTO movie_genres (movie_id, genre)
SELECT (SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring'), 'FANTASY'
WHERE NOT EXISTS (
  SELECT 1 FROM movie_genres
  WHERE movie_id = (SELECT movie_id FROM movies WHERE title='The Lord of the Rings: The Fellowship of the Ring')
    AND genre = 'FANTASY'
);
