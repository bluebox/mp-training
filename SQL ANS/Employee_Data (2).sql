-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 13, 2022 at 03:36 PM
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
-- Database: `Employee_Data`
--

-- --------------------------------------------------------

--
-- Table structure for table `Department_Table`
--

CREATE TABLE `Department_Table` (
  `dept_id` varchar(10) NOT NULL,
  `dept_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Department_Table`
--

INSERT INTO `Department_Table` (`dept_id`, `dept_name`) VALUES
('D001', 'Software'),
('D002', 'IT'),
('D003', 'Sales');

-- --------------------------------------------------------

--
-- Table structure for table `Employee_Info_Table`
--

CREATE TABLE `Employee_Info_Table` (
  `id` varchar(10) NOT NULL,
  `doj` date NOT NULL,
  `salary` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Employee_Info_Table`
--

INSERT INTO `Employee_Info_Table` (`id`, `doj`, `salary`) VALUES
('001', '2022-07-03', 10000),
('002', '2022-03-09', 13000),
('003', '2022-03-07', 23000),
('004', '2022-01-17', 210000),
('005', '2021-08-10', 12000000),
('006', '2021-02-08', 14000000),
('007', '2021-11-21', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `Employee_office_Table`
--

CREATE TABLE `Employee_office_Table` (
  `id` varchar(10) NOT NULL,
  `emp_id` varchar(10) NOT NULL,
  `office_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Employee_office_Table`
--

INSERT INTO `Employee_office_Table` (`id`, `emp_id`, `office_id`) VALUES
('001', 'O001', 'MED001'),
('002', 'O002', 'MED001'),
('003', 'O003', 'MED002'),
('004', 'O004', 'MED003'),
('005', 'O0010', 'MED003'),
('006', 'O0011', 'MED001'),
('007', 'O0012', 'MED002'),
('008', 'O0013', 'MED003');

-- --------------------------------------------------------

--
-- Table structure for table `Employee_Table`
--

CREATE TABLE `Employee_Table` (
  `emp_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) NOT NULL,
  `immediate_head_id` varchar(10) NOT NULL,
  `dept_id` varchar(10) NOT NULL,
  `insurance_id` varchar(10) NOT NULL,
  `employee_info` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Employee_Table`
--

INSERT INTO `Employee_Table` (`emp_id`, `name`, `immediate_head_id`, `dept_id`, `insurance_id`, `employee_info`) VALUES
('O001', 'Purnima', 'O0010', 'D001', 'I001', 'Software'),
('O0010', 'Hari', 'O00100', 'D002', 'I003', 'Software'),
('O00100', 'Sharma ', 'O00', 'D001', 'I002', 'IT'),
('O0011', 'Yash', 'O00100', 'D001', 'I003', 'Sales'),
('O0012', 'Dheeraj', 'O00100', 'D003', 'I003', 'Sales'),
('O0013', 'Vishwajeet', 'O0010', 'D002', 'I002', 'IT'),
('O0014', 'Samba', 'O00100', 'D001', 'I001', 'Software'),
('O0015', 'Sakshi', 'O00100', 'D002', 'I003', 'IT'),
('O002', 'Yatin', 'O0011', 'D001', 'I001', 'Software'),
('O003', 'Mansa', 'O0012', 'D002', 'I002', 'IT'),
('O004', 'Saithi', 'O0013', 'D002', 'I002', 'IT'),
('O005', 'Nikita', 'O0014', 'D003', 'I003', 'Sales');

-- --------------------------------------------------------

--
-- Table structure for table `Insurance_Table`
--

CREATE TABLE `Insurance_Table` (
  `insurance_id` varchar(10) NOT NULL,
  `insurance_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Insurance_Table`
--

INSERT INTO `Insurance_Table` (`insurance_id`, `insurance_name`) VALUES
('I001', 'LIC family full'),
('I002', 'Jivan Bima'),
('I003', 'LIC Small');

-- --------------------------------------------------------

--
-- Table structure for table `Office_Table`
--

CREATE TABLE `Office_Table` (
  `office_id` varchar(10) NOT NULL,
  `office_name` varchar(20) NOT NULL,
  `place` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Office_Table`
--

INSERT INTO `Office_Table` (`office_id`, `office_name`, `place`) VALUES
('MED001', 'Optival', 'Hitec City'),
('MED002', 'Medplus', 'Ameerpet'),
('MED003', 'Custom Furnish', 'Madhapur');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Department_Table`
--
ALTER TABLE `Department_Table`
  ADD PRIMARY KEY (`dept_id`);

--
-- Indexes for table `Employee_Info_Table`
--
ALTER TABLE `Employee_Info_Table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Employee_office_Table`
--
ALTER TABLE `Employee_office_Table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Employee_Table`
--
ALTER TABLE `Employee_Table`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `Insurance_Table`
--
ALTER TABLE `Insurance_Table`
  ADD PRIMARY KEY (`insurance_id`);

--
-- Indexes for table `Office_Table`
--
ALTER TABLE `Office_Table`
  ADD PRIMARY KEY (`office_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
