-- Add missing actors referenced in relationships
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Al Pacino', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Al Pacino');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Marlon Brando', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Marlon Brando');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Tim Robbins', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Tim Robbins');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Morgan Freeman', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Morgan Freeman');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Christian Bale', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Christian Bale');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Heath Ledger', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Heath Ledger');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Elliot Page', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Elliot Page');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Elijah Wood', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Elijah Wood');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Viggo Mortensen', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Viggo Mortensen');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Keanu Reeves', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Keanu Reeves');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Carrie-Anne Moss', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Carrie-Anne Moss');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Jodie Foster', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Jodie Foster');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Anthony Hopkins', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Anthony Hopkins');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Mark Hamill', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Mark Hamill');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Carrie Fisher', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Carrie Fisher');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Sam Neill', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Sam Neill');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Laura Dern', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Laura Dern');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Matthew McConaughey', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Matthew McConaughey');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Anne Hathaway', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Anne Hathaway');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Rumi Hiiragi', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Rumi Hiiragi');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Song Kang-ho', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Song Kang-ho');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Jang Hye-jin', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Jang Hye-jin');
INSERT INTO actors (full_name, birthday, biography)
SELECT 'Hugh Jackman', NULL, '' WHERE NOT EXISTS (SELECT 1 FROM actors WHERE full_name = 'Hugh Jackman');
