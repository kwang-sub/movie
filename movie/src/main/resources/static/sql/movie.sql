-- movie.genre definition

CREATE TABLE `genre` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- movie.production_company definition

CREATE TABLE `production_company` (
  `production_company_id` int(11) NOT NULL AUTO_INCREMENT,
  `logo_path` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `origin_country` varchar(255) NOT NULL,
  PRIMARY KEY (`production_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- movie.production_country definition

CREATE TABLE `production_country` (
  `production_country_id` int(11) NOT NULL AUTO_INCREMENT,
  `iso_3166_1` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`production_country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- movie.spoken_language definition

CREATE TABLE `spoken_language` (
  `spoken_language_id` int(11) NOT NULL AUTO_INCREMENT,
  `iso_639_1` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`spoken_language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- movie.movie definition

CREATE TABLE `movie` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `adult` varchar(255) NOT NULL,
  `backdrop_path` varchar(255) DEFAULT NULL,
  `belongs_to_collection` varchar(255) DEFAULT NULL,
  `budget` int(11) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `imdb_id` varchar(255) DEFAULT NULL,
  `original_language` varchar(255) DEFAULT NULL,
  `original_title` varchar(255) DEFAULT NULL,
  `overview` varchar(255) DEFAULT NULL,
  `popularity` int(11) DEFAULT NULL,
  `poster_path` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `revenue` int(11) DEFAULT NULL,
  `runtime` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tagline` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video` varchar(255) NOT NULL,
  `vote_average` int(11) DEFAULT NULL,
  `vote_count` int(11) DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  `production_company_id` int(11) DEFAULT NULL,
  `production_country_id` int(11) DEFAULT NULL,
  `spoken_language_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `FK2ggat6246891h4goynp4h9lk5` (`genre_id`),
  KEY `FK5q43c9ttmc70n54e3sp6jq252` (`production_company_id`),
  KEY `FK24hyxijqwalt0smtvpjivwdi8` (`production_country_id`),
  KEY `FK4uaxms9a0hmrpcqppl7v4nfx6` (`spoken_language_id`),
  CONSTRAINT `FK24hyxijqwalt0smtvpjivwdi8` FOREIGN KEY (`production_country_id`) REFERENCES `production_country` (`production_country_id`),
  CONSTRAINT `FK2ggat6246891h4goynp4h9lk5` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `FK4uaxms9a0hmrpcqppl7v4nfx6` FOREIGN KEY (`spoken_language_id`) REFERENCES `spoken_language` (`spoken_language_id`),
  CONSTRAINT `FK5q43c9ttmc70n54e3sp6jq252` FOREIGN KEY (`production_company_id`) REFERENCES `production_company` (`production_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb3;


insert into genre (name) value("공포");
insert into genre (name) value("액션");
insert into genre (name) value("코미디");
commit;

insert into production_company (logo_path, name, origin_country) value("DUMMY DATA 11", "DUMMY DATA11", "DUMMY DATA11");
insert into production_company (logo_path, name, origin_country) value("DUMMY DATA 22", "DUMMY DATA22", "DUMMY DATA22");
insert into production_company (logo_path, name, origin_country) value("DUMMY DATA 33", "DUMMY DATA33", "DUMMY DATA33");
commit;

insert into production_country (iso_3166_1, name) value("DUMMY DATA 11", "DUMMY DATA 11");
insert into production_country (iso_3166_1, name) value("DUMMY DATA 22", "DUMMY DATA 22");
insert into production_country (iso_3166_1, name) value("DUMMY DATA 33", "DUMMY DATA 33");
commit;

insert into spoken_language (iso_639_1, name) value("DUMMY DATA 11", "DUMMY DATA 11");
insert into spoken_language (iso_639_1, name) value("DUMMY DATA 22", "DUMMY DATA 22");
insert into spoken_language (iso_639_1, name) value("DUMMY DATA 33", "DUMMY DATA 33");
commit;