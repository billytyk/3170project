CREATE TABLE IF NOT EXISTS Driver(
	id integer primary key,
    name varchar(30) not null,
    vid varchar(6) not null
	);


CREATE TABLE IF NOT EXISTS Vehicle(
	id varchar(6) primary key,
    model varchar(30) not null,
    model_year integer(4) not null,
    seats integer not null
    );

CREATE TABLE IF NOT EXISTS Passenger(
	id integer primary key,
    name varchar(30) not null
    );

CREATE TABLE IF NOT EXISTS Trip(
	id integer primary key,
    did integer,
    pid integer,
    start DATETIME not null,
    end DATETIME,
    fee integer,
    rating float
    );

SHOW TABLES;
