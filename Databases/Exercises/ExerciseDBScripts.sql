Drop database if exists movieland;

Create database if not exists movieland;

Use movieland;

DROP TABLE IF EXISTS Credit;
DROP TABLE IF EXISTS Actor;
DROP TABLE IF EXISTS Movie;

-- Movies
CREATE TABLE Movie(
    MovieID     INT PRIMARY KEY AUTO_INCREMENT,
    Title       VARCHAR(200) NOT NULL,
    ReleaseDate DATE NOT NULL,
    Rating VARCHAR(5) NOT NULL,
    CONSTRAINT UC_Movie_Title_ReleaseDate UNIQUE (Title, ReleaseDate)
);

-- Actors
CREATE TABLE Actor(
    ActorID    INT PRIMARY KEY AUTO_INCREMENT,
    StageName  VARCHAR(150) NOT NULL,
    BirthDate  DATE NULL,
    CONSTRAINT UC_Actor_StageName UNIQUE (StageName)
);

-- Credits 
CREATE TABLE Credit(
    CreditID  INT PRIMARY KEY AUTO_INCREMENT,
    MovieID   INT NOT NULL,
    ActorID   INT NOT NULL,
    RoleName  VARCHAR(150) NOT NULL,
    CONSTRAINT FK_Credit_Movie_MovieID FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    CONSTRAINT FK_Credit_Actor_ActorID FOREIGN KEY (ActorID) REFERENCES Actor(ActorID),
    CONSTRAINT UC_Credit UNIQUE (MovieID, ActorID, RoleName)
);

-- Movies Data 
INSERT INTO Movie (Title, ReleaseDate, Rating) VALUES
('The Fast and the Furious', '2009-06-04', 'PG-13'),
('Wonder Woman',              '2017-06-02', 'PG-13'),
('Guardians of the Galaxy',   '2014-08-01', 'PG-13'),
('Encanto',                   '2021-11-24', 'PG');

-- Actors Data
INSERT INTO Actor (StageName) VALUES
('Vin Diesel'),
('Gal Gadot'),
('Chris Pine'),
('Chris Pratt'),
('Zoe Saldana'),
('Stephanie Beatriz'),
('John Leguizamo');

-- Credits Data
INSERT INTO Credit (MovieID, ActorID, RoleName) VALUES
(1, 1, 'Dominic Toretto'),
(1, 2, 'Gisele'),
(2, 2, 'Diana Prince / Wonder Woman'),
(2, 3, 'Steve Trevor'),
(3, 4, 'Peter Quill'),
(3, 1, 'Groot'),
(3, 5, 'Gamora'),
(4, 6, 'Mirabel'),
(4, 7, 'Bruno'); 

-- Adding a Genres column to Movie
ALTER TABLE Movie
ADD Genres VARCHAR(200) NOT NULL DEFAULT '';