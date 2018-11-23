CREATE TABLE IF NOT EXISTS Vehicle(
	id varchar(6) UNSIGNED NOT NULL,
    model varchar(30) not null,
    model_year integer(4) unsigned not null,
    seats integer unsigned not null,
    PRIMARY KEY(id)
    );

CREATE TABLE IF NOT EXISTS Driver(
	id integer UNSIGNED NOT NULL,
    name varchar(30) not null,
    vid varchar(6) not null,
    PRIMARY KEY(id),
    FOREIGN KEY(vid) REFERENCES Vehicle(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    UNIQUE INDEX `vid_UNIQUE` (vid ASC)
	);


CREATE TABLE IF NOT EXISTS Passenger(
	id integer UNSIGNED primary key,
    name varchar(30) not null
    );

CREATE TABLE IF NOT EXISTS Trip(
	id integer UNSIGNED primary key AUTO_INCREMENT,
    did integer unsigned,
    pid integer unsigned,
    start DATETIME not null,
    end DATETIME,
    fee integer unsigned,
    rating integer unsigned
    );

CREATE TABLE IF NOT EXISTS Request(
    id integer UNSIGNED primary key AUTO_INCREMENT,
    pid integer unsigned,
    taken integer unsigned,
    model_year integer(4) unsigned,
    model varchar(30),
    passengers integer unsigned
);


