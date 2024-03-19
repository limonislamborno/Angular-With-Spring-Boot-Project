-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bankmanagement
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `aid` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) NOT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cell` varchar(255) DEFAULT NULL,
  `creating_date` date DEFAULT NULL,
  `current_balence` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nid` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `UK_kkyy3tr5399btuq307jibrjqm` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (3,'2330014','Current','Komorpur, Faridpur','01911-678100','2024-01-24',62698,'limonislamborno@gmail.com','Limon Islam Borno','Male','owner_image_1705990911500.jpg','K M Sayfullah','102486594257',_binary ''),(4,'2058616','Savings','Soriotpur','09878652825','2024-01-24',13800,'tyuty@gmail.com','Masud Rana','Male','owner_image_1706044790351.jpg','Hasan','65465475757567',_binary '\0'),(5,'3558257','Savings','Sherpur','017456925','2024-01-24',1500,'rajibahmedjhinuk@gmail.com','Rajib Al Ahmed',NULL,'owner_image_1706071104816.jpg','Jhinuk','102486594257',_binary ''),(6,'0462855','Current','Komorpur, Faridpur','01711648922','2024-01-02',500,'kacheasargolpobd@gmail.com','K M Sayfullah','Male','owner_image_1706079238695.jpg','iol;lkj','54456',_binary '\0');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit_transaction`
--

DROP TABLE IF EXISTS `deposit_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit_transaction` (
  `did` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `d_amount` int NOT NULL,
  `deposit_time` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`did`),
  KEY `FKthukjskme9pc05txu6yiky56y` (`customer_id`),
  CONSTRAINT `FKthukjskme9pc05txu6yiky56y` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit_transaction`
--

