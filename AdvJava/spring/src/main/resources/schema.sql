CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `age` varchar(3) NOT NULL,
  `pnum` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL
);