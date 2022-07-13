-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 13, 2022 at 03:03 PM
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
-- Database: `Online_food_delivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `Item_name` varchar(25) NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`Item_name`, `quantity`) VALUES
('apollo fish', 1),
('chicken 65', 2),
('seekh kebab', 3);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `Item_name` varchar(15) NOT NULL,
  `price` int NOT NULL,
  `category` varchar(15) NOT NULL,
  `availability` varchar(45) NOT NULL,
  `details` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`Item_name`, `price`, `category`, `availability`, `details`) VALUES
('apollo fish', 550, 'seafood starter', 'available', 'fish'),
('chicken 65', 250, 'nonveg starter', 'available', 'chicken'),
('seekh kebab', 350, 'nonveg starter', 'available', 'minced meat');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Item_name` varchar(15) NOT NULL,
  `price` int NOT NULL,
  `delivery_partner_name` varchar(15) NOT NULL,
  `delivery_partner_phoneno` varchar(45) NOT NULL,
  `status` varchar(15) NOT NULL,
  `expected_delivery_time` datetime NOT NULL,
  `address` varchar(25) NOT NULL,
  `user_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Item_name`, `price`, `delivery_partner_name`, `delivery_partner_phoneno`, `status`, `expected_delivery_time`, `address`, `user_name`) VALUES
('apollo fish', 550, 'anas', '999000777', 'delivered', '2022-07-13 11:06:14', 'abc', 'nikita'),
('chicken 65', 250, 'aman', '999666999', 'on the way', '2022-07-13 11:06:14', 'abc', 'Radhika'),
('seekh kebab', 250, 'aryan', '888777666', 'delivered', '2022-07-11 07:08:44', 'wert', 'Radha');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `user_name` varchar(15) NOT NULL,
  `card_number` varchar(15) NOT NULL,
  `CVV` int NOT NULL,
  `expiry_date` varchar(45) NOT NULL,
  `payment_status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`user_name`, `card_number`, `CVV`, `expiry_date`, `payment_status`) VALUES
('nikita', '123455', 123, '03/23', 'paid'),
('Radha', '45678', 678, '03/24', 'payment not done'),
('Radhika', '67890', 890, '03/23', 'paid');

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE `signup` (
  `user_name` varchar(25) NOT NULL,
  `f_name` varchar(15) NOT NULL,
  `l_name` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `address` varchar(45) NOT NULL,
  `referral_code` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`user_name`, `f_name`, `l_name`, `email`, `phone_no`, `address`, `referral_code`) VALUES
('Radhika', 'radhika', 'gupta', 'tyu@xyz.com', '888999777', 'wert', 'sdf'),
('Radha', 'radha', 'khanna', 'dcc@xyz.com', '9898889', 'nmjkl', 'asd'),
('nikita', 'nikita', 'singh', 'abc@xyz.com', '9898989', 'abc', 'aaa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`Item_name`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`Item_name`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Item_name`,`address`),
  ADD KEY `user_name` (`user_name`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`user_name`,`card_number`);

--
-- Indexes for table `signup`
--
ALTER TABLE `signup`
  ADD PRIMARY KEY (`phone_no`),
  ADD KEY `user_name` (`user_name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`Item_name`) REFERENCES `cart` (`Item_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`Item_name`) REFERENCES `orders` (`Item_name`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `payment` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `signup`
--
ALTER TABLE `signup`
  ADD CONSTRAINT `signup_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `payment` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
