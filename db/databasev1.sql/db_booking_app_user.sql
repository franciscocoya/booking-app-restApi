-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_booking
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UNAME` varchar(20) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `EMAIL` varchar(80) NOT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `PASS` varchar(64) NOT NULL,
  `PROFILE_IMG` longblob,
  `ID_APP_CONFIGURATION` int DEFAULT NULL,
  `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_APP_USER` (`ID_APP_CONFIGURATION`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`ID_APP_CONFIGURATION`) REFERENCES `user_configuration` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'Samuel','Cooper','samuel.cooper@gmail.com','773893743 ','$2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO',NULL,1,'2022-05-20 17:55:28'),(2,'Dana','Moreno','dana.moreno@gmail.com','680745322','$2a$12$QUek6cYr28DOEyiCElA95e0z6DzygeYL7ouf70.AA541Wh4LJPyfu',NULL,1,'2022-05-20 17:55:28'),(3,'Ardalion','Meza Ocampo','ardalion.mezao@gmail.com','720671778','$2a$12$jIDE.Hvw7itNBQwv6zxCWebjpEqoalGvN3jTfvTnXplpCjh4Xw/v2',NULL,2,'2022-05-20 17:55:28'),(4,'Marta','Soto Pérez','marta.sotop@gmail.com','664889909','$2a$12$hkP/e2Y2VqkTwq3Dg0sQDOCo950lxE/zMap5Joq5YdBxDNCxX4zYu',NULL,1,'2022-05-20 17:55:28'),(5,'Antonio','Rodríguez Fernández','antonio.rodfer@gmail.com','757791565','$2a$12$v3JFeMwVSLtQg3WsbNVIHe67ecIPqpdra.YK5dQiAVThXxIH8M55C',NULL,1,'2022-05-20 17:55:28'),(6,'Marina','Riojas Sarabia','marina22.rodsar@gmail.com','648773354','$2a$12$SrqfKU60SsBhPRIucQiKAOmNzTwWJeYXOrbuM2HMB0aOfd15pSC32',NULL,2,'2022-05-20 17:55:28'),(7,'administrator','administrator','admin@leoncamp.es','000000000','$2a$12$sOsCaNg4.OEwsF515ESzeeXoYASgruL8rMznrZi7hVx6OkjWegR2u',NULL,NULL,'2022-05-20 17:55:28');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 19:58:23
