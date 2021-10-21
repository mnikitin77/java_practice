-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
SELECT t1.title, t1.showtime, t1.duration_value, t2.title, t2.showtime, t2.duration_value FROM
	(SELECT ms1.id, m1.title, ms1.showtime, d1.duration_value FROM movie_show ms1
		INNER JOIN movie m1 ON m1.id = ms1.movie_id
		INNER JOIN duration d1 ON d1.id = m1.duration_id) AS t1
INNER JOIN 
	(SELECT ms2.id, m2.title, ms2.showtime, d2.duration_value FROM movie_show ms2
		INNER JOIN movie m2 ON m2.id = ms2.movie_id
		INNER JOIN duration d2 ON d2.id = m2.duration_id) AS t2
ON t1.id <> t2.id
WHERE t1.showtime < t2.showtime AND
	t1.showtime + t1.duration_value * interval '1 minute' > t2.showtime
ORDER BY t1.showtime, t2.showtime
	
-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
SELECT 	t1.title AS "Movie 1", 
		t1.showtime AS "Starts at", 
		t1.duration_value AS "Duration", 
		t2.showtime AS "Next Starts At", 
		(t2.showtime - (t1.showtime + t1.duration_value * interval '1 minute')) AS Break
FROM
	(SELECT ms2.id, m2.title, ms2.showtime FROM movie_show ms2
		INNER JOIN movie m2 ON m2.id = ms2.movie_id
		INNER JOIN duration d2 ON d2.id = m2.duration_id) AS t2
INNER JOIN 
	(SELECT ms1.id, m1.title, ms1.showtime, d1.duration_value FROM movie_show ms1
		INNER JOIN movie m1 ON m1.id = ms1.movie_id
		INNER JOIN duration d1 ON d1.id = m1.duration_id) AS t1
ON t1.id <> t2.id
WHERE t1.showtime < t2.showtime AND
	(t1.showtime + (t1.duration_value + 30) * interval '1 minute') <= t2.showtime
ORDER BY Break DESC

-- список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
WITH stats AS (
	SELECT	m.title, 
			COALESCE(SUM(show_stat.show_count), 0) AS "total visits",
			COALESCE(ROUND(AVG(show_stat.show_count),2), 0) AS "average visits per show",
			COALESCE(SUM(show_stat.show_sales), money(0)) AS "total sales"
	FROM movie m
	LEFT JOIN
		(SELECT ms.id, ms.movie_id, COUNT(st.id) as show_count, SUM(ms.price) as show_sales FROM movie_show ms
			INNER JOIN sold_ticket st
		ON st.movie_show_id = ms.id
		GROUP BY ms.id) AS show_stat
	ON m.id = show_stat.id
	GROUP BY m.title
)
(SELECT * FROM stats ORDER BY "total sales" DESC)
UNION
(SELECT	'итого', 
		COALESCE(SUM(stats."total visits"), 0), 
		COALESCE(ROUND(AVG(stats."average visits per show"), 2), 0), 
		COALESCE(SUM(stats."total sales"), money(0))
FROM stats)
	
	
	
	