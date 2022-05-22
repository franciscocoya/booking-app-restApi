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
-- Table structure for table `app_currency`
--

DROP TABLE IF EXISTS `app_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_currency` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CURRENCY_ALPHANUMERIC_CODE` varchar(3) DEFAULT NULL,
  `CURRENCY_NAME` varchar(65) NOT NULL,
  `CURRENCY_CODE` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=439 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_currency`
--

LOCK TABLES `app_currency` WRITE;
/*!40000 ALTER TABLE `app_currency` DISABLE KEYS */;
INSERT INTO `app_currency` VALUES (1,'AFN','Afghani',971),(2,'EUR','Euro',978),(3,'ALL','Lek',8),(4,'DZD','Algerian Dinar',12),(5,'USD','US Dollar',840),(7,'AOA','Kwanza',973),(8,'XCD','East Caribbean Dollar',951),(9,NULL,'No universal currency',NULL),(11,'ARS','Argentine Peso',32),(12,'AMD','Armenian Dram',51),(13,'AWG','Aruban Florin',533),(14,'AUD','Australian Dollar',36),(16,'AZN','Azerbaijan Manat',944),(17,'BSD','Bahamian Dollar',44),(18,'BHD','Bahraini Dinar',48),(19,'BDT','Taka',50),(20,'BBD','Barbados Dollar',52),(21,'BYN','Belarusian Ruble',933),(23,'BZD','Belize Dollar',84),(24,'XOF','CFA Franc BCEAO',952),(25,'BMD','Bermudian Dollar',60),(26,'INR','Indian Rupee',356),(27,'BTN','Ngultrum',64),(28,'BOB','Boliviano',68),(29,'BOV','Mvdol',984),(31,'BAM','Convertible Mark',977),(32,'BWP','Pula',72),(33,'NOK','Norwegian Krone',578),(34,'BRL','Brazilian Real',986),(36,'BND','Brunei Dollar',96),(37,'BGN','Bulgarian Lev',975),(39,'BIF','Burundi Franc',108),(40,'CVE','Cabo Verde Escudo',132),(41,'KHR','Riel',116),(42,'XAF','CFA Franc BEAC',950),(43,'CAD','Canadian Dollar',124),(44,'KYD','Cayman Islands Dollar',136),(47,'CLP','Chilean Peso',152),(48,'CLF','Unidad de Fomento',990),(49,'CNY','Yuan Renminbi',156),(52,'COP','Colombian Peso',170),(53,'COU','Unidad de Valor Real',970),(54,'KMF','Comorian Franc',174),(55,'CDF','Congolese Franc',976),(57,'NZD','New Zealand Dollar',554),(58,'CRC','Costa Rican Colon',188),(60,'HRK','Kuna',191),(61,'CUP','Cuban Peso',192),(62,'CUC','Peso Convertible',931),(63,'ANG','Netherlands Antillean Guilder',532),(65,'CZK','Czech Koruna',203),(66,'DKK','Danish Krone',208),(67,'DJF','Djibouti Franc',262),(69,'DOP','Dominican Peso',214),(71,'EGP','Egyptian Pound',818),(72,'SVC','El Salvador Colon',222),(75,'ERN','Nakfa',232),(77,'SZL','Lilangeni',748),(78,'ETB','Ethiopian Birr',230),(80,'FKP','Falkland Islands Pound',238),(82,'FJD','Fiji Dollar',242),(86,'XPF','CFP Franc',953),(89,'GMD','Dalasi',270),(90,'GEL','Lari',981),(92,'GHS','Ghana Cedi',936),(93,'GIP','Gibraltar Pound',292),(99,'GTQ','Quetzal',320),(100,'GBP','Pound Sterling',826),(101,'GNF','Guinean Franc',324),(103,'GYD','Guyana Dollar',328),(104,'HTG','Gourde',332),(108,'HNL','Lempira',340),(109,'HKD','Hong Kong Dollar',344),(110,'HUF','Forint',348),(111,'ISK','Iceland Krona',352),(113,'IDR','Rupiah',360),(114,'XDR','SDR (Special Drawing Right)',960),(115,'IRR','Iranian Rial',364),(116,'IQD','Iraqi Dinar',368),(119,'ILS','New Israeli Sheqel',376),(121,'JMD','Jamaican Dollar',388),(122,'JPY','Yen',392),(124,'JOD','Jordanian Dinar',400),(125,'KZT','Tenge',398),(126,'KES','Kenyan Shilling',404),(128,'KPW','North Korean Won',408),(129,'KRW','Won',410),(130,'KWD','Kuwaiti Dinar',414),(131,'KGS','Som',417),(132,'LAK','Lao Kip',418),(134,'LBP','Lebanese Pound',422),(135,'LSL','Loti',426),(136,'ZAR','Rand',710),(137,'LRD','Liberian Dollar',430),(138,'LYD','Libyan Dinar',434),(139,'CHF','Swiss Franc',756),(142,'MOP','Pataca',446),(143,'MKD','Denar',807),(144,'MGA','Malagasy Ariary',969),(145,'MWK','Malawi Kwacha',454),(146,'MYR','Malaysian Ringgit',458),(147,'MVR','Rufiyaa',462),(152,'MRU','Ouguiya',929),(153,'MUR','Mauritius Rupee',480),(155,'XUA','ADB Unit of Account',965),(156,'MXN','Mexican Peso',484),(157,'MXV','Mexican Unidad de Inversion (UDI)',979),(159,'MDL','Moldovan Leu',498),(161,'MNT','Tugrik',496),(164,'MAD','Moroccan Dirham',504),(165,'MZN','Mozambique Metical',943),(166,'MMK','Kyat',104),(167,'NAD','Namibia Dollar',516),(170,'NPR','Nepalese Rupee',524),(174,'NIO','Cordoba Oro',558),(176,'NGN','Naira',566),(181,'OMR','Rial Omani',512),(182,'PKR','Pakistan Rupee',586),(185,'PAB','Balboa',590),(187,'PGK','Kina',598),(188,'PYG','Guarani',600),(189,'PEN','Sol',604),(190,'PHP','Philippine Peso',608),(192,'PLN','Zloty',985),(195,'QAR','Qatari Rial',634),(197,'RON','Romanian Leu',946),(198,'RUB','Russian Ruble',643),(199,'RWF','Rwanda Franc',646),(201,'SHP','Saint Helena Pound',654),(207,'WST','Tala',882),(209,'STN','Dobra',930),(210,'SAR','Saudi Riyal',682),(212,'RSD','Serbian Dinar',941),(213,'SCR','Seychelles Rupee',690),(214,'SLL','Leone',694),(215,'SGD','Singapore Dollar',702),(217,'XSU','Sucre',994),(220,'SBD','Solomon Islands Dollar',90),(221,'SOS','Somali Shilling',706),(224,'SSP','South Sudanese Pound',728),(226,'LKR','Sri Lanka Rupee',144),(227,'SDG','Sudanese Pound',938),(228,'SRD','Surinam Dollar',968),(230,'SEK','Swedish Krona',752),(232,'CHE','WIR Euro',947),(233,'CHW','WIR Franc',948),(234,'SYP','Syrian Pound',760),(235,'TWD','New Taiwan Dollar',901),(236,'TJS','Somoni',972),(237,'TZS','Tanzanian Shilling',834),(238,'THB','Baht',764),(242,'TOP','Pa\'anga',776),(243,'TTD','Trinidad and Tobago Dollar',780),(244,'TND','Tunisian Dinar',788),(245,'TRY','Turkish Lira',949),(246,'TMT','Turkmenistan New Manat',934),(249,'UGX','Uganda Shilling',800),(250,'UAH','Hryvnia',980),(251,'AED','UAE Dirham',784),(255,'USN','US Dollar (Next day)',997),(256,'UYU','Peso Uruguayo',858),(257,'UYI','Uruguay Peso en Unidades Indexadas (UI)',940),(258,'UYW','Unidad Previsional',927),(259,'UZS','Uzbekistan Sum',860),(260,'VUV','Vatu',548),(261,'VES','Bolívar Soberano',928),(262,'VND','Dong',704),(267,'YER','Yemeni Rial',886),(268,'ZMW','Zambian Kwacha',967),(269,'ZWL','Zimbabwe Dollar',932),(270,'XBA','Bond Markets Unit European Composite Unit (EURCO)',955),(271,'XBB','Bond Markets Unit European Monetary Unit (E.M.U.-6)',956),(272,'XBC','Bond Markets Unit European Unit of Account 9 (E.U.A.-9)',957),(273,'XBD','Bond Markets Unit European Unit of Account 17 (E.U.A.-17)',958),(274,'XTS','Codes specifically reserved for testing purposes',963),(275,'XXX','The codes assigned for transactions where no currency is involved',999),(276,'XAU','Gold',959),(277,'XPD','Palladium',964),(278,'XPT','Platinum',962),(279,'XAG','Silver',961),(280,'AFA','Afghani',4),(281,'FIM','Markka',246),(283,'ADP','Andorran Peseta',20),(284,'ESP','Spanish Peseta',724),(285,'FRF','French Franc',250),(286,'AOK','Kwanza',24),(288,'AOR','Kwanza Reajustado',982),(292,'RUR','Russian Ruble',810),(293,'ATS','Schilling',40),(294,'AYM','Azerbaijan Manat',945),(295,'AZM','Azerbaijanian Manat',31),(297,'BYB','Belarusian Ruble',112),(298,'BYR','Belarusian Ruble',974),(300,'BEC','Convertible Franc',993),(301,'BEF','Belgian Franc',56),(302,'BEL','Financial Franc',992),(304,'BAD','Dinar',70),(305,'BRB','Cruzeiro',76),(309,'BRR','Cruzeiro Real',987),(310,'BGJ','Lev A/52',100),(316,'CYP','Cyprus Pound',196),(318,'CSK','Koruna',200),(319,'ECS','Sucre',218),(320,'ECV','Unidad de Valor Constante (UVC)',983),(321,'GQE','Ekwele',226),(322,'EEK','Kroon',233),(323,'XEU','European Currency Unit (E.C.U)',954),(328,'GEK','Georgian Coupon',268),(330,'DDM','Mark der DDR',278),(331,'DEM','Deutsche Mark',276),(332,'GHC','Cedi',288),(333,'GHP','Ghana Cedi',939),(334,'GRD','Drachma',300),(338,'GWE','Guinea Escudo',624),(340,'ITL','Italian Lira',380),(342,'IEP','Irish Pound',372),(349,'LVL','Latvian Lats',428),(352,'ZAL','Financial Rand',991),(353,'LTL','Lithuanian Litas',440),(355,'LUC','Luxembourg Convertible Franc',989),(356,'LUF','Luxembourg Franc',442),(357,'LUL','Luxembourg Financial Franc',988),(358,'MGF','Malagasy Franc',450),(361,'MLF','Mali Franc',466),(362,'MTL','Maltese Lira',470),(365,'MRO','Ouguiya',478),(370,'MZE','Mozambique Escudo',508),(372,'NLG','Netherlands Guilder',528),(379,'PLZ','Zloty',616),(380,'PTE','Portuguese Escudo',620),(382,'ROK','Leu A/52',642),(390,'STD','Dobra',678),(391,'CSD','Serbian Dinar',891),(393,'SKK','Slovak Koruna',703),(394,'SIT','Tolar',705),(397,'RHD','Rhodesian Dollar',716),(398,'ESA','Spanish Peseta',996),(399,'ESB','A\" Account (convertible Peseta Account)\"',995),(401,'SDD','Sudanese Dinar',736),(403,'SRG','Surinam Guilder',740),(407,'TJR','Tajik Ruble',762),(409,'TPE','Timor Escudo',626),(410,'TRL','Old Turkish Lira',792),(413,'TMM','Turkmenistan Manat',795),(416,'UAK','Karbovanet',804),(418,'USS','US Dollar (Same day)',998),(422,'VEB','Bolivar',862),(423,'VEF','Bolivar Fuerte',937),(427,'YDD','Yemeni Dinar',720),(428,'YUD','New Yugoslavian Dinar',890),(431,'ZRN','New Zaire',180),(433,'ZMK','Zambian Kwacha',894),(437,'ZWN','Zimbabwe Dollar (new)',942),(438,'ZWR','Zimbabwe Dollar',935);
/*!40000 ALTER TABLE `app_currency` ENABLE KEYS */;
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
