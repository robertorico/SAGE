CREATE DATABASE  IF NOT EXISTS `loanapound` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `loanapound`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: loanapound
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `config_criteria`
--

DROP TABLE IF EXISTS `config_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_criteria` (
  `provider_id` int(11) NOT NULL,
  `criteria_id` int(11) NOT NULL,
  KEY `FKkns8nexpxbuvagjjviwd3hy2n` (`criteria_id`),
  KEY `FK848duch33d05nr38qgp5ik7wy` (`provider_id`),
  CONSTRAINT `FK848duch33d05nr38qgp5ik7wy` FOREIGN KEY (`provider_id`) REFERENCES `provider_score` (`id`),
  CONSTRAINT `FKkns8nexpxbuvagjjviwd3hy2n` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_criteria`
--

LOCK TABLES `config_criteria` WRITE;
/*!40000 ALTER TABLE `config_criteria` DISABLE KEYS */;
INSERT INTO `config_criteria` VALUES (1,1),(1,3),(2,2),(2,4);
/*!40000 ALTER TABLE `config_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_provider`
--

DROP TABLE IF EXISTS `config_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_provider` (
  `password` varchar(128) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `user` varchar(64) DEFAULT NULL,
  `type_ws` varchar(4) NOT NULL,
  `provider_score` int(11) NOT NULL,
  PRIMARY KEY (`type_ws`,`provider_score`),
  KEY `FK7rl9jbjmk1m32y6fe8ie64txi` (`provider_score`),
  CONSTRAINT `FK6442ns4538kpxckd1qn8n6u4i` FOREIGN KEY (`type_ws`) REFERENCES `type_ws` (`name`),
  CONSTRAINT `FK7rl9jbjmk1m32y6fe8ie64txi` FOREIGN KEY (`provider_score`) REFERENCES `provider_score` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_provider`
--

LOCK TABLES `config_provider` WRITE;
/*!40000 ALTER TABLE `config_provider` DISABLE KEYS */;
INSERT INTO `config_provider` VALUES (NULL,'http://localhost:82/getScore/',NULL,'rest',2),(NULL,NULL,NULL,'soap',1);
/*!40000 ALTER TABLE `config_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria`
--

DROP TABLE IF EXISTS `criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criteria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria`
--

LOCK TABLES `criteria` WRITE;
/*!40000 ALTER TABLE `criteria` DISABLE KEYS */;
INSERT INTO `criteria` VALUES (1,'minAmount',1000),(2,'minAmount',5000),(3,'maxAmount',5000),(4,'maxAmount',10000);
/*!40000 ALTER TABLE `criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` varchar(10) NOT NULL,
  `birthday` tinyblob NOT NULL,
  `country` varchar(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number` varchar(6) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `postal_code` varchar(8) NOT NULL,
  `name_street` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `max_amount` double NOT NULL,
  `max_month` int(11) NOT NULL,
  `min_amount` double NOT NULL,
  `min_month` int(11) NOT NULL,
  `provider` varchar(64) NOT NULL,
  `rate` double NOT NULL,
  `score_approved` int(11) NOT NULL,
  `score_rejected` int(11) NOT NULL,
  `type_loan` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8mf5wbhikodclhcspb3c6e0ix` (`type_loan`),
  CONSTRAINT `FK8mf5wbhikodclhcspb3c6e0ix` FOREIGN KEY (`type_loan`) REFERENCES `type_loan` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (1,10000,48,1000,12,'bank1',10,80,50,'Car');
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_request`
--

DROP TABLE IF EXISTS `loan_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `end_date` tinyblob NOT NULL,
  `is_mail_send` bit(1) NOT NULL,
  `months` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `start_date` tinyblob NOT NULL,
  `status` varchar(255) NOT NULL,
  `customer` varchar(10) NOT NULL,
  `loan` int(11) NOT NULL,
  `provider_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsdggex5c3581j1gb4oif815iy` (`customer`),
  KEY `FK2uhb6c73uc64u5a9370v6j935` (`loan`),
  KEY `FKgas89vvncdbvsuqhnjupcl5p3` (`provider_score`),
  CONSTRAINT `FK2uhb6c73uc64u5a9370v6j935` FOREIGN KEY (`loan`) REFERENCES `loan` (`id`),
  CONSTRAINT `FKgas89vvncdbvsuqhnjupcl5p3` FOREIGN KEY (`provider_score`) REFERENCES `provider_score` (`id`),
  CONSTRAINT `FKsdggex5c3581j1gb4oif815iy` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_request`
--

LOCK TABLES `loan_request` WRITE;
/*!40000 ALTER TABLE `loan_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider_score`
--

DROP TABLE IF EXISTS `provider_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider_score`
--

LOCK TABLES `provider_score` WRITE;
/*!40000 ALTER TABLE `provider_score` DISABLE KEYS */;
INSERT INTO `provider_score` VALUES (1,'provider1'),(2,'provider2');
/*!40000 ALTER TABLE `provider_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_loan`
--

DROP TABLE IF EXISTS `type_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_loan` (
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_loan`
--

LOCK TABLES `type_loan` WRITE;
/*!40000 ALTER TABLE `type_loan` DISABLE KEYS */;
INSERT INTO `type_loan` VALUES ('Car'),('Debts'),('Holiday'),('Weeding');
/*!40000 ALTER TABLE `type_loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_ws`
--

DROP TABLE IF EXISTS `type_ws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_ws` (
  `name` varchar(4) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_ws`
--

LOCK TABLES `type_ws` WRITE;
/*!40000 ALTER TABLE `type_ws` DISABLE KEYS */;
INSERT INTO `type_ws` VALUES ('rest'),('soap');
/*!40000 ALTER TABLE `type_ws` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03 23:47:51
