insert into Users (name,email,date_of_birth) values
('John Doe', 'john.doe@example.com', '1990-04-12'),
('Jane Smith', 'jane.smith@example.com', '1985-09-30'),
('Mike Ross', 'mike.ross@example.com', '1992-12-01'),
('Rachel Green', 'rachel.green@example.com', '1988-05-17'),
('Tom Hardy', 'tom.hardy@example.com', '1995-06-25')


insert into Movies (title,genre,duration_minutes) values
('Inception', 'Sci-Fi', 148),
('The Godfather', 'Crime', 175),
('Interstellar', 'Sci-Fi', 169),
('Titanic', 'Romance', 195),
('The Dark Knight', 'Action', 152)


insert into WatchHistory (user_id,movie_id,watch_date) values
(1, 1, '2025-07-01'),
(2, 2, '2025-07-02'),
(1, 3, '2025-07-03'),
(3, 1, '2025-07-04'),
(4, 5, '2025-07-05')


insert into Directors (name,email) values
('Christopher Nolan', 'nolan@example.com'),
('Francis Ford Coppola', 'coppola@example.com'),
('James Cameron', 'cameron@example.com'),
('Steven Spielberg', 'spielberg@example.com'),
('Martin Scorsese', 'scorsese@example.com')

insert into MovieDirectors (director_id,movie_id) values
(1, 1), 
(2, 2),  
(1, 3),  
(3, 4), 
(1, 5)  

