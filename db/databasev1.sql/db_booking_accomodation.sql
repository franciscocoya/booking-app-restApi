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
-- Table structure for table `accomodation`
--

DROP TABLE IF EXISTS `accomodation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accomodation` (
  `REG_NUM` varchar(20) NOT NULL,
  `ACC_DESCRIPTION` mediumtext NOT NULL,
  `BEDS` int DEFAULT NULL,
  `NUM_BATHROOMS` int DEFAULT NULL,
  `NUM_BEDROOMS` int DEFAULT NULL,
  `PRICE_PER_NIGHT` decimal(15,4) DEFAULT NULL,
  `GUESTS` int DEFAULT NULL,
  `AREA` decimal(15,4) NOT NULL,
  `ID_ACC_CATEGORY` int NOT NULL,
  `ID_ACC_LOCATION` int NOT NULL,
  `ID_USER_OWNER` int NOT NULL,
  `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`REG_NUM`),
  KEY `FK_ACCOMODATION_CATEGORY` (`ID_ACC_CATEGORY`),
  KEY `FK_ACCOMODATION_LOCATION` (`ID_ACC_LOCATION`),
  KEY `FK_ACCOMODATION_USER_HOST` (`ID_USER_OWNER`),
  CONSTRAINT `FK_ACCOMODATION_CATEGORY` FOREIGN KEY (`ID_ACC_CATEGORY`) REFERENCES `accomodation_category` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ACCOMODATION_LOCATION` FOREIGN KEY (`ID_ACC_LOCATION`) REFERENCES `accomodation_location` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ACCOMODATION_USER_HOST` FOREIGN KEY (`ID_USER_OWNER`) REFERENCES `user_host` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CK_AREA` CHECK ((`AREA` > 0)),
  CONSTRAINT `CK_BEDS` CHECK ((`BEDS` > 0)),
  CONSTRAINT `CK_NUM_BATHROOMS` CHECK ((`NUM_BATHROOMS` > 0)),
  CONSTRAINT `CK_NUM_BEDROOMS` CHECK ((`NUM_BEDROOMS` > 0)),
  CONSTRAINT `CK_PRICE` CHECK ((`PRICE_PER_NIGHT` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation`
--

LOCK TABLES `accomodation` WRITE;
/*!40000 ALTER TABLE `accomodation` DISABLE KEYS */;
INSERT INTO `accomodation` VALUES ('0001234A','El moderno apartamento cuenta con una habitación independiente con cama de matrimonio y tv. El salón tiene un sofá cama doble, tv y un agradable calefactor eléctrico led. Cocina totalmente equipada y utensilios básicos para cocinar. Baño amplio con ducha. Calefacción y wifi incluidos.\ncuenta con un dormitorio con cama doble y televisor. La sala de estar cuenta con un sofá cama para 2 personas, televisor y un cómodo calentador eléctrico led. Cocina totalmente equipada. El baño tiene una ducha. Sistema de calefacción y WiFi incluido.',3,1,2,35.0000,4,75.0000,1,1,1,'2022-05-20 17:55:29'),('0005678B','Estudio en el centro de León, a 200m de la Catedral y los monumentos más emblemáticos de la ciudad. El alojamiento ofrece todos las facilidades para que disfrutes de unos días inolvidables en León.',2,1,1,35.0000,2,89.9900,2,2,3,'2022-05-20 17:55:29');
/*!40000 ALTER TABLE `accomodation` ENABLE KEYS */;
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
