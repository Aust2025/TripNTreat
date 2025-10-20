-- MySQL dump 10.13  Distrib 8.0.43, for macos15 (arm64)
--
-- Host: localhost    Database: tripntreat
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `serviceno` varchar(45) DEFAULT NULL,
  `servicename` varchar(45) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'STA01','機場Taxi接駁',2000),(2,'SLU01','托運行李',700),(3,'TKO01','東京市區飯店升級',4000),(4,'TKO02','東京壽喜燒吃到飽',1000),(5,'HKD01','北海道溫泉旅館升級',2200),(6,'HKD02','北海道羊肉燒烤餐廳',8000),(7,'NL01','荷蘭恩荷芬鄉村餐廳',4500),(8,'NL02','荷蘭恩荷芬現代酒店升級',4000),(9,'NL03','荷蘭阿姆斯特丹文青咖啡館',1800),(10,'NL04','荷蘭阿姆斯特丹渡假村升級',10000),(11,'AK01','美國阿拉斯加精選酒店升級',12000),(12,'AK02','美國阿拉斯加現撈海鮮餐廳',1500),(13,'SF01','美國舊金山經典豪華酒店升級',14000),(14,'SF02','美國大老鷹餐廳風味料理',800),(15,'DE01','德國時尚設計酒店升級',5400),(16,'DE02','德國柏林地中海餐館',600),(17,'DE03','德國巴伐利亞啤酒餐廳',3000),(18,'DE04','德國慕尼黑別墅飯店升級',7000),(19,'MEL01','澳洲墨爾本遊艇晚餐',6000),(20,'MEL02','澳洲墨爾本濱海渡假村升級',9800),(21,'BNE01','澳洲布里斯本酒莊餐廳',2600),(22,'BNE02','澳洲布里斯本養生會館升級',5500);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-20  8:16:38
