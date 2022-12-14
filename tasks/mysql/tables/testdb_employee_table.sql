-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee_table`
--

DROP TABLE IF EXISTS `employee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_table` (
  `emp_id` int NOT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `immediate_head_id` int DEFAULT NULL,
  `dept_id` int DEFAULT NULL,
  `insurance_id` int DEFAULT NULL,
  `employee_info` int DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `dept_id` (`dept_id`),
  KEY `insurance_id` (`insurance_id`),
  KEY `employee_info` (`employee_info`),
  CONSTRAINT `employee_table_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department_table` (`dept_id`),
  CONSTRAINT `employee_table_ibfk_2` FOREIGN KEY (`insurance_id`) REFERENCES `insurance_table` (`insurance_id`),
  CONSTRAINT `employee_table_ibfk_3` FOREIGN KEY (`employee_info`) REFERENCES `employee_info_table` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_table`
--

LOCK TABLES `employee_table` WRITE;
/*!40000 ALTER TABLE `employee_table` DISABLE KEYS */;
INSERT INTO `employee_table` VALUES (97760,'Harikrishna Annam',97784,1,2,5),(97764,'Dheeraj Vishwakarma',97760,1,1,4),(97765,'C Vaibhav',97760,1,2,4),(97784,'Sharma T',NULL,1,1,6),(117672,'Nishit Kumar',97760,2,3,3),(117673,'Bipin Oraon',97764,1,1,2),(117674,'Nikhil Samudrala',97764,3,2,2),(117675,'Abhishek Gupta',97765,1,1,1),(117676,'Fardeen Khan',97760,2,3,2),(117677,'Abhishek MV',97765,1,2,1);
/*!40000 ALTER TABLE `employee_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 10:40:32
