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
-- Table structure for table `accomodation_service`
--

DROP TABLE IF EXISTS `accomodation_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accomodation_service` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DENOMINATION` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation_service`
--

LOCK TABLES `accomodation_service` WRITE;
/*!40000 ALTER TABLE `accomodation_service` DISABLE KEYS */;
INSERT INTO `accomodation_service` VALUES (1,'Wifi'),(2,'Calefacción'),(3,'TV'),(4,'Ropa de cama'),(5,'Toallas'),(6,'Detector de humo'),(7,'Botiquín'),(8,'Desayuno'),(9,'Aparcamiento gratuito'),(10,'Aire acondicionado'),(11,'Agua caliente'),(12,'Secador de pelo'),(13,'Lavadora'),(14,'Plancha'),(15,'Vajilla'),(16,'Admite mascotas'),(17,'Apto para fumadores'),(18,'Cuna'),(19,'Productos de limpieza'),(20,'Microondas'),(21,'Cafetera'),(22,'Accesible a minusválidos'),(23,'Congelador'),(24,'Horno'),(25,'Lavavajillas'),(26,'Terraza / patio'),(27,'Ascensor'),(28,'Bañera de hidromasaje'),(29,'Piscina privada'),(30,'Piscina compartida'),(31,'Jardín'),(32,'Zona para trabajar / Oficina');
/*!40000 ALTER TABLE `accomodation_service` ENABLE KEYS */;
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
