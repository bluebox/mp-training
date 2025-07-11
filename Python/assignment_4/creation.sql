create database Movie
use Movie

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    date_of_birth DATE
);


CREATE TABLE Movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    genre VARCHAR(50),
    duration_minutes INT,
    current_views INT DEFAULT 0
);

CREATE TABLE WatchHistory (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    movie_id INT,
    watch_date DATE DEFAULT (current_date),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) on delete cascade,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) on delete cascade
);

CREATE TABLE Directors (
    director_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE MovieDirectors (
    director_id INT,
    movie_id INT,
    PRIMARY KEY (director_id, movie_id),
    FOREIGN KEY (director_id) REFERENCES Directors(director_id) on delete cascade,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) on delete cascade
);


