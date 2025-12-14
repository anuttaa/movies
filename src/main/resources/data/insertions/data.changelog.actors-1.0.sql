--liquibase formatted data
--add actors

--changeset Telpuk Nikifor:1
INSERT INTO actors (full_name, birthday, biography)
VALUES
    ('Tom Hanks', '1956-07-09', 'Tom Hanks is an American actor and filmmaker. Known for his versatile performances in both dramatic and comedic roles, he is one of the most popular and recognizable film stars worldwide.'),
    ('Meryl Streep', '1949-06-22', 'Meryl Streep is an American actress who has been called "the best actress of her generation". Streep has received a record 21 Academy Award nominations, and has won three Oscars.'),
    ('Leonardo DiCaprio', '1974-11-11', 'Leonardo DiCaprio is an American actor and film producer. He is one of the most popular and recognizable film stars worldwide, and is widely regarded as one of the greatest actors of his generation.'),
    ('Denzel Washington', '1954-12-28', 'Denzel Washington is an American actor, director, and producer. He has been described as an actor who reconfigured "the concept of classic moviestardom", transforming the 20th-century model of the African-American actor.'),
    ('Emma Stone', '1988-11-06', 'Emma Stone is an American actress. One of the world''s highest-paid actresses, she has received various accolades, including an Academy Award, a BAFTA Award, and a Golden Globe Award.'),
    ('Robert Downey Jr.', '1965-04-04', 'Robert Downey Jr. is an American actor and producer. His career has been characterized by critical and popular success in his youth, followed by a period of substance abuse and legal troubles.'),
    ('Chadwick Boseman', '1976-11-29', 'Chadwick Boseman was an American actor. He is known for his portrayals of real-life historical figures, such as Jackie Robinson in 42, James Brown in Get on Up, and Thurgood Marshall in Marshall.'),
    ('Scarlett Johansson', '1984-11-22', 'Scarlett Johansson is an American actress. She is the world''s highest-paid actress and has featured multiple times in the Forbes Celebrity 100 list.'),
    ('Brad Pitt', '1963-12-18', 'Brad Pitt is an American actor and producer. He is one of the most popular and recognizable film stars worldwide, and is widely regarded as one of the greatest actors of his generation.'),
    ('Jennifer Lawrence', '1990-08-15', 'Jennifer Lawrence is an American actress. She is the youngest person to achieve four Academy Award nominations, and has won one.');

--changeset Telpuk Nikifor:2
INSERT INTO actors (full_name, birthday, biography)
VALUES
    ('Dwayne Johnson', '1972-05-02', 'Dwayne "The Rock" Johnson is an American actor, producer, and former professional wrestler. He is one of the highest-paid and most popular actors in the world.'),
    ('Margot Robbie', '1990-07-02', 'Margot Robbie is an Australian actress and producer. She is one of the world''s highest-paid actresses and has received multiple accolades, including a nomination for an Academy Award.'),
    ('Tom Holland', '1996-06-01', 'Tom Holland is an English actor. He is best known for playing Spider-Man in the Marvel Cinematic Universe, a role for which he has received critical acclaim.'),
    ('Gal Gadot', '1985-04-30', 'Gal Gadot is an Israeli actress and model. She is best known for her role as Wonder Woman in the DC Extended Universe, for which she has received critical praise.'),
    ('Rami Malek', '1981-05-12', 'Rami Malek is an American actor. He is best known for his role as Elliot Alderson in the psychological thriller television series Mr. Robot, for which he won a Primetime Emmy Award.'),
    ('Viola Davis', '1965-08-11', 'Viola Davis is an American actress and producer. She is the first black actor to achieve the "Triple Crown of Acting", winning an Oscar, an Emmy, and a Tony Award.'),
    ('Mahershala Ali', '1974-02-16', 'Mahershala Ali is an American actor. He has received two Academy Awards and a Golden Globe Award for his performances in Moonlight and Green Book.'),
    ('Zendaya', '1996-09-01', 'Zendaya is an American actress and singer. She is the youngest person to receive the Primetime Emmy Award for Outstanding Lead Actress in a Drama Series.'),
    ('Timothée Chalamet', '1995-12-27', 'Timothée Chalamet is an American actor. He is the youngest actor to be nominated for an Academy Award for Best Actor since Mickey Rooney in 1939.'),
    ('Lupita Nyong''o', '1983-03-01', 'Lupita Nyong''o is a Kenyan-Mexican actress. She is the first Kenyan and Mexican actress to win an Academy Award, for her role in 12 Years a Slave.'),
    ('Al Pacino', NULL, ''),
    ('Marlon Brando', NULL, ''),
    ('Tim Robbins', NULL, ''),
    ('Morgan Freeman', NULL, ''),
    ('Christian Bale', NULL, ''),
    ('Heath Ledger', NULL, ''),
    ('Elliot Page', NULL, ''),
    ('Elijah Wood', NULL, ''),
    ('Viggo Mortensen', NULL, ''),
    ('Keanu Reeves', NULL, ''),
    ('Carrie-Anne Moss', NULL, ''),
    ('Jodie Foster', NULL, ''),
    ('Anthony Hopkins', NULL, ''),
    ('Mark Hamill', NULL, ''),
    ('Carrie Fisher', NULL, ''),
    ('Sam Neill', NULL, ''),
    ('Laura Dern', NULL, ''),
    ('Matthew McConaughey', NULL, ''),
    ('Anne Hathaway', NULL, ''),
    ('Rumi Hiiragi', NULL, ''),
    ('Song Kang-ho', NULL, ''),
    ('Jang Hye-jin', NULL, ''),
    ('Hugh Jackman', NULL, '');
