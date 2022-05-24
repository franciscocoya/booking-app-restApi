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
-- Table structure for table `accomodation_acc_rule`
--

DROP TABLE IF EXISTS `accomodation_acc_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accomodation_acc_rule` (
  `ID_ACC` varchar(20) NOT NULL,
  `ID_ACC_RULE` int NOT NULL,
  PRIMARY KEY (`ID_ACC`,`ID_ACC_RULE`),
  KEY `FK_ACCOMODATION_ACC_RULE_ACCOMODATION_RULE` (`ID_ACC_RULE`),
  CONSTRAINT `FK_ACCOMODATION_ACC_RULE_ACCOMODATION` FOREIGN KEY (`ID_ACC`) REFERENCES `accomodation` (`REG_NUM`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ACCOMODATION_ACC_RULE_ACCOMODATION_RULE` FOREIGN KEY (`ID_ACC_RULE`) REFERENCES `accomodation_rule` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation_acc_rule`
--

LOCK TABLES `accomodation_acc_rule` WRITE;
/*!40000 ALTER TABLE `accomodation_acc_rule` DISABLE KEYS */;
INSERT INTO `accomodation_acc_rule` VALUES ('0001234A',1),('0005678B',1),('0001234A',2),('0005678B',2),('0001234A',3),('0005678B',3);
/*!40000 ALTER TABLE `accomodation_acc_rule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 19:58:22