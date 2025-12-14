--changeset Telpuk Nikifor:1
INSERT INTO movies (title, description, rate, director, duration, release_year, poster)
VALUES
    (
      'The Shawshank Redemption',
      'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
      9.3,
      (SELECT director_id FROM directors WHERE director_full_name = 'Frank Darabont'),
      '2h 22m', 1994,
      'https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Godfather',
      'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
      9.2,
      (SELECT director_id FROM directors WHERE director_full_name = 'Francis Ford Coppola'),
      '2h 55m', 1972,
      'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Dark Knight',
      'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
      9.0,
      (SELECT director_id FROM directors WHERE director_full_name = 'Christopher Nolan'),
      '2h 32m', 2008,
      'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Inception',
      'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea in the mind of a CEO.',
      8.8,
      (SELECT director_id FROM directors WHERE director_full_name = 'Christopher Nolan'),
      '2h 28m', 2010,
      'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Lord of the Rings: The Fellowship of the Ring',
      'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.',
      8.8,
      (SELECT director_id FROM directors WHERE director_full_name = 'Peter Jackson'),
      '2h 58m', 2001,
      'https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Forrest Gump',
      'The story of a feebleminded man who witnesses and influences some of the major historical events in the latter half of the 20th century in the United States.',
      8.8,
      (SELECT director_id FROM directors WHERE director_full_name = 'Robert Zemeckis'),
      '2h 22m', 1994,
      'https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Matrix',
      'A computer programmer is awakened from his simulated reality and must confront the realities of the world of machines that now dominate the human population.',
      8.7,
      (SELECT director_id FROM directors WHERE director_full_name = 'Lana Wachowski, Lilly Wachowski'),
      '2h 16m', 1999,
      'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Silence of the Lambs',
      'A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.',
      8.6,
      (SELECT director_id FROM directors WHERE director_full_name = 'Jonathan Demme'),
      '1h 58m', 1991,
      'https://m.media-amazon.com/images/M/MV5BNjNhZTk0ZmEtNjJhMi00YzFlLWE1MmEtYzM1M2ZmMGMwMTU3XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Star Wars: Episode IV - A New Hope',
      'Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to rescue a princess from the evil Galactic Empire.',
      8.6,
      (SELECT director_id FROM directors WHERE director_full_name = 'George Lucas'),
      '2h 5m', 1977,
      'https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Jurassic Park',
      'A billionaire provides a summercamp experience with live dinosaurs to divorce his grandchildrens parents.',
      8.2,
      (SELECT director_id FROM directors WHERE director_full_name = 'Steven Spielberg'),
      '2h 7m', 1993,
      'https://m.media-amazon.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM9OTcxMDE@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Avengers',
      'Earths mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.',
      8.0,
      (SELECT director_id FROM directors WHERE director_full_name = 'Joss Whedon'),
      '2h 23m', 2012,
      'https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Interstellar',
      'A team of explorers travel through a wormhole in space in an attempt to ensure humanitys survival.',
      8.6,
      (SELECT director_id FROM directors WHERE director_full_name = 'Christopher Nolan'),
      '2h 49m', 2014,
      'https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Spirited Away',
      'During her familys move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.',
      8.5,
      (SELECT director_id FROM directors WHERE director_full_name = 'Hayao Miyazaki'),
      '2h 5m', 2001,
      'https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NzMxNDhlXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'Parasite',
      'A poor family, the Kims, con their way into becoming the servants of a rich family, the Parks. But their easy life gets complicated when their deception is threatened with exposure.',
      8.5,
      (SELECT director_id FROM directors WHERE director_full_name = 'Bong Joon-ho'),
      '2h 12m', 2019,
      'https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1000_.jpg'
    ),
    (
      'The Prestige',
      'After a tragic accident, two stage magicians in 1890s London engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.',
      8.5,
      (SELECT director_id FROM directors WHERE director_full_name = 'Christopher Nolan'),
      '2h 10m', 2006,
      'https://m.media-amazon.com/images/M/MV5BMjA4NDI0MTIxNF5BMl5BanBnXkFtZTYwNTM0MzY2._V1_FMjpg_UX1000_.jpg'
    );
