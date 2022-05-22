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
-- Table structure for table `accomodation_review`
--

DROP TABLE IF EXISTS `accomodation_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accomodation_review` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CONTENT` mediumtext,
  `STARS` int DEFAULT '1',
  `ID_USER` int NOT NULL,
  `ID_ACC` varchar(20) NOT NULL,
  `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_ACCOMODATION_REVIEW_USER` (`ID_USER`),
  KEY `FK_ACCOMODATION_REVIEW_ACC` (`ID_ACC`),
  CONSTRAINT `FK_ACCOMODATION_REVIEW_ACC` FOREIGN KEY (`ID_ACC`) REFERENCES `accomodation` (`REG_NUM`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ACCOMODATION_REVIEW_USER` FOREIGN KEY (`ID_USER`) REFERENCES `app_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CK_ACCOMODATION_REVIEW_STARS` CHECK ((`STARS` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation_review`
--

LOCK TABLES `accomodation_review` WRITE;
/*!40000 ALTER TABLE `accomodation_review` DISABLE KEYS */;
INSERT INTO `accomodation_review` VALUES (1,'El alojamiento está como en las fotos, hemos pasado unos días increíbles',4,2,'0001234A','2022-05-20 17:55:29'),(2,'Una estancia muy tranquila para disfrutar de unos días de vacaciones con todo detalle',5,1,'0005678B','2022-05-20 17:55:29'),(3,'Samuel cuida los pequeños detalles y nos recibió con un aperitivo, repetiremos !!',5,4,'0001234A','2022-05-20 17:55:29');
/*!40000 ALTER TABLE `accomodation_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 19:58:24
