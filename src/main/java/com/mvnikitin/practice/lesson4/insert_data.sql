ALTER TABLE sold_ticket DROP CONSTRAINT IF EXISTS fk_sold_ticket_movie_show;
ALTER TABLE movie_show DROP CONSTRAINT IF EXISTS fk_movie_show_movie;
ALTER TABLE movie DROP CONSTRAINT IF EXISTS fk_movie_duration;

TRUNCATE TABLE duration;
TRUNCATE TABLE movie;
TRUNCATE TABLE movie_show;
TRUNCATE TABLE sold_ticket;

-- duration
INSERT INTO duration (id, duration_value) 
VALUES	(1, 60), (2, 90), (3, 120);

-- movie
INSERT INTO movie (title, duration_id) 
VALUES	('movie 1', 1), ('movie 2', 2), ('movie 3', 3), ('movie 4', 1), ('movie 5', 2);

-- movie_show
INSERT INTO movie_show (movie_id, showtime, price) 
VALUES  ((SELECT id FROM movie WHERE title = 'movie 1'), '2021-11-05 12:00:00', 100),
		((SELECT id FROM movie WHERE title = 'movie 1'), '2021-11-05 13:30:00', 120),
		((SELECT id FROM movie WHERE title = 'movie 1'), '2021-11-05 18:00:00', 230),
		((SELECT id FROM movie WHERE title = 'movie 4'), '2021-11-05 14:30:00', 120),
		((SELECT id FROM movie WHERE title = 'movie 4'), '2021-11-05 21:30:00', 230),
		((SELECT id FROM movie WHERE title = 'movie 3'), '2021-11-05 12:30:00', 100),
		((SELECT id FROM movie WHERE title = 'movie 3'), '2021-11-05 17:30:00', 150),
		((SELECT id FROM movie WHERE title = 'movie 3'), '2021-11-05 18:30:00', 230),
		((SELECT id FROM movie WHERE title = 'movie 3'), '2021-11-05 22:00:00', 230),
		((SELECT id FROM movie WHERE title = 'movie 2'), '2021-11-05 12:00:00', 100),
		((SELECT id FROM movie WHERE title = 'movie 2'), '2021-11-05 13:00:00', 120);
		
-- movie_show
INSERT INTO sold_ticket (movie_show_id, seat) 
VALUES  ((SELECT id FROM movie_show WHERE movie_id = (SELECT id FROM movie WHERE title = 'movie 1') AND showtime = '2021-11-05 12:00:00'), 123),
		((SELECT id FROM movie_show WHERE movie_id = (SELECT id FROM movie WHERE title = 'movie 4') AND showtime = '2021-11-05 21:30:00'), 45),
		((SELECT id FROM movie_show WHERE movie_id = (SELECT id FROM movie WHERE title = 'movie 1') AND showtime = '2021-11-05 12:00:00'), 230),
		((SELECT id FROM movie_show WHERE movie_id = (SELECT id FROM movie WHERE title = 'movie 4') AND showtime = '2021-11-05 21:30:00'), 120);

ALTER TABLE sold_ticket ADD CONSTRAINT fk_sold_ticket_movie_show FOREIGN KEY (movie_show_id) REFERENCES movie_show (id);
ALTER TABLE movie_show ADD CONSTRAINT fk_movie_show_movie FOREIGN KEY (movie_id) REFERENCES movie (id);
ALTER TABLE movie ADD CONSTRAINT fk_movie_duration FOREIGN KEY (duration_id) REFERENCES duration (id);