DROP SEQUENCE IF EXISTS movie_id_seq CASCADE;
DROP SEQUENCE IF EXISTS movie_show_id_seq CASCADE;
DROP SEQUENCE IF EXISTS sold_ticket_id_seq CASCADE;

CREATE SEQUENCE movie_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE movie_show_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
CREATE SEQUENCE sold_ticket_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

DROP TABLE IF EXISTS duration CASCADE;
DROP TABLE IF EXISTS movie CASCADE;
DROP TABLE IF EXISTS movie_show CASCADE;
DROP TABLE IF EXISTS sold_ticket CASCADE;

CREATE TABLE duration (
	id int NOT NULL,
	duration_value int,
	CONSTRAINT duration_pk PRIMARY KEY (id)
);

CREATE TABLE movie (
	id int NOT NULL DEFAULT nextval('movie_id_seq'::regclass),
	title character varying(255),
	duration_id int,
	CONSTRAINT movie_pk PRIMARY KEY (id),
	CONSTRAINT fk_movie_duration FOREIGN KEY (duration_id)
		REFERENCES duration (id)
);

CREATE TABLE movie_show (
	id int NOT NULL DEFAULT nextval('movie_show_id_seq'::regclass),
	movie_id int,
	showtime timestamp,
	price money,
	CONSTRAINT movie_show_pk PRIMARY KEY (id),
	CONSTRAINT fk_movie_show_movie FOREIGN KEY (movie_id)
		REFERENCES movie (id)
);

CREATE TABLE sold_ticket (
	id int NOT NULL DEFAULT nextval('sold_ticket_id_seq'::regclass),
	movie_show_id int,
	seat smallint,
	sale_time timestamp DEFAULT now(),
	CONSTRAINT sold_ticket_pk PRIMARY KEY (id),
	CONSTRAINT fk_sold_ticket_movie_show FOREIGN KEY (movie_show_id)
		REFERENCES movie_show (id)
);