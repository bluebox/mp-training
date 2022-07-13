-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 13, 2022 at 04:29 PM
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
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `order_id` varchar(10) NOT NULL,
  `product_id` varchar(10) NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_id`, `product_id`, `quantity`) VALUES
(1, 'a11', 'a101', 1),
(2, 'a11', 'a102', 2),
(3, 'a11', 'a103', 4),
(4, 'a12', 'a101', 2),
(5, 'a13', 'a103', 4);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `detail_id` varchar(10) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `total` int NOT NULL,
  `payment_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`detail_id`, `user_id`, `total`, `payment_id`) VALUES
('aa110', '1001', 170000, 100320),
('aa120', '1002', 20000, 100330),
('aa130', '1003', 120000, 100340);

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `detail_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_status`
--

INSERT INTO `order_status` (`detail_id`, `order_status`) VALUES
('aa110', 'delivered'),
('aa120', 'out for delivery'),
('aa130', 'packed');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(10) NOT NULL,
  `name` text NOT NULL,
  `desc` varchar(255) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `category_id` varchar(10) NOT NULL,
  `price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `desc`, `sku`, `category_id`, `price`) VALUES
('a101', 'Iphone 11', 'Its a very expensive phone', 'bk101', 'AP101', 10000),
('a102', 'Iphone 12', 'Its a very classic phone', 'bk102', 'AP102', 20000),
('a103', 'Iphone 13', 'Its a very futuristic phone', 'bk101', 'AP101', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `category_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` text NOT NULL,
  `desc` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`category_id`, `name`, `desc`, `created_at`) VALUES
('AP101', 'Phone', 'use for using whatsapp', '2022-07-13 14:25:19'),
('AP102', 'Electronics', 'use electricity here!!', '2022-07-13 14:25:42');

-- --------------------------------------------------------

--
-- Table structure for table `shopping_session`
--

CREATE TABLE `shopping_session` (
  `order_id` varchar(10) NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shopping_session`
--

INSERT INTO `shopping_session` (`order_id`, `user_id`, `created_at`) VALUES
('a11', 1001, '2022-07-13 14:20:22'),
('a12', 1002, '2022-07-13 14:20:44'),
('a13', 1003, '2022-07-13 14:20:44'),
('a14', 1004, '2022-07-13 14:20:44');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `f_name` text NOT NULL,
  `l_name` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `f_name`, `l_name`, `created_at`) VALUES
(1001, 'yatin123', 'hello123', 'yatin', 'vohra', '2022-07-11 12:38:29'),
(1002, 'sak9911', 'hello123', 'saksham', 'sinha', '2022-07-11 12:40:11'),
(1003, 'purnima123', 'hello123', 'purnima', 'agarwal', '2022-07-11 12:40:11'),
(1004, 'sakshi123', 'hello123', 'sakshi', 'gyani', '2022-07-11 12:40:11'),
(1005, 'irfan123', 'hello123', 'irfan', 'taneja', '2022-07-11 12:40:11');

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE `user_address` (
  `user_id` int NOT NULL,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) NOT NULL,
  `city` text NOT NULL,
  `postal_code` int NOT NULL,
  `country` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_address`
--

INSERT INTO `user_address` (`user_id`, `address_line1`, `address_line2`, `city`, `postal_code`, `country`) VALUES
(1001, 'house number 425', 'house number 300', 'faridabad', 121003, 'India'),
(1002, 'hno. 400', 'hno. 440', 'karnataka', 110050, 'India'),
(1003, 'hno. 2121', 'hno. 3443', 'Banglore', 110052, 'India'),
(1004, 'hno. 100', 'hno. 4404', 'Chennai', 110050, 'India'),
(1005, 'hno. 400', 'hno. 440', 'Tamil nadu', 11200, 'India');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
  ADD PRIMARY KEY (`detail_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `shopping_session`
--
ALTER TABLE `shopping_session`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_address`
--
ALTER TABLE `user_address`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
