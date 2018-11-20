CREATE TABLE IF NOT EXISTS Vehicle(
	id varchar(6) NOT NULL,
    model varchar(30) not null,
    model_year integer(4) not null,
    seats integer not null,
    PRIMARY KEY(id)
    );

CREATE TABLE IF NOT EXISTS Driver(
	id integer NOT NULL,
    name varchar(30) not null,
    vid varchar(6) not null,
    PRIMARY KEY(id),
    FOREIGN KEY(vid) REFERENCES Vehicle(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    UNIQUE INDEX `vid_UNIQUE` (vid ASC)
	);


CREATE TABLE IF NOT EXISTS Passenger(
	id integer primary key,
    name varchar(30) not null
    );

CREATE TABLE IF NOT EXISTS Trip(
	id integer primary key AUTO_INCREMENT,
    did integer,
    pid integer,
    start DATETIME not null,
    end DATETIME,
    fee integer,
    rating float
    );

CREATE TABLE IF NOT EXISTS Request(
    id integer primary key AUTO_INCREMENT,
    taken integer,
    model_year integer(4),
    model varchar(30),
    passengers integer
);

