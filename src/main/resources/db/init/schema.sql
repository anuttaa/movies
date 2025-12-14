-- Final database schema without author mentions
-- This script is executed by Postgres on first container initialization

-- Users
CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  surname VARCHAR(256) NOT NULL,
  login VARCHAR(256) NOT NULL UNIQUE,
  password TEXT,
  role VARCHAR(15) NOT NULL DEFAULT 'GUEST',
  is_email_verified BOOLEAN NOT NULL DEFAULT FALSE,
  avatar VARCHAR(150)
);

-- Directors
CREATE TABLE IF NOT EXISTS directors (
  director_id SERIAL PRIMARY KEY,
  director_full_name VARCHAR(128) UNIQUE NOT NULL
);

-- Awards
CREATE TABLE IF NOT EXISTS awards (
  award_id SERIAL PRIMARY KEY,
  award_name VARCHAR(255) NOT NULL,
  award_type VARCHAR(64),
  award_description TEXT,
  year DATE
);

-- Actors
CREATE TABLE IF NOT EXISTS actors (
  actor_id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  birthday DATE,
  biography TEXT
);

-- Movies
CREATE TABLE IF NOT EXISTS movies (
  movie_id SERIAL PRIMARY KEY,
  title VARCHAR(128) NOT NULL,
  description TEXT,
  rate DOUBLE PRECISION,
  poster VARCHAR(150),
  director VARCHAR(128) NOT NULL REFERENCES directors(director_full_name),
  duration VARCHAR(8) NOT NULL,
  release_year SMALLINT NOT NULL
);

-- Movies-Awards (many-to-many)
CREATE TABLE IF NOT EXISTS movies_awards (
  movie_id INT NOT NULL REFERENCES movies(movie_id) ON DELETE CASCADE,
  award_id INT NOT NULL REFERENCES awards(award_id) ON DELETE CASCADE,
  PRIMARY KEY (movie_id, award_id)
);

-- Actors-Awards (many-to-many)
CREATE TABLE IF NOT EXISTS actors_awards (
  actor_id INT NOT NULL REFERENCES actors(actor_id) ON DELETE CASCADE,
  award_id INT NOT NULL REFERENCES awards(award_id) ON DELETE CASCADE,
  PRIMARY KEY (actor_id, award_id)
);

-- Directors-Awards (many-to-many)
CREATE TABLE IF NOT EXISTS directors_awards (
  director_id INT NOT NULL REFERENCES directors(director_id) ON DELETE CASCADE,
  award_id INT NOT NULL REFERENCES awards(award_id) ON DELETE CASCADE,
  PRIMARY KEY (director_id, award_id)
);

-- Roles (actor plays character in movie)
CREATE TABLE IF NOT EXISTS movies_actors (
  movie_id INT NOT NULL REFERENCES movies(movie_id) ON DELETE CASCADE,
  actor_id INT NOT NULL REFERENCES actors(actor_id) ON DELETE CASCADE,
  role VARCHAR(64),
  PRIMARY KEY (movie_id, actor_id)
);

-- Reviews
CREATE TABLE IF NOT EXISTS review (
  review_id SERIAL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  movie_id INT NOT NULL REFERENCES movies(movie_id) ON DELETE CASCADE,
  rating DOUBLE PRECISION,
  review_text TEXT,
  created_at DATE NOT NULL DEFAULT NOW()
);

-- Favourites (users <-> movies)
CREATE TABLE IF NOT EXISTS favourites (
  favourites_id SERIAL PRIMARY KEY,
  movie_id INT NOT NULL REFERENCES movies(movie_id) ON DELETE CASCADE,
  user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE
);

-- Genres per movie
CREATE TABLE IF NOT EXISTS movie_genres (
  movie_id INT NOT NULL REFERENCES movies (movie_id) ON DELETE CASCADE,
  genre VARCHAR(50) NOT NULL,
  PRIMARY KEY (movie_id, genre)
);

-- Chats
CREATE TABLE IF NOT EXISTS chats (
  chat_id SERIAL PRIMARY KEY,
  chat_name TEXT NOT NULL
);

-- Messages
CREATE TABLE IF NOT EXISTS messages (
  message_id SERIAL PRIMARY KEY,
  sender_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  receiver_chat_id INT REFERENCES chats(chat_id) ON DELETE CASCADE,
  message TEXT NOT NULL,
  date TIMESTAMP NOT NULL
);

-- Users <-> Chats
CREATE TABLE IF NOT EXISTS chats_users (
  chat_id INT NOT NULL REFERENCES chats(chat_id) ON DELETE CASCADE,
  user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  PRIMARY KEY (chat_id, user_id)
);

-- Confirmation token
CREATE TABLE IF NOT EXISTS confirmation_token (
  id SERIAL PRIMARY KEY,
  token VARCHAR(255) NOT NULL,
  user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
  created_at TIMESTAMP NOT NULL,
  expires_at TIMESTAMP NOT NULL,
  confirmed_at TIMESTAMP
);
