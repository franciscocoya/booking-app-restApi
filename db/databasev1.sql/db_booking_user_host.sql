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
-- Table structure for table `user_host`
--

DROP TABLE IF EXISTS `user_host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_host` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DNI` varchar(9) NOT NULL,
  `BIO` mediumtext,
  `IS_DNI_VERIFIED` tinyint(1) DEFAULT '0',
  `IS_EMAIL_VERIFIED` tinyint(1) DEFAULT '0',
  `IS_PHONE_VERIFIED` tinyint(1) DEFAULT '0',
  `IS_VERIFIED` tinyint(1) DEFAULT '0',
  `DIRECTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_HOST_USER` FOREIGN KEY (`ID`) REFERENCES `app_user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_host`
--

LOCK TABLES `user_host` WRITE;
/*!40000 ALTER TABLE `user_host` DISABLE KEYS */;
INSERT INTO `user_host` VALUES (1,'15412808W','¡Me encanta viajar! Por ello, ofrezco no sólo un hogar en Granada a la gente que viene de fuera, sino que me encantará aconsejarles sobre los sitios más bonitos para pasear, mis bares o restaurantes favoritos, las vistas más bonitas de la ciudad,...',0,0,0,0,'C/ Canónigo Valiño, 28'),(3,'14055845Q','Vivo en León con mi familia en una zona muy tranquila y muy cerca del centro de la ciudad (10min).',0,0,0,0,'Avda/ Mariano Andrés, 120'),(4,'10081139D',NULL,0,0,0,0,'Avda/ de Europa 2');
/*!40000 ALTER TABLE `user_host` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 19:58:21
