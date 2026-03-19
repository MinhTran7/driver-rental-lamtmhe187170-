CREATE DATABASE  IF NOT EXISTS `driver_rental` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `driver_rental`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: driver_rental
-- ------------------------------------------------------
-- Server version	9.6.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '90932b7b-22b4-11f1-b741-40c2ba4f533b:1-41';

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(150) NOT NULL,
  `message` text NOT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_notifications_user` (`user_id`),
  CONSTRAINT `fk_notifications_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,1,'Welcome Admin','Welcome to driver rental system',0,'2026-03-18 12:27:48'),(2,2,'Driver Notice','Your account has been created by admin',0,'2026-03-18 12:27:48'),(3,3,'Customer Notice','Thank you for registering',0,'2026-03-18 12:27:48'),(4,4,'System Notification','This is a test notification',0,'2026-03-18 19:06:44'),(5,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống',0,'2026-03-18 19:18:13'),(6,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống 123',0,'2026-03-18 19:21:56'),(7,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống 123',0,'2026-03-18 19:22:03'),(8,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 19:37:42'),(9,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 20:30:54'),(10,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 20:45:06'),(11,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 20:53:31'),(12,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 21:20:03'),(13,4,'Đăng nhập thành công','Bạn vừa đăng nhập vào hệ thống Rendtal Driver',0,'2026-03-18 21:20:17');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pending_registrations`
--

DROP TABLE IF EXISTS `pending_registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pending_registrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `accepted_terms` tinyint(1) NOT NULL DEFAULT '0',
  `verify_token` varchar(255) NOT NULL,
  `status` enum('PENDING','VERIFIED','EXPIRED') NOT NULL DEFAULT 'PENDING',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `verify_token` (`verify_token`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pending_registrations`
--

LOCK TABLES `pending_registrations` WRITE;
/*!40000 ALTER TABLE `pending_registrations` DISABLE KEYS */;
INSERT INTO `pending_registrations` VALUES (3,'Lam Minh','he187170tranminhlam@gmail.com','123456','','',1,'199fad22-cb97-49ef-83c8-8f7fc8e1c8ae','VERIFIED','2026-03-18 16:58:54');
/*!40000 ALTER TABLE `pending_registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_rules`
--

DROP TABLE IF EXISTS `pricing_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_rules` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(100) NOT NULL,
  `description` text,
  `base_price` decimal(12,2) NOT NULL DEFAULT '0.00',
  `price_per_km` decimal(12,2) NOT NULL DEFAULT '0.00',
  `price_per_hour` decimal(12,2) NOT NULL DEFAULT '0.00',
  `active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_rules`
--

LOCK TABLES `pricing_rules` WRITE;
/*!40000 ALTER TABLE `pricing_rules` DISABLE KEYS */;
INSERT INTO `pricing_rules` VALUES (1,'Standard Pricingging','Default pricing rule for normal bookings',50000.00,12000.00,80000.00,1,'2026-03-18 12:27:55','2026-03-18 19:38:15'),(2,'Night Pricing','Applied for night trips',70020.00,15000.00,100000.00,1,'2026-03-18 12:27:55','2026-03-18 20:45:24');
/*!40000 ALTER TABLE `pricing_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `discount_percent` decimal(5,2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotions`
--

LOCK TABLES `promotions` WRITE;
/*!40000 ALTER TABLE `promotions` DISABLE KEYS */;
INSERT INTO `promotions` VALUES (1,'WELCOME10','Welcome Promotion','Discount for new customers',16.00,'2026-03-01','2026-12-31',1,'2026-03-18 12:28:20','2026-03-18 20:53:43'),(2,'SUMMER15','Summer Promotion','Seasonal promotion',15.00,'2026-06-01','2026-08-31',1,'2026-03-18 12:28:20','2026-03-18 12:28:20'),(3,'SALE10','Khuyến mãi tháng 3','Giảm 10 phần trăm',10.00,'2026-03-01','2026-03-31',1,'2026-03-18 20:26:43','2026-03-18 20:26:43');
/*!40000 ALTER TABLE `promotions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` enum('CUSTOMER','DRIVER','ADMIN') NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'System Admin','admin@gmail.com','123456','0900000001','Ha Noi','ADMIN','ACTIVE','2026-03-18 12:26:58','2026-03-18 12:26:58'),(2,'Driver One','driver1@gmail.com','123456','0900000002','Ha Noi','DRIVER','ACTIVE','2026-03-18 12:27:09','2026-03-18 12:27:09'),(3,'Customer One','customer1@gmail.com','123456','0900000003','Ha Noi','CUSTOMER','ACTIVE','2026-03-18 12:27:26','2026-03-18 12:27:26'),(4,'Minh Trần','he187170tranminhlam@gmail.com','123','123456789','','CUSTOMER','ACTIVE','2026-03-18 18:15:24','2026-03-18 21:19:57');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-19  5:23:40
