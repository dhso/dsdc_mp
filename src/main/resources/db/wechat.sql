-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 10.201.14.231    Database: dataguru_robot
-- ------------------------------------------------------
-- Server version	5.6.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `wx_customer`
--

DROP TABLE IF EXISTS `wx_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_customer` (
  `wc_openid` varchar(255) NOT NULL,
  `wc_nickname` varchar(255) DEFAULT NULL,
  `wc_sex` char(1) DEFAULT NULL,
  `wc_city` varchar(100) DEFAULT NULL,
  `wc_country` varchar(100) DEFAULT NULL,
  `wc_province` varchar(100) DEFAULT NULL,
  `wc_language` varchar(45) DEFAULT NULL,
  `wc_headimgurl` text,
  `wc_subscribe_time` varchar(45) DEFAULT NULL,
  `wc_unionid` varchar(255) DEFAULT NULL,
  `wc_remark` varchar(255) DEFAULT NULL,
  `wc_groupid` varchar(255) DEFAULT NULL,
  `wc_subscribe` char(1) DEFAULT NULL,
  PRIMARY KEY (`wc_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wx_question`
--

DROP TABLE IF EXISTS `wx_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_question` (
  `wq_id` int(11) NOT NULL AUTO_INCREMENT,
  `wq_openid` varchar(255) NOT NULL,
  `wq_question` text NOT NULL,
  `wq_create_dt` datetime NOT NULL,
  PRIMARY KEY (`wq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_question`
--

LOCK TABLES `wx_question` WRITE;
/*!40000 ALTER TABLE `wx_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 11:12:14