LOCK TABLES `deposit_transaction` WRITE;
/*!40000 ALTER TABLE `deposit_transaction` DISABLE KEYS */;
INSERT INTO `deposit_transaction` VALUES (1,'2058616','Savings',12000,'2024-01-24','Masud Rana',4),(2,'2330014','Current',500,'2024-01-24','Limon Islam Borno',3),(3,'2330014','Current',8500,'2024-01-24','Limon Islam Borno',3),(4,'2330014','Current',50000,'2024-02-10','Limon Islam Borno',3),(5,'2330014','Current',800,'2024-02-24','Limon Islam Borno',3),(6,'2330014','Current',900,'2024-02-24','Limon Islam Borno',3),(7,'2330014','Current',1500,'2024-02-24','Limon Islam Borno',3),(8,'2330014','Current',2500,'2024-02-24','Limon Islam Borno',3),(9,'2330014','Current',2500,'2024-02-24','Limon Islam Borno',3),(10,'3558257','Savings',1500,'2024-02-25','Rajib Al Ahmed',5);
/*!40000 ALTER TABLE `deposit_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dps_about`
--

DROP TABLE IF EXISTS `dps_about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dps_about` (
  `dpsid` bigint NOT NULL AUTO_INCREMENT,
  `dps_amount` bigint DEFAULT NULL,
  `dps_name` varchar(255) DEFAULT NULL,
  `dps_term` bigint DEFAULT NULL,
  `interest_rate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dpsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dps_about`
--

LOCK TABLES `dps_about` WRITE;
/*!40000 ALTER TABLE `dps_about` DISABLE KEYS */;
/*!40000 ALTER TABLE `dps_about` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dps_information`
--

DROP TABLE IF EXISTS `dps_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dps_information` (
  `dpsid` bigint NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `dps_amount` double NOT NULL,
  `dps_paid` double NOT NULL,
  `dps_term` int NOT NULL,
  `interest_rate` double NOT NULL,
  `total_all_amount` double NOT NULL,
  `total_amount` double NOT NULL,
  `total_interest` double NOT NULL,
  PRIMARY KEY (`dpsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dps_information`
--

LOCK TABLES `dps_information` WRITE;
/*!40000 ALTER TABLE `dps_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `dps_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeesssffff`
--

DROP TABLE IF EXISTS `employeesssffff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeesssffff` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeesssffff`
--

LOCK TABLES `employeesssffff` WRITE;
/*!40000 ALTER TABLE `employeesssffff` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeesssffff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_about`
--

DROP TABLE IF EXISTS `loan_about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_about` (
  `laid` bigint NOT NULL AUTO_INCREMENT,
  `interest_rate` varchar(255) DEFAULT NULL,
  `loan_amount` bigint DEFAULT NULL,
  `loan_term` bigint DEFAULT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`laid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_about`
--

LOCK TABLES `loan_about` WRITE;
/*!40000 ALTER TABLE `loan_about` DISABLE KEYS */;
INSERT INTO `loan_about` VALUES (1,'10%',50000,2,'PersonalLoan'),(2,'15%',100000,5,'PersonalLoan');
/*!40000 ALTER TABLE `loan_about` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_application`
--

DROP TABLE IF EXISTS `loan_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `due` double NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emi` double NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `interest_rate` double NOT NULL,
  `loan_amount` bigint DEFAULT NULL,
  `loan_term` bigint DEFAULT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  `permanent_address` varchar(255) DEFAULT NULL,
  `personal_number` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `present_address` varchar(255) DEFAULT NULL,
  `profession_type` varchar(255) DEFAULT NULL,
  `state_code` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `total_payable_amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_application`
--

LOCK TABLES `loan_application` WRITE;
/*!40000 ALTER TABLE `loan_application` DISABLE KEYS */;
INSERT INTO `loan_application` VALUES (2,'2330014','2024-01-06',0,'kacheasargolpobd@gmail.com',0,'Masud','male',0,50000,2,'PersonalLoan','Komorpur, Faridpur','01911678100','4500','Komorpur, Faridpur','privateJob','8566',_binary '\0',0),(3,'3558257','2024-01-06',0,'rajibahmedjhinuk@gmail.com',0,'Rajib Al Ahmed','male',0,100000,5,'PersonalLoan','Soriotpur ,Dhaka','01611678100','8500','Dhaka','privateJob','8566',_binary '\0',0);
/*!40000 ALTER TABLE `loan_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_information`
--

DROP TABLE IF EXISTS `loan_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_information` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `due` double NOT NULL,
  `emi` double NOT NULL,
  `loan_amount` double NOT NULL,
  `loan_term` int NOT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  `total_interest` double NOT NULL,
  `total_payable_amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_information`
--

LOCK TABLES `loan_information` WRITE;
/*!40000 ALTER TABLE `loan_information` DISABLE KEYS */;
INSERT INTO `loan_information` VALUES (1,'Masud','2330014',55373.91,2307.25,50000,2,'PersonalLoan',5373.91,55373.91),(2,'Masud','2330014',55373.91,2307.25,50000,2,'PersonalLoan',5373.91,55373.91);
/*!40000 ALTER TABLE `loan_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'xdvfgfdbgfd','ADMIN'),(2,'DDDD','USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g7im3j7f0g31yhl6qco2iboy5` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'c3e3308a-7648-4829-beb3-460876ece28b','2024-01-23 14:47:05.734000',2),(2,'f29d6ca0-f80f-4c18-8efa-2669138f243e','2024-01-23 20:17:49.463000',3),(3,'ff2b16f7-c557-49f3-8d67-ac70c560c134','2024-01-24 00:10:55.738000',4),(4,'ee4ecfb1-0bfc-4145-aebf-0a5c1a5ded4a','2024-01-24 00:15:56.723000',5),(5,'95a3c323-0adb-42c1-b5d9-eb3a8363746f','2024-01-24 00:29:19.096000',6),(6,'6b0d65a3-d86f-4b9e-91d0-18d8634b7bf8','2024-01-24 00:38:59.144000',7);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_history2`
--

DROP TABLE IF EXISTS `transaction_history2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_history2` (
  `id` bigint NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `attribute1` varchar(255) DEFAULT NULL,
  `attribute2` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `transaction_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history2`
--

LOCK TABLES `transaction_history2` WRITE;
/*!40000 ALTER TABLE `transaction_history2` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_history2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_view_model`
--

DROP TABLE IF EXISTS `transaction_view_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_view_model` (
  `id` bigint NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `attribute1` varchar(255) DEFAULT NULL,
  `attribute2` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `transaction_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_view_model`
--

LOCK TABLES `transaction_view_model` WRITE;
/*!40000 ALTER TABLE `transaction_view_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_view_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer_transaction`
--

DROP TABLE IF EXISTS `transfer_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer_transaction` (
  `tid` bigint NOT NULL AUTO_INCREMENT,
  `from_account_number` varchar(255) DEFAULT NULL,
  `from_first_name` varchar(255) DEFAULT NULL,
  `to_account_number` varchar(255) DEFAULT NULL,
  `to_first_name` varchar(255) DEFAULT NULL,
  `transfer_amount` int NOT NULL,
  `transfer_time` date DEFAULT NULL,
  `from_customer_id` bigint DEFAULT NULL,
  `to_customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `FK12ntb6lt0uhsgamdiuw1bsncs` (`from_customer_id`),
  KEY `FK7081npjq84fof4tivr6vm2gxv` (`to_customer_id`),
  CONSTRAINT `FK12ntb6lt0uhsgamdiuw1bsncs` FOREIGN KEY (`from_customer_id`) REFERENCES `customer` (`aid`),
  CONSTRAINT `FK7081npjq84fof4tivr6vm2gxv` FOREIGN KEY (`to_customer_id`) REFERENCES `customer` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_transaction`
--

LOCK TABLES `transfer_transaction` WRITE;
/*!40000 ALTER TABLE `transfer_transaction` DISABLE KEYS */;
INSERT INTO `transfer_transaction` VALUES (1,'2058616','Masud Rana','2330014','Limon Islam Borno',500,'2024-01-24',NULL,NULL),(2,'2330014','Limon Islam Borno','2330014','Limon Islam Borno',500,'2024-01-24',NULL,NULL),(3,'2330014','Limon Islam Borno','2058616','Masud Rana',500,'2024-02-22',NULL,NULL),(4,'2330014','Limon Islam Borno','2058616','Masud Rana',1800,'2024-02-24',NULL,NULL);
/*!40000 ALTER TABLE `transfer_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cell_no` varchar(255) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_enable` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'142556456',NULL,'limonislamborno@gmail.com',NULL,'Limon Islam Brono_1705999625584.jpg',_binary '\0','Limon Islam Brono','$2a$10$QwcksCXF0mrYO2GbLnPTwOvYEJQQ8WpYzICMeR.GSlXL2joG3.uMO'),(3,'01511678100',NULL,'kacheasargolpobd@gmail.com',NULL,'Rakib Khan_1706019469342.jpg',_binary '\0','Rakib Khan','$2a$10$0.0lTYE4djk9MHHLjvU2C.qOufyW1L8tX5ywiE5yrmXr/SWOkZXmi'),(4,'01511678100',NULL,'idbstudentbd@gmail.com',NULL,'Sabbir Hasan Chowdhury_1706033455621.jpg',_binary '\0','Sabbir Hasan Chowdhury','$2a$10$AyiRmKQUUk1krQSReq2Y1uWvRJqDA1JMljiWHdbJ6pgTSOPdO3Gvm'),(5,'01511678100',NULL,'samaptinandi69@gmail.com',NULL,'Samapti Nandi_1706033756600.jpg',_binary '\0','Samapti Nandi','$2a$10$TUdy2uU2JfqD5DJYFiDepuladuIOjwxh0y4lp6a0ShTXnr.5PyYhC'),(6,'01511678100',NULL,'sumayaislamrusha@gmail.com',NULL,'Tonmoy Ahmed_1706034558972.jpg',_binary '\0','Tonmoy Ahmed','$2a$10$/iZ04H0G47K2N7bIw6T3Te1lHlsT2aqch95ocJW.J7zKQg6n3hJWq'),(7,'01511678100',NULL,'sayfullah3278@gmail.com',NULL,'K M Sayfullah_1706035139024.jpg',_binary '\0','K M Sayfullah','$2a$10$2xDRpcq0ZcFL5BJPps6uYuOI2zBX8vRoXVV33CF/3XjXFu3v6uU4W');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (2,1),(4,1),(5,1),(6,1),(3,2),(7,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdraw_transaction`
--

DROP TABLE IF EXISTS `withdraw_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withdraw_transaction` (
  `wid` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `w_amount` int NOT NULL,
  `withdraw_time` date DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wid`),
  KEY `FKabpe38xvox3hrr7ot7y96ecgq` (`customer_id`),
  CONSTRAINT `FKabpe38xvox3hrr7ot7y96ecgq` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw_transaction`
--

LOCK TABLES `withdraw_transaction` WRITE;
/*!40000 ALTER TABLE `withdraw_transaction` DISABLE KEYS */;
INSERT INTO `withdraw_transaction` VALUES (1,'2058616','Savings','Masud Rana',500,'2024-01-24',4),(2,'2330014','Current','Limon Islam Borno',500,'2024-02-24',3),(3,'2330014','Current','Limon Islam Borno',602,'2024-02-24',3),(4,'2330014','Current','Limon Islam Borno',900,'2024-02-24',3),(5,'2330014','Current','Limon Islam Borno',1200,'2024-02-24',3),(6,'3558257','Savings','Rajib Al Ahmed',500,'2024-02-25',5);
/*!40000 ALTER TABLE `withdraw_transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-25 13:25:56
