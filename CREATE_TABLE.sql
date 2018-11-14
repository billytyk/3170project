USE db12;

DROP TABLE IF EXISTS Driver;

CREATE TABLE IF NOT EXISTS Driver(
	id integer primary key,
    name varchar(255) not null
	);

DESCRIBE Driver;

CREATE TABLE IF NOT EXISTS Vehicle(
	id integer primary key,
    model varchar(255) not null,
    model_year varchar(255) not null,
    seats integer not null
    );

