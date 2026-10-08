-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_db
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `room_id` int NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `guests` int NOT NULL,
  `payment_status` varchar(20) DEFAULT 'Pending',
  PRIMARY KEY (`booking_id`),
  KEY `customer_id` (`customer_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (10,10,1,'2026-10-01','2026-10-10',1,'Pending');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'VICKY','8790654321','vicky23@gmail.com','akshaya appartment guduvancheri'),(3,'priyan','9898765444','priyan@gmail.com','chennai'),(7,'aravind','9988998822','aravind@gmail.com','tambaram'),(9,'vishnu','9009008899','vishnu@gmail.com','avadi'),(10,'suve','8863455454','suve@gmail.com','krishnagiri');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_services`
--

DROP TABLE IF EXISTS `hotel_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `meal_type` varchar(20) NOT NULL,
  `cuisine` varchar(30) NOT NULL,
  `food_item` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `service_date` date DEFAULT (curdate()),
  PRIMARY KEY (`service_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `hotel_services_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_services`
--

LOCK TABLES `hotel_services` WRITE;
/*!40000 ALTER TABLE `hotel_services` DISABLE KEYS */;
INSERT INTO `hotel_services` VALUES (1,7,'Lunch','North Indian','Naan + Paneer',2,440.00,'2026-09-30'),(2,8,'Lunch','Chinese','Manchurian',2,340.00,'2026-09-30'),(3,8,'Lunch','North Indian','Naan + Paneer',2,440.00,'2026-09-30'),(4,8,'Lunch','Continental','Pasta',1,250.00,'2026-09-30'),(5,10,'Lunch','North Indian','Naan + Paneer',3,660.00,'2026-09-30'),(7,10,'Breakfast','South Indian','Dosa',2,200.00,'2026-09-30'),(9,7,'Lunch','North Indian','Naan + Paneer',2,440.00,'2026-10-01'),(10,7,'Breakfast','Chinese','Noodles',2,320.00,'2026-10-01'),(11,7,'Dinner','Continental','Grilled Sandwich',2,400.00,'2026-10-01'),(12,7,'Dinner','Continental','Pizza',2,600.00,'2026-10-01');
/*!40000 ALTER TABLE `hotel_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_number` varchar(10) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `status` varchar(20) DEFAULT 'Available',
  `ac_type` varchar(20) DEFAULT 'AC',
  `wifi` varchar(10) DEFAULT 'No',
  `breakfast` varchar(10) DEFAULT 'No',
  `beds` int DEFAULT '1',
  `description` varchar(300) DEFAULT NULL,
  `maintenance_comment` varchar(255) DEFAULT '',
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_number` (`room_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'101','Deluxe',1500.00,'Booked','Non-AC','No','No',1,'',''),(2,'102','Single',1500.00,'Available','AC','No','Yes',1,'',''),(3,'201','Double',2500.00,'Available','Non-AC','No','No',1,'',''),(4,'202','Double',2500.00,'Available','AC','No','No',1,NULL,''),(5,'301','Deluxe',3500.00,'Available','Non-AC','No','No',2,'',''),(6,'302','Deluxe',3500.00,'Available','AC','No','No',2,'',''),(7,'401','Suite',5500.00,'Available','AC','No','No',2,'',''),(8,'402','Suite',5500.00,'Available','AC','No','No',2,'',''),(10,'501','Suite',5500.00,'Maintenance','AC','No','No',2,'','AC MAINTENANCE'),(15,'502','Suite',5500.00,'Maintenance','AC','Yes','Yes',2,'AC MAINTENANCE','AC Maintenance');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `shift` varchar(50) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `id_proof_submitted` tinyint(1) NOT NULL,
  `id_proof_type` varchar(50) NOT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('1','hari','Male','9988998876','hari@gmail.com','Security Guard','Morning Shift (6 AM - 6 PM)',25000.00,1,'Aadhar Card');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-08 18:48:43
