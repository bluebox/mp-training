-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 13, 2022 at 03:02 PM
-- Server version: 8.0.29-0ubuntu0.20.04.3
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Railway_Management_System`
--

-- --------------------------------------------------------

--
-- Table structure for table `Passenger_Info`
--

CREATE TABLE `Passenger_Info` (
  `pass_id` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `age` int NOT NULL,
  `gender` varchar(15) NOT NULL,
  `pnr_no` varchar(25) NOT NULL,
  `seat_no` varchar(5) NOT NULL,
  `coach_no` varchar(10) NOT NULL,
  `reservation_status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Passenger_Info`
--

INSERT INTO `Passenger_Info` (`pass_id`, `name`, `age`, `gender`, `pnr_no`, `seat_no`, `coach_no`, `reservation_status`) VALUES
('poo@123', 'Purnima agarwal', 22, 'female', '123436', '1', 'B2', 'confirmed'),
('saikhirfan', 'irfan saikh', 25, 'male', '123456', '56', 'B1', 'confirmed');

-- --------------------------------------------------------

--
-- Table structure for table `Station_Info`
--

CREATE TABLE `Station_Info` (
  `name` varchar(30) NOT NULL,
  `train_no` int NOT NULL,
  `arrival_time` datetime NOT NULL,
  `hault` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Station_Info`
--

INSERT INTO `Station_Info` (`name`, `train_no`, `arrival_time`, `hault`) VALUES
('kolkata jnc', 14429, '2022-08-18 18:28:11', '20mins'),
('hyderabad', 14536, '2022-08-11 09:10:00', '10mins');

-- --------------------------------------------------------

--
-- Table structure for table `Ticket_Info`
--

CREATE TABLE `Ticket_Info` (
  `ticket_id` int NOT NULL,
  `train_no` int NOT NULL,
  `pnr_no` varchar(25) NOT NULL,
  `booked_user` varchar(25) NOT NULL,
  `status` varchar(15) NOT NULL,
  `no_of_passengers` int NOT NULL,
  `user_id` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Ticket_Info`
--

INSERT INTO `Ticket_Info` (`ticket_id`, `train_no`, `pnr_no`, `booked_user`, `status`, `no_of_passengers`, `user_id`) VALUES
(19936, 14536, '123436', 'Purnima agarwal', 'confirmed', 1, 'poo@123'),
(19939, 14429, '123456', 'irfan saikh', 'confirmed', 1, 'saikhirfan');

-- --------------------------------------------------------

--
-- Table structure for table `Train_Info`
--

CREATE TABLE `Train_Info` (
  `train_no` int NOT NULL,
  `train_name` varchar(30) NOT NULL,
  `source` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
  `arrival_time` datetime NOT NULL,
  `departure_time` datetime NOT NULL,
  `availability_of_seats` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Train_Info`
--

INSERT INTO `Train_Info` (`train_no`, `train_name`, `source`, `destination`, `arrival_time`, `departure_time`, `availability_of_seats`) VALUES
(14429, 'gareebrath', 'kolkata', 'lokmanya tilak', '2022-08-18 18:28:11', '2022-08-18 18:48:11', 123),
(14536, 'rajdhani exp', 'hyderabad', 'new delhi', '2022-08-11 09:10:00', '2022-08-11 09:20:00', 256);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `age` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `adhar_no` int NOT NULL,
  `phone_number` int NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pincode` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`user_id`, `password`, `first_name`, `last_name`, `gender`, `age`, `email`, `adhar_no`, `phone_number`, `city`, `state`, `pincode`) VALUES
('Poo@123', 'agar@56', 'purnima', 'agarwal', 'female', 22, 'poo@gmail.com', 213456789, 1324567891, 'kolkata', 'west bengal', 500102),
('saikhirfan', 'irf@123', 'irfan', 'saikh', 'male', 25, 'md.irfan@gmail.com', 123456789, 1234567891, 'sitamarhi', 'bihar', 843302),
('sakshi@567', 'Dub@987', 'sakshi', 'dubey', 'female', 24, 'sak@gmail.com', 143256789, 2113456789, 'lucknow', 'uttarpradesh', 700021),
('yat@119', 'Voh@876', 'yatin', 'vohra', 'male', 20, 'yat@gmail.com', 111234789, 1552348796, 'faridabad', 'haryana', 600012);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Passenger_Info`
--
ALTER TABLE `Passenger_Info`
  ADD PRIMARY KEY (`pass_id`);

--
-- Indexes for table `Station_Info`
--
ALTER TABLE `Station_Info`
  ADD PRIMARY KEY (`train_no`);

--
-- Indexes for table `Ticket_Info`
--
ALTER TABLE `Ticket_Info`
  ADD PRIMARY KEY (`ticket_id`,`pnr_no`);

--
-- Indexes for table `Train_Info`
--
ALTER TABLE `Train_Info`
  ADD PRIMARY KEY (`train_no`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_id`,`email`,`adhar_no`,`phone_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
