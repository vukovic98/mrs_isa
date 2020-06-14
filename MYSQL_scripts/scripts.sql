-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mrs_isa
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `appointment_id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `duration` double NOT NULL,
  `appointment_request_id` bigint DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `medical_report_id` bigint DEFAULT NULL,
  `ordination_id` int DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `pricelis_item_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  UNIQUE KEY `unique_appointment` (`date_time`,`doctor_id`),
  KEY `FK2558817qerl2j6ovx20ob0tnw` (`appointment_request_id`),
  KEY `FKoeb98n82eph1dx43v3y2bcmsl` (`doctor_id`),
  KEY `FKe0oagjimyv2ynl8lj5yclrjte` (`medical_report_id`),
  KEY `FKidcdmyb0acu2fe1mp0t1lkr3q` (`ordination_id`),
  KEY `FK4apif2ewfyf14077ichee8g06` (`patient_id`),
  KEY `FKjsurhq0cnftumkoe0kbykdg0j` (`pricelis_item_id`),
  CONSTRAINT `FK2558817qerl2j6ovx20ob0tnw` FOREIGN KEY (`appointment_request_id`) REFERENCES `appointment_request` (`appointment_request_id`),
  CONSTRAINT `FK4apif2ewfyf14077ichee8g06` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`user_id`),
  CONSTRAINT `FKe0oagjimyv2ynl8lj5yclrjte` FOREIGN KEY (`medical_report_id`) REFERENCES `medical_report` (`medical_report_id`),
  CONSTRAINT `FKidcdmyb0acu2fe1mp0t1lkr3q` FOREIGN KEY (`ordination_id`) REFERENCES `ordination` (`ordination_number`),
  CONSTRAINT `FKjsurhq0cnftumkoe0kbykdg0j` FOREIGN KEY (`pricelis_item_id`) REFERENCES `pricelist_item` (`pricelist_item_id`),
  CONSTRAINT `FKoeb98n82eph1dx43v3y2bcmsl` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2020-05-09 13:00:00.000000',44.69,1,50,15,18,22,4,41.78,NULL),(2,'2020-06-13 15:30:00.000000',0.38333333333333336,2,60,33,17,34,10,9.68,NULL),(3,'2020-04-20 12:30:00.000000',51.26,3,60,18,20,40,6,6.69,NULL),(4,'2020-06-04 15:30:00.000000',0,4,60,7,1,33,9,8.6,NULL),(5,'2020-06-11 11:00:00.000000',5.416666666666667,5,60,30,1,24,3,5.15,NULL),(6,'2020-06-07 09:30:00.000000',0,6,66,13,24,25,3,5.15,NULL),(7,'2020-04-13 10:30:00.000000',78.75,7,44,20,10,26,10,9.68,NULL),(8,'2020-05-25 08:00:00.000000',0,8,44,3,24,23,8,14.87,NULL),(9,'2020-01-20 08:30:00.000000',15.38,9,44,4,24,27,1,1.21,NULL),(10,'2020-01-02 11:30:00.000000',1.2,10,61,14,7,28,4,41.78,NULL),(11,'2020-02-19 15:00:00.000000',6.14,11,61,11,8,35,2,33.17,NULL),(12,'2020-05-21 12:30:00.000000',0,12,61,19,9,36,9,8.6,NULL),(13,'2020-01-21 09:00:00.000000',83.01,13,66,1,7,38,5,7.49,NULL),(14,'2020-06-07 13:00:00.000000',0,14,66,17,6,29,1,1.21,NULL),(15,'2020-01-12 08:00:00.000000',6.74,15,66,6,16,41,3,5.15,NULL),(16,'2020-05-27 13:00:00.000000',0,16,45,8,5,39,10,9.68,NULL),(17,'2020-06-06 10:00:00.000000',0,17,52,16,25,37,1,1.21,NULL),(18,'2020-05-04 10:30:00.000000',94.93,18,51,9,2,30,1,1.21,NULL),(19,'2020-06-13 13:00:00.000000',61.19,19,47,5,25,31,4,41.78,NULL),(20,'2020-02-18 14:30:00.000000',18.37,20,47,10,7,32,9,8.6,NULL),(22,'2020-06-09 09:00:00.000000',0,25,506,NULL,28,22,4,41.78,NULL),(23,'2020-06-11 10:00:00.000000',0,26,506,NULL,28,22,4,41.78,NULL),(28,'2020-06-11 08:00:00.000000',0,31,506,NULL,28,22,4,41.78,NULL),(40,'2020-06-10 09:00:00.000000',0,47,504,NULL,28,22,3,5.15,NULL),(41,'2020-06-10 11:30:00.000000',0,48,504,NULL,28,22,3,5.15,NULL),(47,'2020-06-11 11:00:00.000000',0,60,44,NULL,20,27,2,33.17,NULL),(48,'2020-06-11 15:00:00.000000',0,61,45,NULL,20,27,2,33.17,NULL),(49,'2020-06-11 15:30:00.000000',0,62,56,NULL,20,27,2,33.17,NULL),(50,'2020-06-10 15:30:00.000000',0,63,500,NULL,28,27,1,1.21,NULL),(51,'2020-06-11 14:30:00.000000',0,64,60,NULL,20,22,10,9.68,NULL),(52,'2020-06-25 09:00:00.000000',0,67,60,NULL,20,24,8,14.87,NULL),(53,'2020-06-26 14:00:00.000000',0,68,553,NULL,842,NULL,7,38.84,0.6),(54,'2020-06-25 10:30:00.000000',0,69,558,NULL,842,22,10,9.68,0.6),(55,'2020-06-25 15:30:00.000000',0,70,48,NULL,NULL,22,4,41.78,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_request`
--

DROP TABLE IF EXISTS `appointment_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_request` (
  `appointment_request_id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `request_date` datetime(6) NOT NULL,
  `appointment_id` bigint DEFAULT NULL,
  `clinical_center_admin_id` bigint DEFAULT NULL,
  PRIMARY KEY (`appointment_request_id`),
  KEY `FKcaongb4ylcfd5bgwfggh8v98i` (`appointment_id`),
  KEY `FKxgbya24b34stq24sougtf0xt` (`clinical_center_admin_id`),
  CONSTRAINT `FKcaongb4ylcfd5bgwfggh8v98i` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`),
  CONSTRAINT `FKxgbya24b34stq24sougtf0xt` FOREIGN KEY (`clinical_center_admin_id`) REFERENCES `clinical_center_administrator` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_request`
--

LOCK TABLES `appointment_request` WRITE;
/*!40000 ALTER TABLE `appointment_request` DISABLE KEYS */;
INSERT INTO `appointment_request` VALUES (1,_binary '','2005-11-20 10:13:00.000000',1,1),(2,_binary '','2004-02-20 07:15:00.000000',2,1),(3,_binary '','2003-02-20 02:47:00.000000',3,1),(4,_binary '','2005-07-20 02:39:00.000000',4,1),(5,_binary '','2002-07-20 02:08:00.000000',5,1),(6,_binary '','2001-08-20 10:33:00.000000',6,1),(7,_binary '','2005-02-20 05:52:00.000000',7,1),(8,_binary '','2003-12-20 02:32:00.000000',8,1),(9,_binary '','2005-02-20 10:21:00.000000',9,1),(10,_binary '','2001-05-20 05:55:00.000000',10,1),(11,_binary '','2006-01-20 05:58:00.000000',11,1),(12,_binary '','2005-12-20 06:52:00.000000',12,1),(13,_binary '','2002-04-20 07:26:00.000000',13,1),(14,_binary '','2003-03-20 10:09:00.000000',14,1),(15,_binary '','2005-09-20 01:31:00.000000',15,1),(16,_binary '','2005-09-20 05:25:00.000000',16,1),(17,_binary '','2007-04-20 00:42:00.000000',17,1),(18,_binary '','2004-09-20 08:51:00.000000',18,1),(19,_binary '','2004-09-20 04:41:00.000000',19,1),(20,_binary '','2005-01-20 01:47:00.000000',20,1),(25,_binary '','2020-06-09 09:00:00.000000',22,1),(26,_binary '','2020-06-11 10:00:00.000000',23,1),(31,_binary '','2020-06-11 08:00:00.000000',28,1),(47,_binary '','2020-06-10 09:00:00.000000',40,1),(48,_binary '','2020-06-10 11:30:00.000000',41,1),(60,_binary '','2020-06-11 11:00:00.000000',47,1),(61,_binary '','2020-06-11 15:00:00.000000',48,1),(62,_binary '','2020-06-11 15:30:00.000000',49,1),(63,_binary '','2020-06-10 15:30:00.000000',50,1),(64,_binary '','2020-06-11 14:30:00.000000',51,1),(67,_binary '','2020-06-25 09:00:00.000000',52,1),(68,_binary '','2020-06-26 14:00:00.000000',53,1),(69,_binary '','2020-06-25 10:30:00.000000',54,1),(70,_binary '\0','2020-06-25 15:30:00.000000',55,1);
/*!40000 ALTER TABLE `appointment_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic` (
  `clinic_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pricelist_id` bigint DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`clinic_id`),
  KEY `FKhkutbbq57ulhv8x8wyym8rpxd` (`pricelist_id`),
  CONSTRAINT `FKhkutbbq57ulhv8x8wyym8rpxd` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (1,'Sarajevska 5','This is a description 2066600','Clinic C',1,'Beograd'),(2,'Bulevar oslobođenja 68','Description number 9935388','Clinic H',1,'Novi Sad'),(3,'Bulevar dr Zorana Đinđića 5','This is a description 7951230','Clinic E',1,'Niš'),(4,'Gajeva 10','Description number 8818106','Clinic L',1,'Kragujevac'),(5,'Sutjeska 7','This is a description 2881356','Clinic D',1,'Kosovska Mitrovica'),(6,'Starine Novaka 1','This is a description 6770820','Clinic X',1,'Subotica'),(7,'Dositejeva 16','This is a description 7442127','Clinic Y',1,'Kraljevo'),(8,'Stefana Nemanje 7','This is a description 1859261','Clinic Z',1,'Raška'),(9,'Hercegovačka 3','Description number 8020246','Clinic T',1,'Novi Pazar'),(10,'Šafarikova 90','This is a description 7200345','Clinic N',1,'Bačka Palanka'),(11,'Knjaza Miloša 30','Description number 7820988','Clinic I',1,'Negotin'),(12,'Đure Jakšića 34','This is a description 7236562','Clinic O',1,'Zrenjanin'),(13,'Kneza Milete 3','Description number 5592903','Clinic G',1,'Jagodina'),(14,'Vojvode Stepe 6','Description number 4100887','Clinic S',1,'Pirot'),(15,'Kosovska 14','Description number 5003891','Clinic M',1,'Vranje'),(16,'Ivana Cankara 5','Description number 3786300','Clinic U',1,'Sombor'),(17,'Šumadijska 3','Description number 8587833','Clinic A',1,'Vršac'),(18,'Masarikova 45','Description number 4452302','Clinic V',1,'Šabac'),(19,'Radnička 16','Description number 8964301','Clinic B',1,'Valjevo'),(20,'Vuka Karadžića 5','This is a description 0704753','Clinic J',1,'Gornji Milanovac'),(21,'Bulevar Oslobodjenja 78','clinic','Clinic Novi Sad',1,'Novi Sad'),(22,'Knez Mihailova 5','','Saint Bonaventura',1,'Beograd'),(23,'Knez Mihajlova 15','klinika','KlinicOne',1,'Beograd');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_administrator`
--

DROP TABLE IF EXISTS `clinic_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_administrator` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `clinic_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKrutcaul7x1k33bv8uxtiyq203` (`clinic_id`),
  CONSTRAINT `FKrutcaul7x1k33bv8uxtiyq203` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_administrator`
--

LOCK TABLES `clinic_administrator` WRITE;
/*!40000 ALTER TABLE `clinic_administrator` DISABLE KEYS */;
INSERT INTO `clinic_administrator` VALUES (2,'5 East MacArthur','SEOUL','Congo','s@s','Ester','78359953195','Scheffold','123','666-2824','CLINIC_ADMINISTRATOR',3),(3,'9554 Springfield Ave','Osaka','Venezuela','Frank.Royal@mobileme.de','Matthijs','02174889515','Stewart','Pass376','666-3710','CLINIC_ADMINISTRATOR',2),(4,'9 Warren Ave','JAKARTA','Syria','William.Wargula@gawab.ca','Tom','23999106473','Swaine','Pass060','666-0656','CLINIC_ADMINISTRATOR',18),(5,'16 Windbrook Crs','Kunming','Turkey','TreesAnderson@excite.us','Andy','05412071156','Davis','Pass608','666-2563','CLINIC_ADMINISTRATOR',12),(6,'846 Morningside Dr','Jilin','Western Sahara','Trees.Cramer@dolfijn.us','Victor','09745012393','Lee','Pass402','666-6821','CLINIC_ADMINISTRATOR',10),(7,'17 Sutter Street','Shijiazhuang','Singapore','Fred.Laudanski1@kpn.it','Charles','42559794344','Nahay','Pass669','666-6987','CLINIC_ADMINISTRATOR',8),(8,'4088 W. Broadway','Taegu','Luxembourg','RFrega@yahoo.org','Bert','69398719295','Prior','Pass993','666-4082','CLINIC_ADMINISTRATOR',19),(9,'9 Jefferson Road','Tengzhou','Turkmenistan','Hank.Pickering2@live.co.uk','Joop','86876259825','Gildersleeve','Pass866','666-6679','CLINIC_ADMINISTRATOR',16),(10,'4 S. Riverside Plaza','Hyderabad','Laos','TreesWong@msn.nl','Susan','24157952739','Millis','Pass041','666-2222','CLINIC_ADMINISTRATOR',17),(11,'5 Southwest Blvd','Zhanjiang','Venezuela','VictorWeinstein5@lycos.gov','Alejandro','17120484596','Slater','Pass178','666-6684','CLINIC_ADMINISTRATOR',6),(12,'5 Fontanoso Way','Chengdu','Bouvet Island','BrentHendrix2@libero.org','Suzanne','15824066674','Knopp','Pass472','666-7913','CLINIC_ADMINISTRATOR',15),(13,'8219 Redwood Ave.','KINSHASA','Turkmenistan','HankNahay3@lycos.net','Mick','67295025428','Gerschkow','Pass843','666-8028','CLINIC_ADMINISTRATOR',20),(14,'6157 Willingdon Ave','BANGKOK','Cambodia','L.Millis@web.org','Co','18393216689','Cain','Pass049','666-1481','CLINIC_ADMINISTRATOR',1),(15,'0 Charlotte Avenue','Jilin','Turkey','Pete.Leonarda1@kpn.nl','Francisco','88755081417','Riegel','Pass697','666-5631','CLINIC_ADMINISTRATOR',11),(16,'99 Paddington Lane','MANAGUA','India','Emma.Nithman@libero.dk','Jesse','34637459987','Wilson','Pass187','666-1375','CLINIC_ADMINISTRATOR',4),(17,'9 Woodcote Valley Road','Vadodara','French Guiana','Y.Ionescu1@mobileme.org','Jeffery','49119641380','Troher','Pass537','666-6547','CLINIC_ADMINISTRATOR',5),(18,'1321 Buckthorn Lane','Guikong','Madagascar','G.DeBuck3@libero.gov','Bengie','20991078167','Pierce','Pass509','666-6862','CLINIC_ADMINISTRATOR',14),(19,'2229 Warm Spring Road','YANGON','France','H.Voigt@excite.it','Luis','14977670739','Freed','Pass713','666-4899','CLINIC_ADMINISTRATOR',9),(20,'027 Sarasota St.','Wuhan','American Samoa','Will.Barbee1@mobileme.cc','Will','53599613058','Scheffold','Pass703','666-0725','CLINIC_ADMINISTRATOR',13),(21,'7069 N York Dr','Jaipur','Slovak Republic','CiskaThaler@freeweb.us','Izzy','70255989386','Herrin','Pass142','666-8270','CLINIC_ADMINISTRATOR',7),(252,'Tomice Aleksic 25','Raska','Srbija','pera@peric.pp','Pera','1234565432','Peric','123','666-3599','CLINIC_ADMINISTRATOR',12);
/*!40000 ALTER TABLE `clinic_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_administrator_leave_requests`
--

DROP TABLE IF EXISTS `clinic_administrator_leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_administrator_leave_requests` (
  `administrator_id` bigint NOT NULL,
  `leave_requests_leave_request_id` bigint NOT NULL,
  UNIQUE KEY `UK_gl1wg5f1wi1mm9jephscevfde` (`leave_requests_leave_request_id`),
  KEY `FKn2m6pnjfi2fp4ncl20f4oxoh4` (`administrator_id`),
  CONSTRAINT `FKgrfokl0580i2snifj1x6ei7gx` FOREIGN KEY (`leave_requests_leave_request_id`) REFERENCES `leave_request` (`leave_request_id`),
  CONSTRAINT `FKn2m6pnjfi2fp4ncl20f4oxoh4` FOREIGN KEY (`administrator_id`) REFERENCES `clinic_administrator` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_administrator_leave_requests`
--

LOCK TABLES `clinic_administrator_leave_requests` WRITE;
/*!40000 ALTER TABLE `clinic_administrator_leave_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `clinic_administrator_leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_administrators`
--

DROP TABLE IF EXISTS `clinic_administrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_administrators` (
  `clinic_id` bigint NOT NULL,
  `clinic_administrators_user_id` bigint NOT NULL,
  UNIQUE KEY `UK_exers3fvjl1geigm6x52omwse` (`clinic_administrators_user_id`),
  KEY `FKj7ygriogaon3s08jjno1li50l` (`clinic_id`),
  CONSTRAINT `FKj7ygriogaon3s08jjno1li50l` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`),
  CONSTRAINT `FKm7sjkxtt5ibypsqcmh96a2prp` FOREIGN KEY (`clinic_administrators_user_id`) REFERENCES `clinic_administrator` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_administrators`
--

LOCK TABLES `clinic_administrators` WRITE;
/*!40000 ALTER TABLE `clinic_administrators` DISABLE KEYS */;
INSERT INTO `clinic_administrators` VALUES (1,20),(2,7),(3,4),(4,14),(5,5),(6,9),(7,11),(8,21),(9,12),(10,17),(11,2),(12,19),(13,15),(14,3),(15,13),(16,16),(17,18),(18,6),(19,10),(20,8);
/*!40000 ALTER TABLE `clinic_administrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_administrator_appointment_requests`
--

DROP TABLE IF EXISTS `clinic_center_administrator_appointment_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_administrator_appointment_requests` (
  `administrator_id` bigint NOT NULL,
  `appointment_request_appointment_request_id` bigint NOT NULL,
  UNIQUE KEY `UK_n8yanymldrecoilpd7g7ssrmj` (`appointment_request_appointment_request_id`),
  KEY `FK9uaqp2n4nibi265jk82k5nyi2` (`administrator_id`),
  CONSTRAINT `FK9uaqp2n4nibi265jk82k5nyi2` FOREIGN KEY (`administrator_id`) REFERENCES `clinical_center_administrator` (`user_id`),
  CONSTRAINT `FKc43ggjf8iuajo0ndk4l3ym19l` FOREIGN KEY (`appointment_request_appointment_request_id`) REFERENCES `appointment_request` (`appointment_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_administrator_appointment_requests`
--

LOCK TABLES `clinic_center_administrator_appointment_requests` WRITE;
/*!40000 ALTER TABLE `clinic_center_administrator_appointment_requests` DISABLE KEYS */;
INSERT INTO `clinic_center_administrator_appointment_requests` VALUES (1,25),(1,26),(1,31),(1,47),(1,48),(1,60),(1,61),(1,62),(1,63),(1,64),(1,67),(1,68),(1,69),(1,70);
/*!40000 ALTER TABLE `clinic_center_administrator_appointment_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_administrator_registration_requests`
--

DROP TABLE IF EXISTS `clinic_center_administrator_registration_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_administrator_registration_requests` (
  `administrator_id` bigint NOT NULL,
  `registration_request_registration_request_id` bigint NOT NULL,
  UNIQUE KEY `UK_j5eexuowrpet8f7698l9hq81r` (`registration_request_registration_request_id`),
  KEY `FK1aahnyh9oux7uug4ad8pgxcwv` (`administrator_id`),
  CONSTRAINT `FK1aahnyh9oux7uug4ad8pgxcwv` FOREIGN KEY (`administrator_id`) REFERENCES `clinical_center_administrator` (`user_id`),
  CONSTRAINT `FKcsqwp5aa8v87e2bixm9fca2ln` FOREIGN KEY (`registration_request_registration_request_id`) REFERENCES `registration_request` (`registration_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_administrator_registration_requests`
--

LOCK TABLES `clinic_center_administrator_registration_requests` WRITE;
/*!40000 ALTER TABLE `clinic_center_administrator_registration_requests` DISABLE KEYS */;
INSERT INTO `clinic_center_administrator_registration_requests` VALUES (1,1),(1,7);
/*!40000 ALTER TABLE `clinic_center_administrator_registration_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_administrators`
--

DROP TABLE IF EXISTS `clinic_center_administrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_administrators` (
  `clinic_center_id` bigint NOT NULL,
  `clinical_center_administrators_user_id` bigint NOT NULL,
  UNIQUE KEY `UK_oavjl9excus047hegduo1fwdq` (`clinical_center_administrators_user_id`),
  KEY `FKdlqx0f1hyyhrxpnk4xgg0qmi1` (`clinic_center_id`),
  CONSTRAINT `FKc60s2h8694ucedgrop3jc2lf5` FOREIGN KEY (`clinical_center_administrators_user_id`) REFERENCES `clinical_center_administrator` (`user_id`),
  CONSTRAINT `FKdlqx0f1hyyhrxpnk4xgg0qmi1` FOREIGN KEY (`clinic_center_id`) REFERENCES `clinical_center` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_administrators`
--

LOCK TABLES `clinic_center_administrators` WRITE;
/*!40000 ALTER TABLE `clinic_center_administrators` DISABLE KEYS */;
INSERT INTO `clinic_center_administrators` VALUES (1,1);
/*!40000 ALTER TABLE `clinic_center_administrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_clinics`
--

DROP TABLE IF EXISTS `clinic_center_clinics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_clinics` (
  `clinic_center_id` bigint NOT NULL,
  `clinics_clinic_id` bigint NOT NULL,
  UNIQUE KEY `UK_k9t2jy2alvpr6ugnkqy03vwiu` (`clinics_clinic_id`),
  KEY `FKkpikhoajosecr4l3llrpxi7dx` (`clinic_center_id`),
  CONSTRAINT `FK5na7442qw2jd7txv9ah35150f` FOREIGN KEY (`clinics_clinic_id`) REFERENCES `clinic` (`clinic_id`),
  CONSTRAINT `FKkpikhoajosecr4l3llrpxi7dx` FOREIGN KEY (`clinic_center_id`) REFERENCES `clinical_center` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_clinics`
--

LOCK TABLES `clinic_center_clinics` WRITE;
/*!40000 ALTER TABLE `clinic_center_clinics` DISABLE KEYS */;
INSERT INTO `clinic_center_clinics` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);
/*!40000 ALTER TABLE `clinic_center_clinics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_diagnosis`
--

DROP TABLE IF EXISTS `clinic_center_diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_diagnosis` (
  `clinic_center_id` bigint NOT NULL,
  `diagnosis_diagnosis_id` bigint NOT NULL,
  UNIQUE KEY `UK_m9xgl639nll0ydsrntxynwde7` (`diagnosis_diagnosis_id`),
  KEY `FK3b3nd2w5ue8938b2s9kcs9je3` (`clinic_center_id`),
  CONSTRAINT `FK3b3nd2w5ue8938b2s9kcs9je3` FOREIGN KEY (`clinic_center_id`) REFERENCES `clinical_center` (`clinic_id`),
  CONSTRAINT `FKcq5gp39bf14v9b5t2ku8oqqb4` FOREIGN KEY (`diagnosis_diagnosis_id`) REFERENCES `diagnosis` (`diagnosis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_diagnosis`
--

LOCK TABLES `clinic_center_diagnosis` WRITE;
/*!40000 ALTER TABLE `clinic_center_diagnosis` DISABLE KEYS */;
INSERT INTO `clinic_center_diagnosis` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,29);
/*!40000 ALTER TABLE `clinic_center_diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_center_medication`
--

DROP TABLE IF EXISTS `clinic_center_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_medication` (
  `clinic_center_id` bigint NOT NULL,
  `medications_medication_id` bigint NOT NULL,
  UNIQUE KEY `UK_mfk585ct06tyjb7d732k7in2l` (`medications_medication_id`),
  KEY `FKm54yhf3dfwhxg96ab98emyyti` (`clinic_center_id`),
  CONSTRAINT `FKcp974tnc82x0ydmcqnfbrj0ii` FOREIGN KEY (`medications_medication_id`) REFERENCES `medication` (`medication_id`),
  CONSTRAINT `FKm54yhf3dfwhxg96ab98emyyti` FOREIGN KEY (`clinic_center_id`) REFERENCES `clinical_center` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_medication`
--

LOCK TABLES `clinic_center_medication` WRITE;
/*!40000 ALTER TABLE `clinic_center_medication` DISABLE KEYS */;
INSERT INTO `clinic_center_medication` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25);
/*!40000 ALTER TABLE `clinic_center_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_doctors`
--

DROP TABLE IF EXISTS `clinic_doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_doctors` (
  `clinic_id` bigint NOT NULL,
  `doctors_user_id` bigint NOT NULL,
  UNIQUE KEY `UK_i4uqyuoydnjwqdhwkgsxv5igx` (`doctors_user_id`),
  KEY `FKqpu1mv6pds879afuru3fllsqe` (`clinic_id`),
  CONSTRAINT `FKh77uaslv0k9ep3namy6v4xgi0` FOREIGN KEY (`doctors_user_id`) REFERENCES `doctor` (`user_id`),
  CONSTRAINT `FKqpu1mv6pds879afuru3fllsqe` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_doctors`
--

LOCK TABLES `clinic_doctors` WRITE;
/*!40000 ALTER TABLE `clinic_doctors` DISABLE KEYS */;
INSERT INTO `clinic_doctors` VALUES (1,500),(1,501),(1,502),(1,503),(1,504),(1,505),(1,506),(1,507),(1,508),(1,509),(1,510),(1,511),(1,512),(1,513),(1,514),(1,515),(1,516),(1,517),(1,518),(1,519),(2,520),(2,521),(2,522),(2,523),(2,524),(2,525),(2,526),(2,527),(2,528),(2,529),(2,530),(2,531),(2,532),(2,533),(2,534),(2,535),(2,536),(2,537),(2,538),(2,539),(3,540),(3,541),(3,542),(3,543),(3,544),(3,545),(3,546),(3,547),(3,548),(3,549),(3,550),(3,551),(3,552),(3,553),(3,554),(3,555),(3,556),(3,557),(3,558),(3,559),(4,560),(4,561),(4,562),(4,563),(4,564),(4,565),(4,566),(4,567),(4,568),(4,569),(4,570),(4,571),(4,572),(4,573),(4,574),(4,575),(4,576),(4,577),(4,578),(4,579),(5,580),(5,581),(5,582),(5,583),(5,584),(5,585),(5,586),(5,587),(5,588),(5,589),(5,590),(5,591),(5,592),(5,593),(5,594),(5,595),(5,596),(5,597),(5,598),(5,599),(6,600),(6,601),(6,602),(6,603),(6,604),(6,605),(6,606),(6,607),(6,608),(6,609),(6,610),(6,611),(6,612),(6,613),(6,614),(6,615),(6,616),(6,617),(6,618),(6,619),(7,620),(7,621),(7,622),(7,623),(7,624),(7,625),(7,626),(7,627),(7,628),(7,629),(7,630),(7,631),(7,632),(7,633),(7,634),(7,635),(7,636),(7,637),(7,638),(7,639),(8,640),(8,641),(8,642),(8,643),(8,644),(8,645),(8,646),(8,647),(8,648),(8,649),(8,650),(8,651),(8,652),(8,653),(8,654),(8,655),(8,656),(8,657),(8,658),(8,659),(9,660),(9,661),(9,662),(9,663),(9,664),(9,665),(9,666),(9,667),(9,668),(9,669),(9,670),(9,671),(9,672),(9,673),(9,674),(9,675),(9,676),(9,677),(9,678),(9,679),(10,680),(10,681),(10,682),(10,683),(10,684),(10,685),(10,686),(10,687),(10,688),(10,689),(10,690),(10,691),(10,692),(10,693),(10,694),(10,695),(10,696),(10,697),(10,698),(10,699),(11,700),(11,701),(11,702),(11,703),(11,704),(11,705),(11,706),(11,707),(11,708),(11,709),(11,710),(11,711),(11,712),(11,713),(11,714),(11,715),(11,716),(11,717),(11,718),(11,719),(12,720),(12,721),(12,722),(12,723),(12,724),(12,725),(12,726),(12,727),(12,728),(12,729),(12,730),(12,731),(12,732),(12,733),(12,734),(12,735),(12,736),(12,737),(12,738),(12,739),(13,740),(13,741),(13,742),(13,743),(13,744),(13,745),(13,746),(13,747),(13,748),(13,749),(13,750),(13,751),(13,752),(13,753),(13,754),(13,755),(13,756),(13,757),(13,758),(13,759),(14,760),(14,761),(14,762),(14,763),(14,764),(14,765),(14,766),(14,767),(14,768),(14,769),(14,770),(14,771),(14,772),(14,773),(14,774),(14,775),(14,776),(14,777),(14,778),(14,779),(15,780),(15,781),(15,782),(15,783),(15,784),(15,785),(15,786),(15,787),(15,788),(15,789),(15,790),(15,791),(15,792),(15,793),(15,794),(15,795),(15,796),(15,797),(15,798),(15,799),(16,800),(16,801),(16,802),(16,803),(16,804),(16,805),(16,806),(16,807),(16,808),(16,809),(16,810),(16,811),(16,812),(16,813),(16,814),(16,815),(16,816),(16,817),(16,818),(16,819),(17,820),(17,821),(17,822),(17,823),(17,824),(17,825),(17,826),(17,827),(17,828),(17,829),(17,830),(17,831),(17,832),(17,833),(17,834),(17,835),(17,836),(17,837),(17,838),(17,839),(18,42),(18,43),(18,44),(18,45),(18,46),(18,47),(18,48),(18,49),(18,50),(18,51),(18,52),(18,53),(18,54),(18,55),(18,56),(18,57),(18,58),(18,59),(18,60),(18,61),(19,62),(19,63),(19,64),(19,65),(19,66),(19,67),(19,68),(19,69),(19,70),(19,71),(19,72),(19,73),(19,74),(19,75),(19,76),(19,77),(19,78),(19,79),(19,80),(19,81),(20,82),(20,83),(20,84),(20,85),(20,86),(20,87),(20,88),(20,89),(20,90),(20,91),(20,92),(20,93),(20,94),(20,95),(20,96),(20,97),(20,98),(20,99),(20,100),(20,101),(21,102),(21,103),(21,104),(21,105),(21,106),(21,107),(21,108),(21,109),(21,110),(21,111),(21,112),(21,113),(21,114),(21,115),(21,116),(21,117),(21,118),(21,119),(21,120),(21,121),(22,122),(22,123),(22,124),(22,125),(22,126),(22,127),(22,128),(22,129),(22,130),(22,131),(22,132),(22,133),(22,134),(22,135),(22,136),(22,137),(22,138),(22,139),(22,140),(22,141);
/*!40000 ALTER TABLE `clinic_doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_grades`
--

DROP TABLE IF EXISTS `clinic_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_grades` (
  `clinic_id` bigint NOT NULL,
  `grades_grade_id` bigint NOT NULL,
  UNIQUE KEY `UK_m5sr6a6ci84hvp2w452dr7r1` (`grades_grade_id`),
  KEY `FKmaxm1umyvgcpoykg4a4x6m87p` (`clinic_id`),
  CONSTRAINT `FKexnak8jpjfr2yuhtcisbwt57y` FOREIGN KEY (`grades_grade_id`) REFERENCES `grade` (`grade_id`),
  CONSTRAINT `FKmaxm1umyvgcpoykg4a4x6m87p` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_grades`
--

LOCK TABLES `clinic_grades` WRITE;
/*!40000 ALTER TABLE `clinic_grades` DISABLE KEYS */;
INSERT INTO `clinic_grades` VALUES (18,2);
/*!40000 ALTER TABLE `clinic_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_nurses`
--

DROP TABLE IF EXISTS `clinic_nurses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_nurses` (
  `clinic_id` bigint NOT NULL,
  `nurses_user_id` bigint NOT NULL,
  UNIQUE KEY `UK_dc7vxavqkga87goc48fimu77j` (`nurses_user_id`),
  KEY `FK1rk8ba0b0in25xdj0cxs4ro6i` (`clinic_id`),
  CONSTRAINT `FK1rk8ba0b0in25xdj0cxs4ro6i` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`),
  CONSTRAINT `FKhtawskwhk61atdn8otugrq119` FOREIGN KEY (`nurses_user_id`) REFERENCES `nurse` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_nurses`
--

LOCK TABLES `clinic_nurses` WRITE;
/*!40000 ALTER TABLE `clinic_nurses` DISABLE KEYS */;
INSERT INTO `clinic_nurses` VALUES (1,67),(1,68),(2,69),(2,70),(3,71),(4,72),(5,73),(6,74),(6,75),(7,76),(7,77),(8,78),(8,79),(9,80),(9,81),(10,82),(10,83),(11,84),(12,85),(13,86),(13,87),(14,88),(15,89),(15,90),(16,91);
/*!40000 ALTER TABLE `clinic_nurses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_ordinations`
--

DROP TABLE IF EXISTS `clinic_ordinations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_ordinations` (
  `clinic_id` bigint NOT NULL,
  `ordinations_ordination_number` int NOT NULL,
  UNIQUE KEY `UK_jsalawnxll2nt1lsfuuvpl3qf` (`ordinations_ordination_number`),
  KEY `FKtguk3npkj9g9sc7ayf11o816n` (`clinic_id`),
  CONSTRAINT `FKa0so9opq4sdo4yrmfhfkh0nhr` FOREIGN KEY (`ordinations_ordination_number`) REFERENCES `ordination` (`ordination_number`),
  CONSTRAINT `FKtguk3npkj9g9sc7ayf11o816n` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_ordinations`
--

LOCK TABLES `clinic_ordinations` WRITE;
/*!40000 ALTER TABLE `clinic_ordinations` DISABLE KEYS */;
INSERT INTO `clinic_ordinations` VALUES (1,28),(2,16),(3,842),(3,843),(4,9),(4,24),(5,17),(5,21),(6,10),(6,25),(7,5),(8,6),(9,14),(9,22),(11,18),(12,1),(12,23),(12,26),(13,13),(13,15),(15,7),(15,12),(16,19),(17,2),(17,4),(18,20),(19,3),(19,8),(19,11);
/*!40000 ALTER TABLE `clinic_ordinations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinical_center`
--

DROP TABLE IF EXISTS `clinical_center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinical_center` (
  `clinic_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`clinic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinical_center`
--

LOCK TABLES `clinical_center` WRITE;
/*!40000 ALTER TABLE `clinical_center` DISABLE KEYS */;
INSERT INTO `clinical_center` VALUES (1,'DIV Clinical center');
/*!40000 ALTER TABLE `clinical_center` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinical_center_administrator`
--

DROP TABLE IF EXISTS `clinical_center_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinical_center_administrator` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `clinical_center_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKftcxgov4tt32vhqgialvr9bqk` (`clinical_center_id`),
  CONSTRAINT `FKftcxgov4tt32vhqgialvr9bqk` FOREIGN KEY (`clinical_center_id`) REFERENCES `clinical_center` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinical_center_administrator`
--

LOCK TABLES `clinical_center_administrator` WRITE;
/*!40000 ALTER TABLE `clinical_center_administrator` DISABLE KEYS */;
INSERT INTO `clinical_center_administrator` VALUES (1,'0 Faught Rd','Dallas (TX)','Haiti','a@a','Talitha','39690661539','Caffray','123','666-2599','MAIN_CLINICAL_CENTER_ADMINISTRATOR',1),(253,'Tomice Aleksic 25','Raska','Srbija','v@v','Vladimir','123456543','Vukovic','1234','0669074444','CLINICAL_CENTER_ADMINISTRATOR',1);
/*!40000 ALTER TABLE `clinical_center_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnosis` (
  `diagnosis_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `clinical_center_id` bigint DEFAULT NULL,
  `medical_report_diagnosis_id` bigint DEFAULT NULL,
  PRIMARY KEY (`diagnosis_id`),
  KEY `FKs8d13neuint6pwugwo3avvbxh` (`clinical_center_id`),
  KEY `FKtfnlo2gyo1x475504t75l662y` (`medical_report_diagnosis_id`),
  CONSTRAINT `FKs8d13neuint6pwugwo3avvbxh` FOREIGN KEY (`clinical_center_id`) REFERENCES `clinical_center` (`clinic_id`),
  CONSTRAINT `FKtfnlo2gyo1x475504t75l662y` FOREIGN KEY (`medical_report_diagnosis_id`) REFERENCES `medical_report` (`medical_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
INSERT INTO `diagnosis` VALUES (1,'DIAGNOSIS-719','DIAGNOSIS-MG',1,NULL),(2,'DIAGNOSIS-433','DIAGNOSIS-IR',1,NULL),(3,'DIAGNOSIS-636','DIAGNOSIS-IL',1,NULL),(4,'DIAGNOSIS-437','DIAGNOSIS-OH',1,NULL),(5,'DIAGNOSIS-089','DIAGNOSIS-AO',1,NULL),(6,'DIAGNOSIS-184','DIAGNOSIS-SX',1,NULL),(7,'DIAGNOSIS-071','DIAGNOSIS-BE',1,NULL),(8,'DIAGNOSIS-405','DIAGNOSIS-XA',1,NULL),(9,'DIAGNOSIS-460','DIAGNOSIS-IS',1,NULL),(10,'DIAGNOSIS-582','DIAGNOSIS-SR',1,NULL),(11,'DIAGNOSIS-998','DIAGNOSIS-NK',1,NULL),(12,'DIAGNOSIS-397','DIAGNOSIS-FR',1,NULL),(13,'DIAGNOSIS-362','DIAGNOSIS-UB',1,NULL),(14,'DIAGNOSIS-415','DIAGNOSIS-BC',1,NULL),(15,'DIAGNOSIS-168','DIAGNOSIS-YU',1,NULL),(16,'DIAGNOSIS-721','DIAGNOSIS-KU',1,NULL),(17,'DIAGNOSIS-682','DIAGNOSIS-RW',1,NULL),(18,'DIAGNOSIS-813','DIAGNOSIS-BR',1,NULL),(19,'DIAGNOSIS-675','DIAGNOSIS-VR',1,NULL),(20,'DIAGNOSIS-939','DIAGNOSIS-ZC',1,NULL),(21,'DIAGNOSIS-211','DIAGNOSIS-BM',1,NULL),(22,'DIAGNOSIS-943','DIAGNOSIS-HG',1,NULL),(23,'DIAGNOSIS-034','DIAGNOSIS-OE',1,NULL),(24,'DIAGNOSIS-792','DIAGNOSIS-TQ',1,NULL),(25,'DIAGNOSIS-689','DIAGNOSIS-ZN',1,NULL),(26,'DIAGNOSIS-888','TUMOR',1,NULL),(29,'DIAGNOSIS-777','Heart attack',1,NULL);
/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `clinic_id` bigint DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKaqgufpq4bfr4au915m6u0s4dm` (`clinic_id`),
  CONSTRAINT `FKaqgufpq4bfr4au915m6u0s4dm` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (42,'9 Linden Ridge Dr.','BAKU','Cayman Islands','d@d','Catherine','46772920530','Morgan','123','666-1691','DOCTOR',18,'ORTHOPEDIA'),(43,'4 Southfern Ct','Nagoya','Benin','h@h','Aoife','17081372684','Vostreys','123','666-2324','DOCTOR',18,'ORTHOPEDIA'),(44,'706 Wilder St.','Dalian','American Samoa','oo@oo','Erin','74834766591','Lawton','123','666-1280','DOCTOR',18,'GENERAL'),(45,'683 Eisenhower Avenue','Recife','Neutral Zone','NorbertJohnson5@telfort.dk','Rachael','68038873403','Phelps','Pass583','666-7204','DOCTOR',18,'GENERAL'),(46,'548 East 74th Street','BUCURESTI','Liberia','JohanGriffith2@gmail.com','Peggy','51960111479','Perilloux','Pass043','666-2413','DOCTOR',18,'CHEMICAL'),(47,'40 E. Marions Rd','Suining','New Caledonia','HansMorton@hotmail.nl','Andrea','29337054678','Bergdahl','Pass269','666-0895','DOCTOR',18,'CHEMICAL'),(48,'1093 Stony Field Rd','Maracaibo','Taiwan','GWhite@msn.co.uk','Margaret','72186360147','Bryant','Pass163','666-7143','DOCTOR',18,'PSIHOLOGY'),(49,'34 Elm Street','ANKARA','Belgium','RogerBrennan3@excite.org','Nate','85595611646','Naley','Pass037','666-2027','DOCTOR',18,'PSIHOLOGY'),(50,'20 Sky Park Circle','MONTEVIDEO','Macau','RJulieze1@kpn.cc','Lisa','11680408227','Millis','Pass846','666-0990','DOCTOR',18,'NEUROLOGICAL'),(51,'1415 Sky Park Circle','Zhongshan','Slovenia','MaddyWard3@mobileme.org','Sylvia','68364753196','Long','Pass369','666-3659','DOCTOR',18,'NEUROLOGICAL'),(52,'801 Prospect Hill','Kunming','Georgia','FredPerilloux5@live.org','Babet','45470730276','Oyler','Pass933','666-0374','DOCTOR',18,'PLASTICS'),(53,'5412 Tuckahoe St','BAKU','Bangladesh','Frans.Harder5@lycos.fr','Rachel','29635102674','Brumley','Pass708','666-5113','DOCTOR',18,'PLASTICS'),(54,'5769 Harrison rd','Faisalabad','Sierra Leone','BasVostreys@myspace.gov','Hiram','07953145704','Framus','Pass212','666-8568','DOCTOR',18,'CARDIO'),(55,'1 North Fuller Avenue','Shijiazhuang','French Guiana','Ton.Harness2@live.fr','Cloe','55272128790','Slocum','Pass412','666-2818','DOCTOR',18,'CARDIO'),(56,'5 N. Pine Grove','Qidong','Bulgaria','Peter.Seibel@live.dk','Siem','78558638507','Seibel','Pass570','666-1349','DOCTOR',18,'GENERAL'),(57,'13 Everglades Way','Donetsk','Guadeloupe','Bianca.Queen1@mymail.nl','Caitlin','60415658127','Nahay','Pass358','666-4164','DOCTOR',18,'GENERAL'),(58,'4 Charlotte Avenue','CAIRO','Virgin Islands (British)','J.Barbee@telefonica.com','Nicoline','39622323064','Lawton','Pass731','666-6100','DOCTOR',18,'GINECOLOGY'),(59,'467 Palmer Rd','Nagpur','Gabon','PeteLeGrand@yahoo.dk','Krystyna','25659827475','Jones','Pass498','666-7002','DOCTOR',18,'GINECOLOGY'),(60,'187 Broomhouse Rd','Lianyuan','America','x@x','Leonie','56459150232','Troher','123','666-8059','DOCTOR',18,'PULMOLOGY'),(61,'860 Madden Blvd.','Medellín','Saudi Arabia','G.Long3@live.no','Luka','59293067590','Willis','Pass746','666-7939','DOCTOR',18,'PULMOLOGY'),(62,'6238 Perimeter Dr.','Xiantao','Georgia','AnnNelson@msn.dk','Marta','26571234377','Ostanik','Pass804','666-0965','DOCTOR',19,'ORTHOPEDIA'),(63,'3 Regency Plaza','Tengzhou','Tonga','DMalone4@weboffice.fr','Jose','12034257280','Ladaille','Pass948','666-9959','DOCTOR',19,'ORTHOPEDIA'),(64,'932 Forest Windway','Qingdao','Tajikistan','CKoss5@web.no','Babet','84945372473','Carlos','Pass971','666-5651','DOCTOR',19,'GENERAL'),(65,'176 Linden Ridge Dr.','Taejon','Cook Islands','Ton.Freed@msn.org','Jamie','53600525927','Symms','Pass129','666-2939','DOCTOR',19,'GENERAL'),(66,'6664 Hill Street','Bursa','Spain','HansSpensley3@mobileme.cn','Frederik','46259498696','Morgan','Pass555','666-8299','DOCTOR',19,'CHEMICAL'),(67,'89 Freeman St','Anshan','St. Helena','R.Goodman@telfort.dk','Julia','92010731051','Naley','Pass669','666-1236','DOCTOR',19,'CHEMICAL'),(68,'236 Carroll Center Rd','Xiaoshan','Venezuela','HKingslan2@lycos.dk','Steve','75630548073','Chwatal','Pass853','666-2562','DOCTOR',19,'PSIHOLOGY'),(69,'6427 Springfield Ave','BUCURESTI','Djibouti','Fred.Wakefield1@web.be','Isaac','15603565118','Chwatal','Pass785','666-1193','DOCTOR',19,'PSIHOLOGY'),(70,'7383 Muzzatta Way','Delhi','US Minor Outlying Islands','JimAnderson@excite.us','Sammy','24303777692','Cramer','Pass999','666-4539','DOCTOR',19,'NEUROLOGICAL'),(71,'34 N. Central Ave.','Yuzhou','Latvia','MattBeckbau2@aol.no','Emma','26732654078','DeWilde','Pass596','666-9509','DOCTOR',19,'NEUROLOGICAL'),(72,'0425 N. Glenoaks Blvd','Rawalpindi','Uzbekistan','Nick.Huston@hotmail.it','Cameron','23979966347','Cohen','Pass819','666-1952','DOCTOR',19,'PULMOLOGY'),(73,'5469 Walsh Drive','Yixing','Tanzania','Rick.Chwatal@excite.gov','Nathan','05116201875','Van Dinter','Pass946','666-0648','DOCTOR',19,'PLASTICS'),(74,'24 N. Jackson Avenue','Ufa','Palau','LucyMenovosa@aol.es','Caroline','81581943301','Moon','Pass232','666-1854','DOCTOR',19,'PLASTICS'),(75,'2 Willingdon Ave','BANGKOK','Poland','Matt.Suszantor2@excite.de','Rachel','99081842740','Koch','Pass605','666-7642','DOCTOR',19,'CARDIO'),(76,'81 Miranda Drive','Vienna','Georgia','H.Suszantor4@telfort.es','Nicoline','51205506358','Toreau','Pass383','666-1002','DOCTOR',19,'CARDIO'),(77,'772 Devonshire Rd','SANTIAGO','Bulgaria','Lucas.Hoogbandt1@web.de','Elin','25074736326','Meterson','Pass911','666-9189','DOCTOR',19,'GENERAL'),(78,'2595 Forest Windway','Detroit (MI)','Luxembourg','Ann.Brown@hotmail.de','Bill','42666390021','Wood','Pass701','666-6630','DOCTOR',19,'GENERAL'),(79,'659 Brookfields','Recife','Suriname','Gretsj.Carlos3@weboffice.com','Margarita','04557119523','Nelson','Pass332','666-7558','DOCTOR',19,'GINECOLOGY'),(80,'4461 Gascony Pl.','Shiraz','Taiwan','B.Pekagnan3@gmail.de','Diego','19391089119','Liddle','Pass252','666-5099','DOCTOR',19,'GINECOLOGY'),(81,'405 Village Road East','HARARE','Armenia','AmberMenovosa5@myspace.org','Sergio','09388270070','Ratliff','Pass293','666-9966','DOCTOR',19,'PULMOLOGY'),(82,'3 Floribunda Ave','BERLIN','Antarctica','PetraIonescu@yahoo.de','Lia','79272802480','Mariojnisk','Pass933','666-7668','DOCTOR',20,'ORTHOPEDIA'),(83,'91 N Broadway','Toronto','Kenya','Bas.Hulshof4@freeweb.es','Jace','38044536481','DeWald','Pass956','666-6953','DOCTOR',20,'ORTHOPEDIA'),(84,'8651 Durness Place','TBILISI','Bangladesh','E.Cappello@kpn.com','Peg','23527752734','Mayberry','Pass837','666-9096','DOCTOR',20,'GENERAL'),(85,'4 ZW Bruce Street','PARIS','Wallis and Futuna Islands','ABeckbau4@telfort.cc','Carlos','27116450438','Yinger','Pass375','666-8253','DOCTOR',20,'GENERAL'),(86,'13 N Broadway','La Matanza','Grenada','Ann.Lawton@yahoo.nl','Piotr','60488518821','Gildersleeve','Pass853','666-2402','DOCTOR',20,'CHEMICAL'),(87,'8713 Meadow Walk','SEOUL','Iceland','Bill.Conley@mail.net','Marta','94431757288','Wakefield','Pass204','666-0452','DOCTOR',20,'CHEMICAL'),(88,'0 Redwood Ave.','Hiroshima','Liberia','BasKing@myspace.co.uk','Camille','57575375304','Herzog','Pass959','666-1480','DOCTOR',20,'PSIHOLOGY'),(89,'3733 Rockville Pike','Kyoto','Sri Lanka','J.Archer@live.de','Marcin','81829346355','DelRosso','Pass596','666-4112','DOCTOR',20,'PSIHOLOGY'),(90,'96 Glenridge Drive','Yulin','New Caledonia','BrentOyler@web.dk','Scottie','13026814902','Brown','Pass010','666-4481','DOCTOR',20,'NEUROLOGICAL'),(91,'3 N. Deerwood Drive','Lucknow','Belarus','K.Willis@myspace.no','Caroline','31602266754','Cross','Pass229','666-9246','DOCTOR',20,'NEUROLOGICAL'),(92,'41 Elmoasi Street','Esfahan','Angola','Pablo.Ditmanen@freeweb.cc','Theo','09971668257','Julieze','Pass915','666-8810','DOCTOR',20,'PLASTICS'),(93,'538 Lakewood Drive','NAIROBI','Slovak Republic','JackReyes@myspace.es','Charlotte','55449355939','Muench','Pass722','666-2674','DOCTOR',20,'PLASTICS'),(94,'947 Stewart Ave','LONDON','Pitcairn','H.Van Toorenbeek3@yahoo.cc','Sjef','85361247743','van Goes','Pass073','666-0572','DOCTOR',20,'CARDIO'),(95,'746 Hill Street','Pueblade Zaragoza','Netherlands','M.Braconi2@libero.net','Lucia','00666418934','Van Toorenbeek','Pass960','666-8632','DOCTOR',20,'CARDIO'),(96,'689 W. Sunnyside Ave.','Porto Alegre','Guatemala','Freddy.Griffith@hotmail.dk','Caitlin','97227757249','Caffray','Pass372','666-3385','DOCTOR',20,'GENERAL'),(97,'886 North Irvington','CAIRO','Myanmar','Trees.Ionescu1@gmail.cn','Netty','67560261859','Yinger','Pass035','666-7056','DOCTOR',20,'GENERAL'),(98,'494 Windbrook Crs','ANKARA','Korea (North)','JMartin@aol.dk','Bert','53546808778','Turk','Pass180','666-1296','DOCTOR',20,'GINECOLOGY'),(99,'2276 W. Sunnyside Ave.','Sydney','Faroe Islands','HankKidd5@mymail.cn','Kees','83395818499','Kellock','Pass779','666-1571','DOCTOR',20,'GINECOLOGY'),(100,'4 ZW Bruce Street','Perm','Ukraine','M.Storrs3@aol.net','Ton','47932602383','White','Pass042','666-6917','DOCTOR',20,'PULMOLOGY'),(101,'761 New London Street','Kyoto','Madagascar','TreesPoplock@myspace.cn','Sid','88138547275','Marra','Pass037','666-3251','DOCTOR',20,'PULMOLOGY'),(102,'18 S. Brockbank Drive','Shiraz','Barbados','H.White4@live.gov','Ellie','76877351625','LeGrand','Pass565','666-2824','DOCTOR',21,'ORTHOPEDIA'),(103,'5977 Willingdon Ave','Calcutta','Aruba','Dick.Goodnight1@gmail.co.uk','Ivan','18205600213','Slocum','Pass658','666-5452','DOCTOR',21,'ORTHOPEDIA'),(104,'56 Freeman St','Yulin','Swaziland','BrentHerzog2@yahoo.com','Annie','40306079620','Van Toorenbeek','Pass533','666-7120','DOCTOR',21,'GENERAL'),(105,'657 Perimeter Dr.','Suizhou','Cayman Islands','PVan Toorenbeek@web.be','Lea','08965201810','Moore','Pass480','666-4766','DOCTOR',21,'GENERAL'),(106,'7090 Village Road South','Ludhiana','Anguilla','HankHamilton@live.cn','Julia','56463546266','Scheffold','Pass580','666-9608','DOCTOR',21,'CHEMICAL'),(107,'2870 Meadow Walk','Jaipur','Mayotte','L.Fernandez@hotmail.us','Ada','58574694149','Weaver','Pass680','666-3552','DOCTOR',21,'CHEMICAL'),(108,'047 Sky Park Circle','Kazan','Syria','Johan.Moreau2@hotmail.de','Betty','35411229269','Warner','Pass884','666-8183','DOCTOR',21,'PSIHOLOGY'),(109,'98 Simpson Place','Zhanjiang','Brazil','KayBugno@msn.ca','Dorothy','39043942655','Perilloux','Pass683','666-5303','DOCTOR',21,'PSIHOLOGY'),(110,'93 N. Deerwood Place','Cali','Poland','David.Ijukop1@myspace.cc','Julie','51319041082','Bruno','Pass455','666-1774','DOCTOR',21,'NEUROLOGICAL'),(111,'3570 Medford St','Shanghai','United States','KSchlee@libero.es','Joshua','97173110870','Griffioen','Pass540','666-4468','DOCTOR',21,'NEUROLOGICAL'),(112,'2 N. Jackson Street','Ludhiana','Australia','YDeleo4@mobileme.be','Alba','25849955717','Wilson','Pass526','666-9809','DOCTOR',21,'PLASTICS'),(113,'9 Everglades Road','Montréal','Yugoslavia','Will.Watson@mymail.co.uk','Izzy','46481835535','Yinger','Pass855','666-7348','DOCTOR',21,'PLASTICS'),(114,'66 Village Road South','Suining','Hong Kong','Frank.Archer@telfort.gov','Amber','01674020414','Bernstein','Pass323','666-5738','DOCTOR',21,'CARDIO'),(115,'23 E. Plumb Lane','KABUL','Saudi Arabia','DanaBotsik@libero.fr','Oscar','20989933094','Bright','Pass035','666-0232','DOCTOR',21,'CARDIO'),(116,'5 Fontanoso Way','Luoyang','Maldives','I.Herrin@freeweb.fr','Godfrey','96038619877','Wolpert','Pass566','666-2615','DOCTOR',21,'GENERAL'),(117,'24 Unbridle Way','Ufa','Namibia','Nigel.Roche2@gmail.be','Milan','54428744613','Richter','Pass961','666-7694','DOCTOR',21,'GENERAL'),(118,'4332 Carnaby Creek','Weifang','Czech Republic','G.Dulisse@mail.us','Mike','99093188498','Ijukop','Pass807','666-1469','DOCTOR',21,'GINECOLOGY'),(119,'2 Calle Fidelidad','Suining','Burkina Faso','Rick.Lee@weboffice.dk','Betty','64943482969','Bright','Pass268','666-9655','DOCTOR',21,'GINECOLOGY'),(120,'9727 Pacella Park Drive','Semarang','Norfolk Island','William.Pickering@lycos.nl','Duncan','60462249508','Imhoff','Pass462','666-1533','DOCTOR',21,'PULMOLOGY'),(121,'901 Hill Street','Rizhao','Swaziland','JohanNahay@dolfijn.de','Cristian','09056116851','DeBuck','Pass742','666-2632','DOCTOR',21,'PULMOLOGY'),(122,'460 Calle Fidelidad','TEHRAN','Guyana','Frank.Queen@telfort.net','Edwina','23406625604','Pekagnan','Pass978','666-6684','DOCTOR',22,'ORTHOPEDIA'),(123,'9410 Meadow Walk','Giza','Kiribati','Dick.Cohen@mobileme.es','Johnny','92753024553','Vanderoever','Pass467','666-0498','DOCTOR',22,'ORTHOPEDIA'),(124,'428 Francis Place','Fulin','Honduras','PeterWhite1@gmail.gov','Mathias','76468249822','Alspaugh','Pass052','666-3516','DOCTOR',22,'GENERAL'),(125,'5037 Meadow Walk','KUALA LUMPUR','Niger','Bianca.Emerson@yahoo.fr','Rogier','55341441716','Willis','Pass263','666-5879','DOCTOR',22,'GENERAL'),(126,'6904 E. Marions Rd','TOKYO','Austria','BasHulshof@gawab.be','Nathan','64398175374','Thompson','Pass284','666-9045','DOCTOR',22,'CHEMICAL'),(127,'436 Wilder St.','Pingdu','Virgin Islands (British)','Ton.Sterrett@mymail.no','Alva','56256878040','Hancock','Pass576','666-3830','DOCTOR',22,'CHEMICAL'),(128,'58 Wicksicam Ave','Quezon City','St. Helena','R.Hulshof@msn.be','Kimberly','83132602367','Jiminez','Pass288','666-5193','DOCTOR',22,'PSIHOLOGY'),(129,'7 Perimeter Dr.','ROMA','Barbados','Bart.Hummel@msn.nl','Ashley','49897803570','Herring','Pass126','666-6628','DOCTOR',22,'PSIHOLOGY'),(130,'978 Palos Verdes Mall','Neijiang','Austria','Ton.Imhoff4@myspace.co.uk','Jean','65893449545','Herzog','Pass946','666-5503','DOCTOR',22,'NEUROLOGICAL'),(131,'419 Glenridge Drive','Almaty','Cape Verde','G.Jones5@freeweb.nl','Mariska','64236762342','Carlos','Pass488','666-0994','DOCTOR',22,'NEUROLOGICAL'),(132,'67 Pacella Park Drive','Bombay','Austria','Pauline.Walker@gawab.co.uk','Talita','79025630724','Huffsmitt','Pass210','666-7270','DOCTOR',22,'PLASTICS'),(133,'325 Station Street','Nizhny Novgorod','Nauru','LindsyGuethlein@myspace.nl','Fons','43146767246','Jessen','Pass283','666-4036','DOCTOR',22,'PLASTICS'),(134,'6 Waterford Blvd','Taegu','Tokelau','KimKorkovski4@weboffice.es','Lea','79061926350','Uprovski','Pass676','666-1380','DOCTOR',22,'CARDIO'),(135,'5 New London Street','Jilin','US Minor Outlying Islands','Y.Poissant@telfort.cn','Liza','91067264587','Lee','Pass641','666-9662','DOCTOR',22,'CARDIO'),(136,'8347 ZW Bruce Street','MINSK','Paraguay','PSuszantor@dolfijn.org','Sjanie','10150768279','Bergdahl','Pass053','666-0226','DOCTOR',22,'GENERAL'),(137,'2 Buckthorn Lane','Jilin','Uganda','FrankMcCormick3@freeweb.nl','Anton','30106937900','Trainor','Pass028','666-5107','DOCTOR',22,'GENERAL'),(138,'4729 NW 111th Loop','BOGOTA','Bahamas','FrankPrior3@mobileme.be','Hannah','96244430291','Beckbau','Pass266','666-6515','DOCTOR',22,'GINECOLOGY'),(139,'17 Bryant Street','Ludhiana','Egypt','Peter.Tudisco@telefonica.cn','Ben','87514205242','Oyler','Pass828','666-4803','DOCTOR',22,'GINECOLOGY'),(140,'7310 E. Broward Blvd.','Guikong','Bolivia','GRichter3@mobileme.ca','Jane','56115321155','Wong','Pass584','666-0248','DOCTOR',22,'PULMOLOGY'),(141,'2671 Acorn Dr.','Qiqihaer','Svalbard and Jan Mayen Islands','Leo.Sanders4@telfort.es','Ann','88123582751','Hardoon','Pass103','666-8788','DOCTOR',22,'PULMOLOGY'),(500,'6383 New Michigan Rd','Ningbo','Rwanda','Pablo.Bugno4@web.co.uk','Jozef','17996449735','Queen','Pass754','666-1231','DOCTOR',1,'ORTHOPEDIA'),(501,'03 Palmer Rd','MONTEVIDEO','Saint Lucia','MilanDittrich@mymail.gov','Phil','29140624756','Bitmacs','Pass367','666-0304','DOCTOR',1,'ORTHOPEDIA'),(502,'17 University ave.','Surabaya','Reunion','E.Pickering@msn.de','Krzysztof','46653352210','Mitchell','Pass999','666-2580','DOCTOR',1,'GENERAL'),(503,'59 Gascony Pl.','Cali','Bangladesh','P.Scheffold3@web.nl','Nico','14313733514','Hankins','Pass233','666-4005','DOCTOR',1,'GENERAL'),(504,'43 N. Glenoaks Blvd','Vadodara','Egypt','Y.Freeman1@telefonica.fr','PieterJan','83922396675','Krutkov','Pass493','666-3612','DOCTOR',1,'CHEMICAL'),(505,'6327 W. Broadway','Leiyang','Japan','Jean.Friedman2@aol.es','Florian','76143581825','Cragin','Pass324','666-7195','DOCTOR',1,'CHEMICAL'),(506,'796 N. Sherman Dr.','HARARE','Mozambique','Bas.Mayberry@lycos.dk','Scott','12377096783','Anderson','Pass487','666-6753','DOCTOR',1,'PSIHOLOGY'),(507,'2 Chehalis Road','TBILISI','Czechoslovakia (former)','MadeleinVanderoever@mymail.de','Margarita','50526782311','Ijukop','Pass064','666-1450','DOCTOR',1,'PSIHOLOGY'),(508,'7084 University ave.','La Matanza','Israel','Trees.Brisco2@mymail.dk','Sara','14875270989','Hopper','Pass556','666-6296','DOCTOR',1,'NEUROLOGICAL'),(509,'2 Craftsland Road','Multan','Albania','JamesRauch5@yahoo.es','Sam','52979477971','Jones','Pass852','666-7872','DOCTOR',1,'NEUROLOGICAL'),(510,'20 South Wacker Dr.','Xintai','Belize','M.Weinstein4@telefonica.it','Rolla','55761964161','Reyes','Pass590','666-4374','DOCTOR',1,'PLASTICS'),(511,'010 Wellington Dr.','GUATEMALA CITY','Brazil','Pauline.Walker3@mobileme.org','Anton','44675458714','Bernstein','Pass197','666-4294','DOCTOR',1,'PLASTICS'),(512,'20 Hill Street','Recife','Guam','B.Novratni@live.ca','Barbara','44485311254','Weinstein','Pass613','666-8803','DOCTOR',1,'CARDIO'),(513,'1 Nortman Blvd','PYONGYANG','Seychelles','ILong@msn.it','Jurre','91828767515','Hopper','Pass726','666-0802','DOCTOR',1,'CARDIO'),(514,'3922 Brandon Marshall Street','Magelang','Ghana','VRoche4@gawab.cc','Philip','32774874960','Lawton','Pass562','666-0988','DOCTOR',1,'GENERAL'),(515,'6629 Printing House Yard','HARARE','Marshall Islands','P.Zia@excite.cn','Mikkel','42477960355','Troher','Pass264','666-2710','DOCTOR',1,'GENERAL'),(516,'457 Springfield Ave','Gongzhuling','Singapore','Maarten.Crocetti@mail.us','Matt','92423861858','Massingill','Pass202','666-3905','DOCTOR',1,'GINECOLOGY'),(517,'851 Main St.','Toronto','Mali','EQueen4@lycos.de','Sigrid','46281186604','Moore','Pass119','666-3157','DOCTOR',1,'GINECOLOGY'),(518,'2087 N. Deerwood Avenue','Omdurman','Laos','PeterSlocum@live.net','Pip','62352253062','Huffsmitt','Pass748','666-8515','DOCTOR',1,'PULMOLOGY'),(519,'3 Robin Lane','Dnepropetrovsk','Bulgaria','HKingslan@mymail.us','Ashley','07137173253','Dean','Pass862','666-0765','DOCTOR',1,'PULMOLOGY'),(520,'597 Terminal Way','Chicago','Nigeria','R.Julieze5@yahoo.com','Martina','95278146880','Dulisse','Pass028','666-1288','DOCTOR',2,'ORTHOPEDIA'),(521,'1 Floribunda Ave','Abidjan','Jordan','Norbert.Jackson4@hotmail.it','Peter','07150887908','Anderson','Pass467','666-7794','DOCTOR',2,'ORTHOPEDIA'),(522,'08 Regency Plaza','Bhopal','Zambia','TreesBergdahl@live.es','Wilma','69543840335','Naff','Pass020','666-9736','DOCTOR',2,'GENERAL'),(523,'1335 Penny Lane','San Antonio (TX)','Gibraltar','FredWatson@mail.gov','Joe','35607965122','DeBerg','Pass533','666-5063','DOCTOR',2,'GENERAL'),(524,'10 Warren Ave','Barranquilla','Andorra','Pierre.Olson3@lycos.ca','Tonnie','06624111651','Pierce','Pass060','666-4237','DOCTOR',2,'CHEMICAL'),(525,'5332 S. Maryland Ave','Handan','Svalbard and Jan Mayen Islands','HankEvans5@yahoo.no','Carla','92351184333','Uitergeest','Pass841','666-2499','DOCTOR',2,'CHEMICAL'),(526,'2 Danielle Ct.','Yixing','Denmark','ESeibel@live.gov','Martina','42088793072','Brumley','Pass692','666-4135','DOCTOR',2,'PSIHOLOGY'),(527,'86 Gilbert Street','Almaty','Madagascar','Hank.Cappello2@excite.dk','Teun','01171366964','Arnold','Pass436','666-5177','DOCTOR',2,'PSIHOLOGY'),(528,'746 Chinook Dr.','Dar es Salaam','Kyrgyzstan','LindsyMuench@live.de','Zofia','75287787732','Brown','Pass847','666-0243','DOCTOR',2,'NEUROLOGICAL'),(529,'4 Charlotte Avenue','Barranquilla','Kyrgyzstan','Roy.van der Laar@aol.cn','Juan','81387345759','Massingill','Pass099','666-2814','DOCTOR',2,'NEUROLOGICAL'),(530,'0622 University ave.','Caloocan','Gambia','G.Emerson@telefonica.it','Elzbieta','85793605845','Forsberg','Pass130','666-6508','DOCTOR',2,'PLASTICS'),(531,'5487 Freeman St','Jimo','Montserrat','S.Makelaar@kpn.cn','Nick','46994479588','Stockton','Pass854','666-5055','DOCTOR',2,'PLASTICS'),(532,'8 Harrison rd','PARIS','Grenada','BillRoyal@hotmail.es','Matthijs','41985982072','Baltec','Pass569','666-3828','DOCTOR',2,'CARDIO'),(533,'9 Trenton Avenue','AMMAN','Armenia','IPlantz@mail.com','Sara','57328969546','Braconi','Pass795','666-5446','DOCTOR',2,'CARDIO'),(534,'0882 Grassmere Avenue','Prague','Sri Lanka','Bill.Young5@aol.dk','Iris','81576149046','Makelaar','Pass158','666-6978','DOCTOR',2,'GENERAL'),(535,'49 North Fuller Avenue','Nanchang','Mali','S.Mariojnisk5@dolfijn.cc','Jack','10667823451','Voigt','Pass641','666-3401','DOCTOR',2,'GENERAL'),(536,'71 Centerville Avenue','Kyoto','Korea (South)','RichardPierce@telfort.cc','Lisa','22676467686','McDaniel','Pass890','666-1034','DOCTOR',2,'GINECOLOGY'),(537,'63 S. Maryland Ave','Suining','Canada','Petra.Malone@yahoo.com','Co','21507513144','Ijukop','Pass968','666-4940','DOCTOR',2,'GINECOLOGY'),(538,'3023 Devonshire Rd','Dongguan','Philippines','PChwatal4@yahoo.no','Simon','75859127293','Sharp','Pass166','666-6531','DOCTOR',2,'PULMOLOGY'),(539,'41 Village Road South','Macheng','Ecuador','Annvan Dijk5@hotmail.cn','Sally','08772001302','Dulisse','Pass795','666-7843','DOCTOR',2,'PULMOLOGY'),(540,'49 Jefferson Road','ROMA','Great Britain (UK)','FemkeThaler5@mymail.cn','William','05123030860','Lannigham','Pass047','666-6352','DOCTOR',3,'ORTHOPEDIA'),(541,'2950 N. Sherman Dr.','Chifeng','Bolivia','RickHarder@aol.dk','Ciska','07926272016','Ratliff','Pass512','666-0697','DOCTOR',3,'ORTHOPEDIA'),(542,'3 Danielle Ct.','Gongzhuling','Luxembourg','T.Brennan@myspace.net','Cian','26059057500','Ditmanen','Pass564','666-5135','DOCTOR',3,'GENERAL'),(543,'7 Sulgrave Ave','Weifang','Nauru','OttoToreau5@msn.dk','Caitlin','40077646022','Mariojnisk','Pass519','666-3229','DOCTOR',3,'GENERAL'),(544,'31 Everglades Road','Medan','Egypt','HankMiller@excite.nl','Suzanne','74526733972','Braconi','Pass258','666-9726','DOCTOR',3,'CHEMICAL'),(545,'35 Redwood Ave.','Suqian','Croatia (Hrvatska)','LindsyCain2@weboffice.be','Maaike','44126588468','Arden','Pass139','666-4181','DOCTOR',3,'CHEMICAL'),(546,'85 Vanderheck','Kawasaki','Puerto Rico','FransMorgan@excite.cc','Luka','39621253342','Dulisse','Pass708','666-1061','DOCTOR',3,'PSIHOLOGY'),(547,'0836 S. Riverside Plaza','Inchon','Haiti','Johan.DeWald2@live.es','Christa','84296973585','Wakefield','Pass497','666-7089','DOCTOR',3,'PSIHOLOGY'),(548,'80 Reeves Street','Nanning','Burundi','Nick.Freed@mymail.org','Marco','28799289819','King','Pass765','666-7783','DOCTOR',3,'NEUROLOGICAL'),(549,'81 W. Sunnyside Ave.','Hiroshima','Iraq','NickCaouette@gawab.us','Toon','52764540034','Manson','Pass910','666-3624','DOCTOR',3,'NEUROLOGICAL'),(550,'404 Eastman Drive','Xintai','Somalia','L.Anderson@freeweb.nl','Caroline','03932998614','Anderson','Pass523','666-9196','DOCTOR',3,'PLASTICS'),(551,'3 Farquar Ave','Fulin','Botswana','Richard.Poplock5@libero.nl','Hugo','24952536140','Otto','Pass499','666-0828','DOCTOR',3,'PLASTICS'),(552,'226 Mount Helena Av.','Fulin','Lesotho','Peter.Weaver5@hotmail.cn','Co','36824207612','Climent','Pass597','666-5314','DOCTOR',3,'CARDIO'),(553,'8649 Calle Fidelidad','Maracaibo','Rwanda','HansOverton@gmail.de','Marek','67285784435','Wilson','Pass421','666-5414','DOCTOR',3,'CARDIO'),(554,'8777 N. Glenoaks Blvd','Belém','Finland','Hans.Waddell@lycos.de','Susan','69882177969','Sharp','Pass377','666-3070','DOCTOR',3,'GENERAL'),(555,'890 North Irvington','Zhucheng','Cote D\'Ivoire (Ivory Coast)','L.Uitergeest@kpn.ca','Maggie','49671886274','Zapetis','Pass855','666-1285','DOCTOR',3,'GENERAL'),(556,'246 Windbrook Crs','BUENOS AIRES','Wallis and Futuna Islands','Frans.Green2@weboffice.net','Robert','34424333010','Bertelson','Pass952','666-2696','DOCTOR',3,'GINECOLOGY'),(557,'5606 Crookston Lane','Tianjin','St. Helena','K.Herrin4@lycos.ca','Cecilie','41584012732','Evans','Pass628','666-2824','DOCTOR',3,'GINECOLOGY'),(558,'01 Serena Road','LUANDA','Myanmar','DArnold@freeweb.es','Joe','32884562314','Nelson','Pass734','666-1459','DOCTOR',3,'PULMOLOGY'),(559,'884 Springfield Ave','Karachi','Gabon','Ton.Laudanski@mail.co.uk','Jozef','64561807198','Praeger','Pass612','666-9166','DOCTOR',3,'PULMOLOGY'),(560,'30 Barrenger Dr.','Samara','Malaysia','MattijsCliment@gmail.de','Cathy','97937006752','Anderson','Pass020','666-6639','DOCTOR',4,'ORTHOPEDIA'),(561,'894 Willowcrest','Ruian','Mauritania','HankSlater@myspace.us','Chuck','75356358060','Waddell','Pass204','666-1741','DOCTOR',4,'ORTHOPEDIA'),(562,'4598 University ave.','Tegal','Eritrea','TWooten1@libero.it','Lukas','18774156823','Zimmerman','Pass284','666-7668','DOCTOR',4,'GENERAL'),(563,'73 Swan Way','SEOUL','Bermuda','Maddyvan Dijk1@telfort.co.uk','Anthony','20592339594','Freed','Pass958','666-8355','DOCTOR',4,'GENERAL'),(564,'162 North Irvington','JAKARTA','New Zealand (Aotearoa)','HankOrcutt@hotmail.fr','John','43062286573','Ratliff','Pass239','666-9377','DOCTOR',4,'CHEMICAL'),(565,'33 Willowcrest','BUDAPEST','Myanmar','FrankMorton@mail.be','Maja','97834866932','Robbins','Pass351','666-8485','DOCTOR',4,'CHEMICAL'),(566,'3264 High Palms Ave','Pueblade Zaragoza','France','FrankStockton@web.ca','Steph','94444885967','Dean','Pass400','666-3225','DOCTOR',4,'PSIHOLOGY'),(567,'352 Meadow Walk','Nizhny Novgorod','Kiribati','Femke.Hollman@excite.cc','Amber','50827755650','Cramer','Pass799','666-7762','DOCTOR',4,'PSIHOLOGY'),(568,'491 Canyon Crest Drive','Fushun','Jamaica','P.Stockton3@libero.nl','Mariska','37649586953','Knight','Pass517','666-2924','DOCTOR',4,'NEUROLOGICAL'),(569,'4654 Village Road East','Neijiang','Sweden','H.Wolpert1@kpn.dk','Emily','55262959733','Poissant','Pass895','666-4319','DOCTOR',4,'NEUROLOGICAL'),(570,'4 Victoria Street','Yixing','Yugoslavia','Kim.Visentini@mail.nl','Sam','24202285487','Wolpert','Pass438','666-5817','DOCTOR',4,'PLASTICS'),(571,'7 Copeland Avenue','Tianshui','Dominican Republic','EmmaWhitehurst@mail.nl','Peggy','38315285502','Plantz','Pass550','666-6875','DOCTOR',4,'PLASTICS'),(572,'420 S. Riverside Plaza','Shenyang','Christmas Island','BillJessen3@mail.net','Alice','62976959785','Koss','Pass298','666-1527','DOCTOR',4,'CARDIO'),(573,'3699 Elm Waterford Drive','Kalyan','El Salvador','Hans.Antonucci@kpn.com','Mart','52060076109','Nahay','Pass946','666-1212','DOCTOR',4,'CARDIO'),(574,'8341 Palos Verdes Mall','New York (NY)','American Samoa','EmmaFramus3@mymail.nl','Rogier','29598821730','Helbush','Pass236','666-6124','DOCTOR',4,'GENERAL'),(575,'6 Meadow Walk','Hefei','Cape Verde','Ton.Morgan2@live.us','Krzysztof','74431540245','Reyes','Pass086','666-7882','DOCTOR',4,'GENERAL'),(576,'87 Charlotte Avenue','Ujung Pandang','Cocos (Keeling) Islands','OttoSuszantor4@msn.be','Bas','22467145481','Pyland','Pass424','666-5007','DOCTOR',4,'GINECOLOGY'),(577,'0125 Warwick Square','Taejon','Germany','RGreen@gmail.es','Bengie','23716348089','Nadalin','Pass975','666-4831','DOCTOR',4,'GINECOLOGY'),(578,'52 E. Plumb Lane','Changde','Yugoslavia','AMarkovi2@kpn.it','Tyler','22135234839','Franklin','Pass870','666-2741','DOCTOR',4,'PULMOLOGY'),(579,'2749 Sky Park Circle','Yixing','Kuwait','EmmaBernstein@gmail.co.uk','Marty','20908766854','Mitchell','Pass664','666-9941','DOCTOR',4,'PULMOLOGY'),(580,'51 Highland Drive','Qidong','Yemen','William.Daughtery@telfort.co.uk','Malgorzata','27261217661','Rivers','Pass671','666-5319','DOCTOR',5,'ORTHOPEDIA'),(581,'993 University ave.','Algiers','Ghana','RMiller3@hotmail.nl','Joe','03895345999','Millikin','Pass134','666-1916','DOCTOR',5,'ORTHOPEDIA'),(582,'992 Colette street','SEOUL','Uzbekistan','Jake.Lawton@freeweb.cc','Isaac','77638206681','Watson','Pass039','666-4106','DOCTOR',5,'GENERAL'),(583,'8997 Corporate Drive','Nanchang','Canada','SJohnson@web.com','Rik','69902489847','Sterrett','Pass648','666-6277','DOCTOR',5,'GENERAL'),(584,'580 Meadow Walk','Milano','Estonia','YGibson3@mymail.it','Ton','00709619957','Davis','Pass552','666-4609','DOCTOR',5,'CHEMICAL'),(585,'2 Maxwell Ave.','Huainan','Dominica','P.Moore3@kpn.cn','Olivia','93770776013','Duvall','Pass526','666-3717','DOCTOR',5,'CHEMICAL'),(586,'94 E. Marions Rd','Leshan','Martinique','Ben.Uprovski5@lycos.it','Christian','60635616624','van Doorn','Pass943','666-4351','DOCTOR',5,'PSIHOLOGY'),(587,'3243 Village Road South','Gujranwala','Maldives','R.Watson@mymail.fr','Richard','26551792376','Herzog','Pass077','666-3907','DOCTOR',5,'PSIHOLOGY'),(588,'05 Waterford Blvd','Haerbin','Romania','BKoss2@mymail.fr','Shermie','79321197352','Roche','Pass858','666-5419','DOCTOR',5,'NEUROLOGICAL'),(589,'9813 Roan Lane','Havanna','Bahamas','George.Brennan2@gawab.net','Maaike','52106278564','Gerschkow','Pass106','666-1331','DOCTOR',5,'NEUROLOGICAL'),(590,'3 Village Road East','Qinzhou','Nauru','B.Julieze3@telfort.es','Katie','83781193627','Shapiro','Pass643','666-1921','DOCTOR',5,'PLASTICS'),(591,'19 Rockville Pike','Bandung','Yemen','AGriffioen@mail.fr','Vincent','81597957456','Sharp','Pass887','666-8444','DOCTOR',5,'PLASTICS'),(592,'21 Fairfield Road','Vadodara','China','BartReames3@mymail.gov','Leo','86540352198','Pyland','Pass586','666-5390','DOCTOR',5,'CARDIO'),(593,'9 King George Hwy','GUATEMALA CITY','Maldives','J.Harness4@myspace.cn','Lisa','11183801625','Wood','Pass537','666-5291','DOCTOR',5,'CARDIO'),(594,'1 Village Road South','Yuzhou','New Caledonia','D.Crocetti3@mymail.us','Luke','40276000140','Malone','Pass430','666-8392','DOCTOR',5,'GENERAL'),(595,'1355 Buckthorn Lane','San Diego (CA)','Guadeloupe','Carla.Pensec4@gawab.no','Niklas','24940721785','Byrnes','Pass196','666-1572','DOCTOR',5,'GENERAL'),(596,'7714 Harrison rd','Ahmedabad','Kenya','Freddy.Helfrich@hotmail.dk','Rachael','38499791170','Foreman','Pass629','666-7392','DOCTOR',5,'GINECOLOGY'),(597,'6 Ferro Dr','San Diego (CA)','Virgin Islands (British)','WillTudisco@live.dk','Alice','94169140601','Walker','Pass219','666-8474','DOCTOR',5,'GINECOLOGY'),(598,'02 Nortman Blvd','Huainan','Denmark','Bas.Reames@live.fr','Krystyna','27481124087','Chwatal','Pass890','666-1239','DOCTOR',5,'PULMOLOGY'),(599,'3883 W. Broadway','Shanghai','India','Hank.Herrin@freeweb.nl','Roger','77182062572','Chwatal','Pass071','666-7770','DOCTOR',5,'PULMOLOGY'),(600,'255 Shallowford Rd','Qingdao','US Minor Outlying Islands','Martin.Cain@yahoo.com','Annie','48492356587','Crocetti','Pass717','666-9958','DOCTOR',6,'ORTHOPEDIA'),(601,'00 Sutter Street','TRIPOLI','Bermuda','RDaughtery3@yahoo.co.uk','Suzanne','18572126103','Arcadi','Pass629','666-3969','DOCTOR',6,'ORTHOPEDIA'),(602,'9 R. Wright Ave','Fengcheng','Russian Federation','Bill.Love@myspace.dk','Sid','10796375970','Goodnight','Pass375','666-2512','DOCTOR',6,'GENERAL'),(603,'5389 Brandon Marshall Street','BUENOS AIRES','Ireland','Femke.Francis@telfort.de','Christa','74493564705','Climent','Pass314','666-4899','DOCTOR',6,'GENERAL'),(604,'4 Maxwell Ave.','Nizhny Novgorod','Bouvet Island','FrankDaughtery2@mobileme.es','Babette','87045578447','Royal','Pass021','666-1202','DOCTOR',6,'CHEMICAL'),(605,'8182 Springfield Ave','Xian','Croatia (Hrvatska)','Will.Jones@mymail.cn','Edward','38218493147','Howe','Pass966','666-6441','DOCTOR',6,'CHEMICAL'),(606,'19 Elmar Street','BEIJING','Northern Mariana Islands','SVan Dinter@mobileme.com','Martin','80946821385','Sirabella','Pass330','666-1061','DOCTOR',6,'PSIHOLOGY'),(607,'409 Oak Leaf Drive','Sapporo','Congo','M.Langham@mymail.be','Mathias','30799925404','van Dijk','Pass391','666-5691','DOCTOR',6,'PSIHOLOGY'),(608,'6115 Barrenger Dr.','YEREVAN','Iceland','LucyDeWilde@telfort.org','Krystyna','09243300390','Morton','Pass937','666-7013','DOCTOR',6,'NEUROLOGICAL'),(609,'3 NW 111th Loop','Nanchang','Turkey','FreddyPyland@libero.fr','Ellie','65761651240','Wolpert','Pass340','666-4108','DOCTOR',6,'NEUROLOGICAL'),(610,'93 Tuckahoe St','Huaian','Iceland','BasArcher@excite.dk','Gert','86625690054','Hancock','Pass248','666-9673','DOCTOR',6,'PLASTICS'),(611,'9884 Roan Lane','Vadodara','Martinique','FreddyLinton5@excite.dk','Tim','88307844607','Bitmacs','Pass121','666-5856','DOCTOR',6,'PLASTICS'),(612,'6 Carnaby Creek','Haicheng','Russian Federation','P.Depew@dolfijn.cc','Marco','27574659202','Pensec','Pass170','666-7358','DOCTOR',6,'CARDIO'),(613,'486 Paddington Lane','Naples','Russian Federation','LReyes@lycos.gov','Frederik','95642498501','van Dijk','Pass696','666-1083','DOCTOR',6,'CARDIO'),(614,'1190 Farquar Ave','Ufa','Hong Kong','Johan.Overton2@dolfijn.cc','Luka','53799840116','Wooten','Pass361','666-2656','DOCTOR',6,'GENERAL'),(615,'12 Carolyn Close','Wafangdian','Guam','Pauline.Climent4@web.it','Mikkel','68604150298','Stannard','Pass862','666-9937','DOCTOR',6,'GENERAL'),(616,'6244 2th Ave.','Mexico City','Austria','Frank.Archer@myspace.de','Marcin','71229393079','Bitmacs','Pass741','666-5325','DOCTOR',6,'GINECOLOGY'),(617,'2785 North MacArthur','Chaozhou','Macau','PatrickZapetis4@excite.fr','Ollie','27227438936','Morgan','Pass150','666-0270','DOCTOR',6,'GINECOLOGY'),(618,'26 Warm Spring Road','Odessa','Norway','JohanPierce@telefonica.nl','Ricky','39112567156','Moreau','Pass872','666-4198','DOCTOR',6,'PULMOLOGY'),(619,'42 Meadow Walk','Los Angeles','Bulgaria','G.Royal1@mobileme.nl','Ross','36111352832','Ostanik','Pass748','666-0286','DOCTOR',6,'PULMOLOGY'),(620,'348 Morningside Dr','Naples','Georgia','Trees.Deans@freeweb.be','Rachael','76891614148','Paul','Pass354','666-8997','DOCTOR',7,'ORTHOPEDIA'),(621,'1859 Virginia Foothills Dr','Shenyang','Svalbard and Jan Mayen Islands','J.Oyler@gawab.ca','Cath','33930542726','Hamilton','Pass717','666-4875','DOCTOR',7,'ORTHOPEDIA'),(622,'2 Craftsland Road','Surat','Mauritius','LindsyDaniel@dolfijn.it','Marek','63013185863','Caffray','Pass376','666-8308','DOCTOR',7,'GENERAL'),(623,'1 Arlington Road','Semarang','Myanmar','Mandy.Goodnight@gawab.fr','Jack','57199618773','Stockton','Pass117','666-4416','DOCTOR',7,'GENERAL'),(624,'5531 Pacella Park Drive','LUANDA','Uruguay','FrankForeman4@libero.com','Teddy','53805401936','Beckbau','Pass643','666-0359','DOCTOR',7,'CHEMICAL'),(625,'515 Bryant Street','Xian','Liberia','TreesAllison1@msn.be','Pedro','40362127077','Browne','Pass715','666-3995','DOCTOR',7,'CHEMICAL'),(626,'6 Chehalis Road','KINSHASA','Malaysia','Ann.Brown@libero.cc','Ross','20514459391','Boyer','Pass452','666-6035','DOCTOR',7,'PSIHOLOGY'),(627,'326 Everglades Way','Yokohama','Barbados','Hans.Pickering@mobileme.com','Nicoline','58701141748','Weaver','Pass528','666-6167','DOCTOR',7,'PSIHOLOGY'),(628,'8449 Warwick Square','Taiyuan','Macau','ESchmidt@kpn.nl','Albert','07454614408','van het Hof','Pass724','666-3251','DOCTOR',7,'NEUROLOGICAL'),(629,'1 Business Center Dr.','Omdurman','Sudan','HansKnight1@yahoo.net','Shermie','80446674785','Phelps','Pass219','666-1280','DOCTOR',7,'NEUROLOGICAL'),(630,'5 Wellington Dr.','Kaohsiung','Jamaica','JKoch1@gawab.gov','Charlotte','09538689996','Lawton','Pass154','666-0220','DOCTOR',7,'PLASTICS'),(631,'273 Ninth Avenue','YEREVAN','Namibia','MickLezniak5@telefonica.de','Paul','70128427403','Cragin','Pass276','666-6254','DOCTOR',7,'PLASTICS'),(632,'5180 Brooke Valley Drive','Maracaibo','Poland','BasBrown@hotmail.cn','Vanessa','02544788448','Leonarda','Pass054','666-0958','DOCTOR',7,'CARDIO'),(633,'11 Swallowtail Ct.','Bandung','Turks and Caicos Islands','LucyBugno3@lycos.ca','Liam','34283072440','Brown','Pass574','666-3500','DOCTOR',7,'CARDIO'),(634,'724 Elm Avenue','Chicago','American Samoa','TreesHarder4@yahoo.org','Ulla','43022161653','Framus','Pass779','666-8800','DOCTOR',7,'GENERAL'),(635,'2 W. Sunnyside Ave.','Adana','Malawi','A.Markovi@gmail.gov','Ulla','29759720466','Wood','Pass182','666-9161','DOCTOR',7,'GENERAL'),(636,'5619 One Park Avenue','Kwangchu','Hungary','Y.Moreau@libero.co.uk','Margo','05382735345','Rivers','Pass023','666-0067','DOCTOR',7,'GINECOLOGY'),(637,'68 Station Street','Tegal','Guinea','GKeller@libero.ca','Chloe','27631249353','Herzog','Pass887','666-3085','DOCTOR',7,'GINECOLOGY'),(638,'4 Pacella Park Drive','Luoyang','Qatar','Peter.McDaniel@libero.be','Harry','10755316253','Moon','Pass551','666-6619','DOCTOR',7,'PULMOLOGY'),(639,'5 Mercury Drive','SINGAPORE','Cote D\'Ivoire (Ivory Coast)','SHollman2@live.net','Alexis','20008416880','Rauch','Pass662','666-6186','DOCTOR',7,'PULMOLOGY'),(640,'282 Elmoasi Street','St Petersburg','Bangladesh','Ton.Love@freeweb.nl','Sue','17214542930','Stevens','Pass055','666-7118','DOCTOR',8,'ORTHOPEDIA'),(641,'175 Hunt Valley Road','LONDON','Paraguay','Peter.Cramer5@gawab.cc','Lena','06433039147','Haynes','Pass310','666-1940','DOCTOR',8,'ORTHOPEDIA'),(642,'780 South Main','Pusan','Ecuador','PeteLeGrand@live.gov','Thomas','19680200152','Patricelli','Pass641','666-4741','DOCTOR',8,'GENERAL'),(643,'79 S. Brockbank Drive','Casablanca','Iraq','TArden@kpn.us','Anna','74823565040','Pekaban','Pass235','666-5572','DOCTOR',8,'GENERAL'),(644,'24 Madden Blvd.','Mashhad','Tunisia','WillCramer@mymail.nl','Margaret','66040277738','Miller','Pass695','666-2848','DOCTOR',8,'CHEMICAL'),(645,'0361 Medford St','Ujung Pandang','Nauru','PSakurai4@dolfijn.nl','Chuck','47800078601','Dulisse','Pass241','666-9196','DOCTOR',8,'CHEMICAL'),(646,'969 N York Dr','Nagoya','Botswana','FrankSlater@myspace.es','Charles','05358369555','Millikin','Pass753','666-6814','DOCTOR',8,'PSIHOLOGY'),(647,'80 Nez Perce Street','BUDAPEST','French Guiana','D.Cain3@gawab.be','Daan','21363670288','Rivers','Pass887','666-5522','DOCTOR',8,'PSIHOLOGY'),(648,'305 Summer Drive','Guayaquil','Yemen','Victoria.DeBerg@freeweb.fr','Theo','36810525099','Roger','Pass577','666-6977','DOCTOR',8,'NEUROLOGICAL'),(649,'806 Melrose Ave','TBILISI','Turkey','FransForsberg@myspace.us','Scotty','39531287445','Simonent','Pass400','666-6161','DOCTOR',8,'NEUROLOGICAL'),(650,'5 ZW Bruce Street','Shiraz','Cameroon','Rick.Bogdanovich@web.net','Edwyn','41171569180','Wolpert','Pass791','666-3631','DOCTOR',8,'PLASTICS'),(651,'627 West 48th Street','Osaka','Cape Verde','Dick.Cantere@live.cc','GertJan','48299116881','Stewart','Pass189','666-7815','DOCTOR',8,'PLASTICS'),(652,'50 E. Broward Blvd.','Fortaleza','Equatorial Guinea','Bianca.Mayberry@dolfijn.it','Lena','67334886539','Petterson','Pass049','666-2616','DOCTOR',8,'CARDIO'),(653,'429 Unbridle Way','Huzhou','Yemen','HBrylle@live.org','Erin','87175850618','Schmidt','Pass879','666-3761','DOCTOR',8,'CARDIO'),(654,'3 Pacella Park Drive','Chicago','Georgia','MBotsik@libero.dk','Simon','22007414226','Ray','Pass938','666-6304','DOCTOR',8,'GENERAL'),(655,'586 ZW Bruce Street','Bombay','Cyprus','JimWicks@gawab.no','Freja','83211654636','Stewart','Pass911','666-0781','DOCTOR',8,'GENERAL'),(656,'7387 Chalmette Court','Nanchang','Cambodia','DavidPensec1@live.es','Godfrey','09636335381','Cantere','Pass379','666-5030','DOCTOR',8,'GINECOLOGY'),(657,'4491 Buckthorn Lane','Xiaogan','Puerto Rico','K.Thompson3@web.com','Matthew','88538087188','Wolpert','Pass593','666-0379','DOCTOR',8,'GINECOLOGY'),(658,'2 Hill Street','Lianyuan','Pitcairn','EmmaLee3@gawab.co.uk','Ed','35023922700','Herrin','Pass522','666-0375','DOCTOR',8,'PULMOLOGY'),(659,'8386 North MacArthur','Haicheng','Guatemala','WilliamLaudanski1@excite.cc','Rasmus','72034059072','Bitmacs','Pass985','666-5130','DOCTOR',8,'PULMOLOGY'),(660,'65 Shallowford Rd','Porto Alegre','Morocco','VictorCantere4@mobileme.cc','PieterJan','41079297674','Beckbau','Pass811','666-0666','DOCTOR',9,'ORTHOPEDIA'),(661,'84 Carolyn Close','Milano','Slovenia','RickDulisse5@mobileme.no','Bram','65846994262','Novratni','Pass400','666-6838','DOCTOR',9,'ORTHOPEDIA'),(662,'545 Elmar Street','Calcutta','Ghana','Fred.Bloom@freeweb.org','Ollie','99156555541','Petterson','Pass597','666-3483','DOCTOR',9,'GENERAL'),(663,'43 Elmar Street','Probolinggo','Madagascar','Rogier.Thompson2@yahoo.net','Sherm','08973440455','Stevenson','Pass076','666-9766','DOCTOR',9,'GENERAL'),(664,'1 Grassmere Avenue','Nizhny Novgorod','Burkina Faso','Amber.Leonarda3@gawab.us','Charles','60042725919','Wood','Pass601','666-4387','DOCTOR',9,'CHEMICAL'),(665,'27 Jefferson Ave','Changde','St. Pierre and Miquelon','GMiller@libero.ca','Gerrit','43274184917','Williamson','Pass233','666-8978','DOCTOR',9,'CHEMICAL'),(666,'304 Carnaby Creek','Indore','Ecuador','YChapman5@dolfijn.ca','Amy','61768404865','Brown','Pass733','666-0039','DOCTOR',9,'PSIHOLOGY'),(667,'71 Melrose Ave','Perth','Paraguay','Leovan der Laar@msn.no','Matt','95721122298','Troher','Pass672','666-6812','DOCTOR',9,'PSIHOLOGY'),(668,'0430 Carroll Center Rd','YEREVAN','Cook Islands','David.Korkovski5@live.nl','Pieter','85005969232','Pekaban','Pass943','666-4086','DOCTOR',9,'NEUROLOGICAL'),(669,'2864 Springfield Ave','Vienna','Bulgaria','OliverBotsik3@web.us','Christa','40176470354','Robertson','Pass792','666-7795','DOCTOR',9,'NEUROLOGICAL'),(670,'07 Centerville Street','Bhopal','Nauru','Dana.Ditmanen3@aol.it','Mikkel','88360811981','Poole','Pass269','666-0551','DOCTOR',9,'PLASTICS'),(671,'20 Farquar Ave','Xiaoshan','Trinidad and Tobago','Geoffry.Heyn@gawab.be','Madison','08097873428','Brylle','Pass742','666-2029','DOCTOR',9,'PLASTICS'),(672,'012 Hidden Valley Drive','Hamburg','Dominica','PBeckbau4@libero.de','Jesse','32035977581','Koss','Pass617','666-0337','DOCTOR',9,'CARDIO'),(673,'756 Printing House Yard','Medellín','Japan','RickMoreau@telfort.dk','Janet','45901994676','Scheffold','Pass534','666-9455','DOCTOR',9,'CARDIO'),(674,'25 Close Gardens Court','Zhanjiang','Togo','Petra.Praeger@excite.it','Petra','75896292700','Geoppo','Pass432','666-5585','DOCTOR',9,'GENERAL'),(675,'18 Colette street','Chittagong','Monaco','Frans.Moreau1@mymail.gov','Ulla','08775235256','Pengilly','Pass533','666-6081','DOCTOR',9,'GENERAL'),(676,'7 Everglades Way','RIYADH','Paraguay','FredWong1@excite.es','Malgorzata','52279934525','Mcnally','Pass963','666-9097','DOCTOR',9,'GINECOLOGY'),(677,'9 E. Marions Rd','Jinan','Netherlands Antilles','Eric.Sharp3@mymail.fr','Alfons','14044760835','Langham','Pass174','666-3843','DOCTOR',9,'GINECOLOGY'),(678,'309 Mercury Drive','Pune','Saudi Arabia','Freddy.Mitchell@web.it','Edwin','91962282620','Morgan','Pass937','666-9193','DOCTOR',9,'PULMOLOGY'),(679,'7808 Summer Drive','TEHRAN','Denmark','RicoRobbins5@excite.it','Nick','80881838929','Antonucci','Pass019','666-4107','DOCTOR',9,'PULMOLOGY'),(680,'6 Paddington Lane','Baotou','Saint Kitts and Nevis','James.Robertson5@kpn.nl','Ainhoa','07895264549','Hummel','Pass561','666-6693','DOCTOR',10,'ORTHOPEDIA'),(681,'5 Waterford Place','Dalian','Pitcairn','D.Lannigham@lycos.us','Victor','94862719484','Knopp','Pass951','666-4214','DOCTOR',10,'ORTHOPEDIA'),(682,'697 East MacArthur','Xian','Thailand','Rogier.Nithman5@weboffice.de','Ashley','91835205334','Stevenson','Pass800','666-6298','DOCTOR',10,'GENERAL'),(683,'489 Gilbert Street','Hamburg','Christmas Island','LindsyArnold@hotmail.co.uk','Christa','68940850417','Praeger','Pass277','666-3825','DOCTOR',10,'GENERAL'),(684,'37 Muzzatta Way','Dallas (TX)','Korea (South)','TMcgrew@myspace.de','Camille','59059596081','Praeger','Pass220','666-7476','DOCTOR',10,'CHEMICAL'),(685,'68 Brooke Valley Drive','Pueblade Zaragoza','Zambia','RWolpert@myspace.it','Tim','48091642835','Williamson','Pass040','666-6228','DOCTOR',10,'CHEMICAL'),(686,'065 Southwest Blvd','PYONGYANG','Zambia','NigelKorkovski@telfort.nl','Sue','52117776509','Hardoon','Pass415','666-6668','DOCTOR',10,'PSIHOLOGY'),(687,'1737 Scrutton Street','Novosibirsk','Colombia','CPekaban@freeweb.fr','Sid','02524784160','Herring','Pass899','666-3502','DOCTOR',10,'PSIHOLOGY'),(688,'21 Gascony Pl.','Pakalongan','Korea (North)','Leon.Suszantor@lycos.cn','Edwyn','73160205358','Ostanik','Pass522','666-6056','DOCTOR',10,'NEUROLOGICAL'),(689,'1303 Woodcote Valley Road','Bombay','Falkland Islands (Malvinas)','GeorgeWard@gmail.de','Babet','15744661870','Weaver','Pass658','666-9743','DOCTOR',10,'NEUROLOGICAL'),(690,'460 Shawnee Ct','Vadodara','Guam','Bas.White@telefonica.cc','Sherman','15522820662','Wilson','Pass477','666-5756','DOCTOR',10,'PLASTICS'),(691,'7023 Quorum Dr','Fuzhou','Reunion','GretsjGua Lima@myspace.be','Harry','40674940802','Ahlgren','Pass988','666-4876','DOCTOR',10,'PLASTICS'),(692,'662 Paddington Lane','Cali','Slovenia','Emma.DeBerg4@mymail.de','Will','78141558842','Pengilly','Pass752','666-2152','DOCTOR',10,'CARDIO'),(693,'4 Elmar Street','Wulumuqi','Niger','RickPerilloux5@mail.cc','Luka','82625422736','Mitchell','Pass177','666-5137','DOCTOR',10,'CARDIO'),(694,'532 Station Street','Bandung','Gibraltar','G.Stockton5@freeweb.us','Alfons','82330006366','Wakefield','Pass964','666-1652','DOCTOR',10,'GENERAL'),(695,'6633 N Broadway','Changde','Netherlands Antilles','Pauline.Roger@libero.dk','Richard','92494206406','Hedgecock','Pass681','666-3424','DOCTOR',10,'GENERAL'),(696,'5 Carnaby Creek','Luoyang','Nauru','YHelbush3@msn.net','Sid','88344209074','Chwatal','Pass799','666-8040','DOCTOR',10,'GINECOLOGY'),(697,'4 Sutter Street','Wulumuqi','S. Georgia and S. Sandwich Isls.','RickPetrzelka@telfort.cc','Jeanne','94462014338','Cohen','Pass673','666-2747','DOCTOR',10,'GINECOLOGY'),(698,'3 Prospect Hill','Prague','Zambia','T.Stannard@freeweb.no','David','62451914466','Troher','Pass722','666-8374','DOCTOR',10,'PULMOLOGY'),(699,'2 Chesterfield Road South','St Petersburg','Yemen','LDonatelli@msn.no','Catharine','98013360504','Robbins','Pass992','666-5014','DOCTOR',10,'PULMOLOGY'),(700,'20 Virginia Foothills Dr','Ekaterinoburg','Algeria','TDavis@dolfijn.net','Lucia','84153809763','Petterson','Pass561','666-5638','DOCTOR',11,'ORTHOPEDIA'),(701,'327 N Broadway','MONTEVIDEO','Sweden','Frank.Voigt4@mail.no','Edward','54880515213','McDaniel','Pass811','666-2944','DOCTOR',11,'ORTHOPEDIA'),(702,'8 Penny Lane','Chengdu','Bosnia and Herzegovina','WillMcCrary@dolfijn.es','Joanne','13781866605','Thompson','Pass975','666-5687','DOCTOR',11,'GENERAL'),(703,'67 Clearwater Pkwy','Algiers','Senegal','Bas.Morton5@msn.org','Sid','45338790490','Otto','Pass401','666-0215','DOCTOR',11,'GENERAL'),(704,'6225 Stony Field Rd','LUANDA','Samoa','TonBrisco4@live.dk','Willy','39055275718','Hardoon','Pass977','666-6990','DOCTOR',11,'CHEMICAL'),(705,'6712 Morningside Dr','Adelaide','Turks and Caicos Islands','NickBrumley5@weboffice.fr','Geoffery','49919010041','Guyer','Pass871','666-6060','DOCTOR',11,'CHEMICAL'),(706,'82 Clearwater Pkwy','Delhi','Myanmar','BrendBrylle@aol.cn','Joe','10131300104','Prior','Pass339','666-3303','DOCTOR',11,'PSIHOLOGY'),(707,'5 Prospect Hill','RABAT','Guinea-Bissau','LynnIonescu@web.us','Annie','52543924078','DeBerg','Pass989','666-3412','DOCTOR',11,'PSIHOLOGY'),(708,'27 Edith Marie Drive','Chaozhou','Pitcairn','Mick.Sanders@yahoo.us','Jamie','88520107410','Millis','Pass149','666-0545','DOCTOR',11,'NEUROLOGICAL'),(709,'8 Victoria Street','Jingmen','Wallis and Futuna Islands','JohanNobles4@kpn.us','Syd','18758200960','Seibel','Pass278','666-3417','DOCTOR',11,'NEUROLOGICAL'),(710,'558 High Palms Ave','Fushun','Uganda','GAllison1@msn.no','Milan','51428584408','Goodnight','Pass423','666-7584','DOCTOR',11,'PLASTICS'),(711,'975 Everglades Way','Probolinggo','Palau','Bas.Waddell2@mymail.org','Iris','98586302955','Stewart','Pass913','666-2951','DOCTOR',11,'PLASTICS'),(712,'919 Grassmere Avenue','LIMA','Colombia','RichardPerilloux4@weboffice.co.uk','Victor','55418724254','Morton','Pass196','666-9280','DOCTOR',11,'CARDIO'),(713,'4875 Wellington Dr.','Xiantao','Zaire','SuusBlount@weboffice.com','Erin','49089870958','Olson','Pass808','666-9654','DOCTOR',11,'CARDIO'),(714,'0 Medford St','Macheng','British Indian Ocean Territory','Dana.Mariojnisk@dolfijn.de','Rob','47441643579','Guethlein','Pass961','666-5444','DOCTOR',11,'GENERAL'),(715,'0 27th Street North','Moscow','Oman','Brent.Warner@telfort.be','Dorothy','56818562377','van het Hof','Pass039','666-9494','DOCTOR',11,'GENERAL'),(716,'4 East 74th Street','Inchon','Sierra Leone','LPekagnan@aol.co.uk','Benjamin','61074598797','Griffith','Pass013','666-8732','DOCTOR',11,'GINECOLOGY'),(717,'831 New London Street','Donetsk','Martinique','SydneyBlount4@myspace.be','Jurre','23542455579','Press','Pass215','666-2264','DOCTOR',11,'GINECOLOGY'),(718,'21 Fox Squirrel Lane','Tianmen','Somalia','TBrowne3@weboffice.no','Paul','69017831581','Mulders','Pass062','666-7115','DOCTOR',11,'PULMOLOGY'),(719,'4 Palmer Rd','Guayaquil','Uzbekistan','BiancaSlocum3@freeweb.no','Matthijs','04756997670','Franklin','Pass761','666-5786','DOCTOR',11,'PULMOLOGY'),(720,'6 Northpark Blvd','San Antonio (TX)','Denmark','Rick.Daughtery3@myspace.com','Luis','18373725092','Ionescu','Pass208','666-2882','DOCTOR',12,'ORTHOPEDIA'),(721,'850 Devonshire Rd','Xinghua','Antarctica','E.Anthony5@yahoo.us','Elzbieta','95211286562','Morton','Pass501','666-7202','DOCTOR',12,'ORTHOPEDIA'),(722,'942 E. Marions Rd','Houston','Saint Kitts and Nevis','Sjors.van Dijk@aol.cn','Edward','17851317063','Hardoon','Pass998','666-1709','DOCTOR',12,'GENERAL'),(723,'2 Fontanoso Way','Netzahualcóyotl','French Polynesia','LucyLannigham@gawab.co.uk','Lucille','80421368087','Uprovski','Pass037','666-2200','DOCTOR',12,'GENERAL'),(724,'0361 Crookston Lane','YANGON','Vatican City State (Holy See)','BobMaribarski1@live.fr','Robert','78205181983','Sanders','Pass057','666-9226','DOCTOR',12,'CHEMICAL'),(725,'5 Mercury Drive','Curitiba','Djibouti','OttoBrylle@dolfijn.ca','Scotty','14565032291','Fernandez','Pass645','666-3498','DOCTOR',12,'CHEMICAL'),(726,'2350 Gaston Ave','Zhucheng','St. Helena','A.Glanswol@web.com','Bob','26477792741','Knopp','Pass923','666-7924','DOCTOR',12,'PSIHOLOGY'),(727,'111 Glenridge Drive','Taejon','Guam','BasNelson@kpn.ca','Drew','54676856938','Arcadi','Pass024','666-6498','DOCTOR',12,'PSIHOLOGY'),(728,'97 Regency Plaza','KABUL','Nigeria','K.Toler2@dolfijn.no','Ike','49638366475','Harness','Pass668','666-4898','DOCTOR',12,'NEUROLOGICAL'),(729,'6 Palos Verdes Mall','RABAT','Mauritania','Fons.Arnold@freeweb.de','Raul','99295421118','Vanderoever','Pass083','666-5320','DOCTOR',12,'NEUROLOGICAL'),(730,'7168 Sungrave Lane','Jeddah','Cocos (Keeling) Islands','Lucas.van Goes@telefonica.net','Dorothy','99905215868','Dittrich','Pass273','666-3318','DOCTOR',12,'PLASTICS'),(731,'1 Cheltenham Road','San Diego (CA)','Bangladesh','MilanPatricelli@gawab.it','Susan','34665139097','Herzog','Pass240','666-5550','DOCTOR',12,'PLASTICS'),(732,'7 N. Deerwood Street','Huaian','Uruguay','GChwatal@mail.net','Katarzyna','36474287229','Noteboom','Pass543','666-0356','DOCTOR',12,'CARDIO'),(733,'5 Tremont Street','Ekaterinoburg','Virgin Islands (British)','EHoyt@myspace.es','Dave','16029696214','Alspaugh','Pass949','666-9526','DOCTOR',12,'CARDIO'),(734,'2 Chesterfield Road South','ANKARA','Greenland','MichaelBertelson@msn.no','Catharine','12820322371','Lee','Pass434','666-7291','DOCTOR',12,'GENERAL'),(735,'0 South MacArthur','ANKARA','Kyrgyzstan','EMayberry@yahoo.de','Georgina','11222500286','Blount','Pass665','666-3459','DOCTOR',12,'GENERAL'),(736,'1 Rockville Pike','MAPUTO','Sweden','Paul.Gunter1@gawab.de','Anna','65414286751','Barnett','Pass967','666-3458','DOCTOR',12,'GINECOLOGY'),(737,'79 Chesterfield Road South','Suqian','United Arab Emirates','TreesPoissant@mymail.co.uk','Ross','00088266227','Muench','Pass108','666-3465','DOCTOR',12,'GINECOLOGY'),(738,'72 Sulgrave Lane','Wuxi','Tonga','Paul.Gerschkow@weboffice.gov','Sally','81050688087','Botsik','Pass318','666-8121','DOCTOR',12,'PULMOLOGY'),(739,'613 Trenton Avenue','TOKYO','Turks and Caicos Islands','Hank.Anderson4@msn.cc','Elena','03323776281','Mcgrew','Pass097','666-0897','DOCTOR',12,'PULMOLOGY'),(740,'837 Jefferson Ave','Barranquilla','India','RichardWeaver4@libero.net','Rosa','31247489757','Uprovski','Pass464','666-5417','DOCTOR',13,'ORTHOPEDIA'),(741,'7 E. Plumb Lane','Baotou','Bosnia and Herzegovina','Hank.Helfrich@msn.org','Marie','59510415122','van het Hof','Pass591','666-1222','DOCTOR',13,'ORTHOPEDIA'),(742,'1115 Mount Helena Av.','Ludhiana','Sweden','FredConley3@msn.it','Nate','18350824764','Raines','Pass416','666-3069','DOCTOR',13,'GENERAL'),(743,'449 Broomhouse Rd','Karachi','India','Nick.Antonucci@telfort.es','Bert','01726952598','Watson','Pass506','666-1306','DOCTOR',13,'GENERAL'),(744,'94 Walkers Avenue','Córdoba','Saint Lucia','RichardPoplock5@dolfijn.ca','Edwin','14517980564','Langham','Pass586','666-6162','DOCTOR',13,'CHEMICAL'),(745,'30','Pikine-Guediawaye','Iraq','BrendSuszantor@hotmail.it','Gert','27557144811','Slemp','Pass848','666-4796','DOCTOR',13,'CHEMICAL'),(746,'4 Eisenhower Avenue','Probolinggo','Laos','RogierLangham5@telefonica.co.uk','Tinus','93593096470','Bruno','Pass348','666-5419','DOCTOR',13,'PSIHOLOGY'),(747,'0 Edith Marie Drive','La Matanza','Russian Federation','C.Anderson4@libero.us','Sydney','80568414288','Phelps','Pass818','666-0647','DOCTOR',13,'PSIHOLOGY'),(748,'9503 Oak Leaf Drive','Baotou','Niger','AliceLeonarda@gawab.es','Siska','96222407184','Prior','Pass728','666-9984','DOCTOR',13,'NEUROLOGICAL'),(749,'5468 Elm Waterford Drive','Sydney','Czechoslovakia (former)','GNahay4@libero.no','Marty','85675883090','Ray','Pass168','666-9444','DOCTOR',13,'NEUROLOGICAL'),(750,'65 Palos Verdes Mall','Rizhao','Suriname','NorbertFriedman5@msn.be','Theo','22671356838','Ostanik','Pass332','666-8620','DOCTOR',13,'PLASTICS'),(751,'2277 W. Broadway','Fengcheng','Georgia','Martin.Sharp2@libero.org','Jaclyn','01635023296','Warner','Pass396','666-4890','DOCTOR',13,'PLASTICS'),(752,'71 One Park Avenue','Taejon','Eritrea','WilliamDurso5@live.us','Harold','01029401959','Schlee','Pass258','666-9333','DOCTOR',13,'CARDIO'),(753,'9 Terminal Way','Karachi','Honduras','WilliamKing@excite.cn','Gillian','42673567922','Rivers','Pass909','666-4979','DOCTOR',13,'CARDIO'),(754,'5870 Melrose Ave','BANGKOK','Aruba','Isolde.Caffray@msn.us','Marieke','31665041403','Anderson','Pass526','666-9018','DOCTOR',13,'GENERAL'),(755,'82 W. Broadway','Kanpur','Yugoslavia','Bas.Anderson5@dolfijn.cn','Jonas','26757388412','Robertson','Pass251','666-6936','DOCTOR',13,'GENERAL'),(756,'35 Carroll Center Rd','Yixing','Gabon','Mike.Leonarda@kpn.de','Jody','22115704017','Haynes','Pass977','666-7488','DOCTOR',13,'GINECOLOGY'),(757,'694 East 74th Street','Linyi','United Kingdom','G.Malone@myspace.co.uk','Cecilie','62032094760','Spensley','Pass817','666-2081','DOCTOR',13,'GINECOLOGY'),(758,'024 Glenridge Drive','Algiers','Chad','PScheffold3@yahoo.gov','Michel','37889009284','Van Toorenbeek','Pass011','666-4326','DOCTOR',13,'PULMOLOGY'),(759,'732 Harrison rd','Tangshan','Puerto Rico','BiancaMitchell@hotmail.es','Cath','71392488806','Vostreys','Pass303','666-4494','DOCTOR',13,'PULMOLOGY'),(760,'9 W. Broadway','La Matanza','Yugoslavia','PLong@weboffice.dk','Marieke','98700995134','Daley','Pass643','666-6317','DOCTOR',14,'ORTHOPEDIA'),(761,'71 Calle Fidelidad','Baotou','Mauritania','PaulChwatal@dolfijn.co.uk','Giel','68428714016','Blount','Pass432','666-2012','DOCTOR',14,'ORTHOPEDIA'),(762,'001 Hunt Valley Road','Adelaide','Seychelles','FransRoyal@kpn.be','Lucille','96746353708','Guethlein','Pass331','666-7120','DOCTOR',14,'GENERAL'),(763,'9 Freidrich Lane','Sendai','China','BillFranklin@live.no','Coby','72608519190','Blacher','Pass811','666-1601','DOCTOR',14,'GENERAL'),(764,'5125 27th Street North','Dnepropetrovsk','Great Britain (UK)','R.Herzog@mobileme.com','Fons','51568026134','Ward	','Pass930','666-5603','DOCTOR',14,'CHEMICAL'),(765,'007 Hunt Valley Road','Suining','Zambia','R.Makelaar@msn.de','Vincent','45658033410','Chapman','Pass194','666-1838','DOCTOR',14,'CHEMICAL'),(766,'99 Hunt Valley Road','Yancheng','Niue','GeoffryCrocetti3@aol.cn','Nathan','94202586157','Rivers','Pass628','666-3221','DOCTOR',14,'PSIHOLOGY'),(767,'8242 Jefferson Ave','Adana','Sao Tome and Principe','Kim.Pensec2@yahoo.org','Alba','96208171417','DeBuck','Pass930','666-0771','DOCTOR',14,'PSIHOLOGY'),(768,'06 Dallas Parkway','Alexandria','Cameroon','HansSterrett5@live.es','Siem','14414018348','Love','Pass605','666-8183','DOCTOR',14,'NEUROLOGICAL'),(769,'0692 Buckthorn Lane','Dongtai','El Salvador','BrendKoss@live.gov','Pip','16356692717','Wakefield','Pass187','666-6861','DOCTOR',14,'NEUROLOGICAL'),(770,'90 Ninth Avenue','TOKYO','Greece','P.Poplock@gawab.gov','Piotr','23216663216','Sanders','Pass127','666-0928','DOCTOR',14,'PLASTICS'),(771,'4 Arlington Road','Zaozhuang','Monaco','RicoJohnson@gmail.no','Pip','32265065218','Lee','Pass632','666-4748','DOCTOR',14,'PLASTICS'),(772,'9128 W. Broadway','Jiangyin','Italy','ECappello2@telefonica.fr','Hanna','58448730053','Pearlman','Pass437','666-9995','DOCTOR',14,'CARDIO'),(773,'41 Nortman Blvd','KABUL','Tajikistan','VWalker@freeweb.fr','Krzysztof','42003990985','van Doorn','Pass655','666-3472','DOCTOR',14,'CARDIO'),(774,'18 Perimeter Dr.','Montréal','Panama','Y.Thompson@aol.no','Patricia','72199600195','Bergdahl','Pass562','666-8193','DOCTOR',14,'GENERAL'),(775,'65 Printing House Yard','Bogor','Lithuania','NadineGrote@weboffice.cn','Callum','68398179902','Lawton','Pass554','666-1719','DOCTOR',14,'GENERAL'),(776,'0419 R. Wright Ave','Hefei','Svalbard and Jan Mayen Islands','WillWilson1@yahoo.be','Edwina','25283505952','Wooten','Pass298','666-1466','DOCTOR',14,'GINECOLOGY'),(777,'075 Stokes Rd.','Xintai','Anguilla','K.Press1@hotmail.gov','Lucas','28935575279','Hankins','Pass042','666-5091','DOCTOR',14,'GINECOLOGY'),(778,'5702 Brooke Valley Drive','Jaipur','Bahamas','S.Roche2@myspace.gov','Sammy','53655796644','Knight','Pass655','666-9686','DOCTOR',14,'PULMOLOGY'),(779,'869 Serang Place','Inchon','Montserrat','DavidPensec@telefonica.us','Sofia','61461831482','Jenssen','Pass310','666-4612','DOCTOR',14,'PULMOLOGY'),(780,'46 Wilder St.','Baotou','Azerbaijan','JohanMiller@mymail.cn','Karen','88060122758','Carlos','Pass431','666-8808','DOCTOR',15,'ORTHOPEDIA'),(781,'12 Shawnee Ct','Jiangyin','Saint Kitts and Nevis','RickNobles@mymail.ca','Esther','85551628180','Antonucci','Pass313','666-5859','DOCTOR',15,'ORTHOPEDIA'),(782,'817 Quorum Dr','Tianjin','Moldova','Bas.Spensley@excite.nl','Chris','04678422171','Clarke','Pass939','666-8433','DOCTOR',15,'GENERAL'),(783,'0730 Maxwell Ave.','Fulin','Taiwan','Nick.Otto@dolfijn.cc','Sofia','07352781215','Praeger','Pass085','666-3725','DOCTOR',15,'GENERAL'),(784,'0','Medellín','Niue','William.Shapiro@myspace.com','Sjaak','58524407003','Trainor','Pass076','666-7020','DOCTOR',15,'CHEMICAL'),(785,'160 Gilbert Street','Fortaleza','Macedonia','Freddy.Pierce@hotmail.cc','Sarah','01488379766','Visentini','Pass054','666-5240','DOCTOR',15,'CHEMICAL'),(786,'4 Pacella Park Drive','Netzahualcóyotl','Ghana','WillCaouette@weboffice.dk','Francisco','69340166242','Reyes','Pass365','666-3122','DOCTOR',15,'PSIHOLOGY'),(787,'8 Woodcote Valley Road','Multan','Wallis and Futuna Islands','Bas.Ray5@live.ca','John','34203130677','Pekaban','Pass881','666-9316','DOCTOR',15,'PSIHOLOGY'),(788,'25 Palos Verdes Mall','Lagos','Cambodia','Patrick.Climent@mymail.it','Niek','37769447746','Polti','Pass172','666-0552','DOCTOR',15,'NEUROLOGICAL'),(789,'7 N. Central Ave.','Tangshan','Israel','William.Brisco@freeweb.us','Scottie','36758901909','Framus','Pass565','666-0811','DOCTOR',15,'NEUROLOGICAL'),(790,'72 Corporate Drive','BOGOTA','Guadeloupe','Lynn.Whitehurst@mymail.org','Ada','66451092971','Shapiro','Pass577','666-1587','DOCTOR',15,'PLASTICS'),(791,'0839 Bryant Street','Taegu','British Indian Ocean Territory','Patty.Ahlgren2@aol.fr','Sammy','63519641723','Shapiro','Pass697','666-9845','DOCTOR',15,'PLASTICS'),(792,'72 Hidden Valley Drive','Perth','Hong Kong','Sven.Baltec@mobileme.it','Ellie','09685522925','McCrary','Pass324','666-5649','DOCTOR',15,'CARDIO'),(793,'2780 Charlotte Avenue','MADRID','El Salvador','Freddy.Pickering@gmail.cc','Bo','99186682981','Novratni','Pass791','666-1309','DOCTOR',15,'CARDIO'),(794,'6 N. Glenoaks Blvd','Novosibirsk','Puerto Rico','Norbert.Grote3@kpn.fr','Sydney','72035128333','Huston','Pass512','666-2005','DOCTOR',15,'GENERAL'),(795,'3416 Nortman Blvd','Hefei','Czechoslovakia (former)','YBloom2@web.co.uk','Cloe','79876281741','Nahay','Pass656','666-6117','DOCTOR',15,'GENERAL'),(796,'8418 Muzzatta Way','BRASILIA','Fiji','Bas.Green@libero.ca','Saskia','34012883705','Gaskins','Pass054','666-6507','DOCTOR',15,'GINECOLOGY'),(797,'339 South Main','Jilin','Western Sahara','GYinger@mail.dk','Robert','41265222127','Reyes','Pass759','666-1462','DOCTOR',15,'GINECOLOGY'),(798,'71 Foxway','Nanning','Syria','Martin.Wood3@gawab.org','Martin','62212419912','Malone','Pass436','666-3660','DOCTOR',15,'PULMOLOGY'),(799,'5903 Hidden Valley Drive','SOFIA','Dominican Republic','JComeau2@aol.dk','Dorothy','83856680920','Deleo','Pass991','666-1470','DOCTOR',15,'PULMOLOGY'),(800,'9459 Acorn Dr.','Medan','Nauru','T.Makelaar@telfort.gov','Cristina','36640653758','Cramer','Pass757','666-3635','DOCTOR',16,'ORTHOPEDIA'),(801,'6921 Charlotte Avenue','Dalian','Latvia','Frank.Stevens@excite.co.uk','Bram','50747334858','Fox','Pass602','666-5465','DOCTOR',16,'ORTHOPEDIA'),(802,'048 High Palms Ave','ANKARA','Dominican Republic','GNaff@dolfijn.com','Luis','40224883594','Hummel','Pass804','666-8232','DOCTOR',16,'GENERAL'),(803,'571 Floribunda Ave','Phoenix (AZ)','French Polynesia','NigelPekagnan@mobileme.org','Mariska','52885430184','Ladaille','Pass719','666-5822','DOCTOR',16,'GENERAL'),(804,'699 Mercury Drive','Casablanca','Ireland','Nadine.Stannard@weboffice.ca','Ben','38467522966','Marra','Pass986','666-1982','DOCTOR',16,'CHEMICAL'),(805,'79 Wellington Dr.','LUSAKA','Uganda','LChwatal@yahoo.gov','Co','44789013215','Crocetti','Pass491','666-1823','DOCTOR',16,'CHEMICAL'),(806,'970 Charlotte Avenue','St Petersburg','Puerto Rico','Peter.Foreman2@lycos.be','Pete','04928431079','Zurich','Pass514','666-8107','DOCTOR',16,'PSIHOLOGY'),(807,'1721 Edith Marie Drive','Jilin','American Samoa','P.Durso@dolfijn.net','Elin','26951182113','Reames','Pass921','666-1459','DOCTOR',16,'PSIHOLOGY'),(808,'0 S. Brackbank Dr','TBILISI','Liberia','E.Clark@freeweb.fr','Helma','48346693816','Press','Pass335','666-2081','DOCTOR',16,'NEUROLOGICAL'),(809,'5 Waterford Blvd','Fuzhou','Spain','H.Slater3@mail.org','Catherine','41457002752','van Goes','Pass474','666-1678','DOCTOR',16,'NEUROLOGICAL'),(810,'915 Carolyn Close','Hefei','Swaziland','George.Lawton@aol.no','Sigrid','15272859906','Brylle','Pass619','666-0662','DOCTOR',16,'PLASTICS'),(811,'7567 North 8th Street','Pusan','Hong Kong','E.Willis3@mail.net','Talitha','24267868777','Miller','Pass081','666-8193','DOCTOR',16,'PLASTICS'),(812,'4 Hill Street','Semarang','Chad','Patrick.DeBuck1@gmail.co.uk','Lisa','63293011598','Moore','Pass505','666-9913','DOCTOR',16,'CARDIO'),(813,'1 Ninth Avenue','Madras','Tajikistan','EDean@freeweb.cc','Leon','74154583432','Sanders','Pass908','666-2487','DOCTOR',16,'CARDIO'),(814,'27 Madden Blvd.','Philadelphia (PA)','Pitcairn','HansLamere3@aol.gov','Cristina','58840686823','Guethlein','Pass037','666-9861','DOCTOR',16,'GENERAL'),(815,'0 Canyon Crest Drive','Qidong','France','Victoria.Crocetti5@mobileme.no','Jeanne','86192683106','Jones','Pass836','666-0527','DOCTOR',16,'GENERAL'),(816,'767 Hidden Valley Drive','Haicheng','Heard and McDonald Islands','Pablo.Uprovski@gmail.fr','Maggie','71428130691','Turk','Pass326','666-6785','DOCTOR',16,'GINECOLOGY'),(817,'345 Dairy Rd','Kawasaki','Armenia','Bas.Wong@hotmail.net','Sem','82463474231','Mairy','Pass547','666-8300','DOCTOR',16,'GINECOLOGY'),(818,'414 Chehalis Road','Haicheng','Hong Kong','Bill.Wakefield1@telfort.us','Jody','58874948761','van Goes','Pass764','666-4538','DOCTOR',16,'PULMOLOGY'),(819,'9119 Walkers Avenue','Handan','Bhutan','K.Mcgrew1@mobileme.no','Alfons','69846937506','Van Dinter','Pass224','666-3401','DOCTOR',16,'PULMOLOGY'),(820,'47 N. Pine Grove','Qingdao','Tunisia','Bart.Thaler1@excite.gov','Sammy','63005139593','van Dijk','Pass461','666-3844','DOCTOR',17,'ORTHOPEDIA'),(821,'9 South Main','Yixing','Bouvet Island','Franky.Johnson3@aol.dk','Lucille','56735128284','Ionescu','Pass308','666-4391','DOCTOR',17,'ORTHOPEDIA'),(822,'03 New London Street','Wulumuqi','India','KMoon@weboffice.ca','Co','07210478165','Mitchell','Pass422','666-8562','DOCTOR',17,'GENERAL'),(823,'7773 Trenton Avenue','Daqing','United States','FrankyRauch@kpn.net','Teddy','71280286032','Olson','Pass804','666-9344','DOCTOR',17,'GENERAL'),(824,'1311 Edith Marie Drive','Taipei','Vanuatu','Jack.Johnson@yahoo.gov','Tyler','54825003922','Moreau','Pass725','666-9000','DOCTOR',17,'CHEMICAL'),(825,'88 Palos Verdes Mall','Nagpur','Japan','PKoss1@myspace.it','Jesse','51100352597','Deans','Pass049','666-7812','DOCTOR',17,'CHEMICAL'),(826,'87 Sungrave Lane','Phoenix (AZ)','Bosnia and Herzegovina','Freddy.Stockton4@freeweb.no','Samantha','82421435824','Helbush','Pass825','666-5310','DOCTOR',17,'PSIHOLOGY'),(827,'54 N. Jackson Street','Osaka','Sri Lanka','Hank.Freed@hotmail.cn','Leon','94412341131','Climent','Pass104','666-9657','DOCTOR',17,'PSIHOLOGY'),(828,'363 Paddington Lane','Xintai','Kenya','TonArcadi@mail.be','Cristina','61946124272','Herzog','Pass348','666-0190','DOCTOR',17,'NEUROLOGICAL'),(829,'8323 Miranda Drive','Xintai','Equatorial Guinea','FreddySterrett@msn.cc','Chris','72639579647','Bloom','Pass045','666-9365','DOCTOR',17,'NEUROLOGICAL'),(830,'64 Eisenhower Avenue','Bogor','French Southern Territories','ISeibel@web.no','Madison','41167362969','van het Hof','Pass818','666-5086','DOCTOR',17,'PLASTICS'),(831,'8 W. Broadway','Gongzhuling','Anguilla','Richard.King4@mymail.com','Dylan','52261384347','Ionescu','Pass441','666-7095','DOCTOR',17,'PLASTICS'),(832,'9281 Northcreek Parkway','Heze','Liechtenstein','JohanHamilton@gmail.it','Talitha','56493310429','Robertson','Pass720','666-2786','DOCTOR',17,'CARDIO'),(833,'9 27th Street North','Hangzhou','Grenada','LThaler@weboffice.no','Ted','00020790455','Makelaar','Pass199','666-4261','DOCTOR',17,'CARDIO'),(834,'1190 Stony Field Rd','Medellín','Burkina Faso','HHankins@mymail.us','Daniela','93877649444','Sirabella','Pass276','666-6972','DOCTOR',17,'GENERAL'),(835,'1378 Melrose Ave','Multan','Lithuania','Mandy.Ionescu@freeweb.es','Rogier','56519016078','Ecchevarri','Pass678','666-8298','DOCTOR',17,'GENERAL'),(836,'1 Walsh Avenue','KINSHASA','Korea (South)','MickReames2@hotmail.ca','Peter','89968820281','Poissant','Pass233','666-8718','DOCTOR',17,'GINECOLOGY'),(837,'1809 Medford St','Kampong Cham','Portugal','K.Kingslan3@mymail.ca','Andrew','92778175355','Ionescu','Pass813','666-7192','DOCTOR',17,'GINECOLOGY'),(838,'4 Canyon Crest Drive','Pakalongan','Wallis and Futuna Islands','E.Stevens4@weboffice.it','Ricky','02323007158','Hulshof','Pass570','666-3274','DOCTOR',17,'PULMOLOGY'),(839,'231 Buckthorn Lane','Naples','Western Sahara','I.Otto@libero.de','Alva','09352950239','Warner','Pass866','666-8787','DOCTOR',17,'PULMOLOGY');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_appointments`
--

DROP TABLE IF EXISTS `doctor_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_appointments` (
  `doctor_id` bigint NOT NULL,
  `appointments_appointment_id` bigint NOT NULL,
  UNIQUE KEY `UK_ivi2ofpd8ijaf1pu5rplea3mb` (`appointments_appointment_id`),
  KEY `FK5u1cmc7duuj14hdmhei33ger9` (`doctor_id`),
  CONSTRAINT `FK5u1cmc7duuj14hdmhei33ger9` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`user_id`),
  CONSTRAINT `FKqjeojsmmvmw8ub8ih3d0k96ji` FOREIGN KEY (`appointments_appointment_id`) REFERENCES `appointment` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_appointments`
--

LOCK TABLES `doctor_appointments` WRITE;
/*!40000 ALTER TABLE `doctor_appointments` DISABLE KEYS */;
INSERT INTO `doctor_appointments` VALUES (44,7),(44,8),(44,9),(44,47),(45,16),(45,48),(47,19),(47,20),(50,1),(51,18),(52,17),(56,49),(60,2),(60,3),(60,4),(60,5),(60,51),(60,52),(61,10),(61,11),(61,12),(66,6),(66,13),(66,14),(66,15),(500,50),(504,40),(504,41),(506,22),(506,23),(506,28),(553,53),(558,54);
/*!40000 ALTER TABLE `doctor_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_grades`
--

DROP TABLE IF EXISTS `doctor_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_grades` (
  `doctor_id` bigint NOT NULL,
  `grades_grade_id` bigint NOT NULL,
  UNIQUE KEY `UK_6anh0tl051bk54g2b87jwxr7h` (`grades_grade_id`),
  KEY `FK4l1x36r5qn82wp2yx4je63h3h` (`doctor_id`),
  CONSTRAINT `FK4l1x36r5qn82wp2yx4je63h3h` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`user_id`),
  CONSTRAINT `FKl2bdoo2snfker3gkxp1ix6lwl` FOREIGN KEY (`grades_grade_id`) REFERENCES `grade` (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_grades`
--

LOCK TABLES `doctor_grades` WRITE;
/*!40000 ALTER TABLE `doctor_grades` DISABLE KEYS */;
INSERT INTO `doctor_grades` VALUES (50,1);
/*!40000 ALTER TABLE `doctor_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_leave_requests`
--

DROP TABLE IF EXISTS `doctor_leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_leave_requests` (
  `doctor_id` bigint NOT NULL,
  `leave_requests_leave_request_id` bigint NOT NULL,
  UNIQUE KEY `UK_5s8iq54p485w5tklc631v730u` (`leave_requests_leave_request_id`),
  KEY `FKk3apnqe056k5tgptisup2b6p6` (`doctor_id`),
  CONSTRAINT `FKcnea97w0w6gqh5fro66mh9emx` FOREIGN KEY (`leave_requests_leave_request_id`) REFERENCES `leave_request` (`leave_request_id`),
  CONSTRAINT `FKk3apnqe056k5tgptisup2b6p6` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_leave_requests`
--

LOCK TABLES `doctor_leave_requests` WRITE;
/*!40000 ALTER TABLE `doctor_leave_requests` DISABLE KEYS */;
INSERT INTO `doctor_leave_requests` VALUES (43,7),(43,23),(44,3),(44,22),(45,15),(46,8),(47,1),(48,18),(53,21),(55,4),(56,14),(56,25),(61,17),(62,20),(63,5),(66,13);
/*!40000 ALTER TABLE `doctor_leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `grade_id` bigint NOT NULL AUTO_INCREMENT,
  `grade_number` int DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `FKjvrg35m85p2rukvmd8tcqodjm` (`patient_id`),
  CONSTRAINT `FKjvrg35m85p2rukvmd8tcqodjm` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,10,22),(2,10,22);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('default',843);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_request`
--

DROP TABLE IF EXISTS `leave_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_request` (
  `leave_request_id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `date_form` datetime(6) NOT NULL,
  `date_to` datetime(6) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`leave_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_request`
--

LOCK TABLES `leave_request` WRITE;
/*!40000 ALTER TABLE `leave_request` DISABLE KEYS */;
INSERT INTO `leave_request` VALUES (1,_binary '','2020-01-01 00:57:00.000000','2020-04-20 10:37:00.000000',73),(2,_binary '','2020-01-01 08:58:00.000000','2020-02-08 07:13:00.000000',77),(3,_binary '\0','2020-01-01 08:56:00.000000','2020-03-25 01:35:00.000000',49),(4,_binary '','2020-01-01 09:01:00.000000','2020-03-31 03:37:00.000000',72),(5,_binary '','2020-01-01 04:16:00.000000','2020-04-11 01:36:00.000000',89),(6,_binary '\0','2020-01-01 06:41:00.000000','2020-05-04 06:19:00.000000',55),(7,_binary '','2020-01-01 04:30:00.000000','2020-06-22 08:39:00.000000',75),(8,_binary '','2020-01-01 09:10:00.000000','2020-05-28 06:34:00.000000',71),(9,_binary '','2020-01-01 01:51:00.000000','2020-02-28 00:22:00.000000',59),(10,_binary '\0','2020-01-01 07:37:00.000000','2020-02-06 05:53:00.000000',48),(11,_binary '','2020-01-01 06:57:00.000000','2020-05-29 06:21:00.000000',71),(12,_binary '\0','2020-01-01 02:54:00.000000','2020-03-17 07:15:00.000000',45),(13,_binary '\0','2020-01-01 08:30:00.000000','2020-07-01 09:53:00.000000',63),(14,_binary '\0','2020-01-01 08:14:00.000000','2020-03-13 08:18:00.000000',67),(15,_binary '','2020-01-01 08:01:00.000000','2020-01-06 06:54:00.000000',51),(16,_binary '','2020-01-01 01:29:00.000000','2020-07-01 01:08:00.000000',52),(17,_binary '\0','2020-01-01 10:47:00.000000','2020-01-28 09:26:00.000000',82),(18,_binary '','2020-01-01 01:18:00.000000','2020-03-11 04:13:00.000000',78),(19,_binary '\0','2020-01-01 10:41:00.000000','2020-03-04 05:26:00.000000',84),(20,_binary '\0','2020-01-01 08:27:00.000000','2020-05-22 09:33:00.000000',90),(21,_binary '','2020-01-01 06:33:00.000000','2020-03-30 07:11:00.000000',83),(22,_binary '\0','2020-01-01 07:02:00.000000','2020-01-19 01:35:00.000000',63),(23,_binary '\0','2020-01-01 03:35:00.000000','2020-03-26 05:58:00.000000',87),(24,_binary '\0','2020-01-01 08:36:00.000000','2020-01-17 10:10:00.000000',84),(25,_binary '','2020-01-01 02:55:00.000000','2020-03-12 06:05:00.000000',82),(26,_binary '\0','2020-01-01 00:37:00.000000','2020-01-29 02:05:00.000000',45),(27,_binary '','2020-01-01 07:14:00.000000','2020-06-02 06:11:00.000000',54),(28,_binary '','2020-01-01 08:39:00.000000','2020-03-22 10:45:00.000000',53),(29,_binary '\0','2020-01-01 00:15:00.000000','2020-01-28 02:56:00.000000',61),(30,_binary '','2020-01-01 03:54:00.000000','2020-06-28 09:17:00.000000',54),(31,_binary '','2020-01-01 04:32:00.000000','2020-06-16 07:57:00.000000',86),(32,_binary '\0','2020-01-01 03:37:00.000000','2020-05-22 00:49:00.000000',81),(33,_binary '','2020-01-01 01:15:00.000000','2020-02-14 10:05:00.000000',46),(34,_binary '','2020-01-01 00:38:00.000000','2020-01-18 08:15:00.000000',54),(35,_binary '\0','2020-01-01 06:45:00.000000','2020-06-10 03:02:00.000000',73),(36,_binary '','2020-01-01 02:19:00.000000','2020-04-05 03:27:00.000000',79),(37,_binary '','2020-01-01 10:19:00.000000','2020-03-08 01:34:00.000000',43),(38,_binary '','2020-01-01 05:27:00.000000','2020-05-02 10:41:00.000000',61),(39,_binary '\0','2020-01-01 02:07:00.000000','2020-02-05 06:04:00.000000',85),(40,_binary '\0','2020-01-01 09:55:00.000000','2020-06-12 03:42:00.000000',73),(41,_binary '\0','2020-01-01 10:20:00.000000','2020-05-25 04:39:00.000000',74),(42,_binary '\0','2020-01-01 04:28:00.000000','2020-06-24 05:48:00.000000',88),(43,_binary '','2020-01-01 05:42:00.000000','2020-02-20 00:00:00.000000',63),(44,_binary '','2020-01-01 07:34:00.000000','2020-03-14 04:51:00.000000',71),(45,_binary '\0','2020-01-01 08:06:00.000000','2020-02-18 07:46:00.000000',72),(46,_binary '','2020-01-01 05:37:00.000000','2020-05-31 10:54:00.000000',52),(47,_binary '','2020-01-01 08:40:00.000000','2020-01-20 10:58:00.000000',64),(48,_binary '\0','2020-01-01 09:32:00.000000','2020-04-29 01:37:00.000000',79),(49,_binary '\0','2020-01-01 02:31:00.000000','2020-05-27 06:03:00.000000',61),(50,_binary '\0','2020-01-01 03:58:00.000000','2020-04-21 04:39:00.000000',75),(51,_binary '\0','2020-01-01 09:53:00.000000','2020-03-05 03:48:00.000000',53),(52,_binary '\0','2020-01-01 04:08:00.000000','2020-04-28 04:41:00.000000',72),(53,_binary '\0','2020-01-01 07:04:00.000000','2020-06-14 07:14:00.000000',49),(54,_binary '','2020-01-01 00:02:00.000000','2020-03-31 06:21:00.000000',48),(55,_binary '','2020-01-01 05:40:00.000000','2020-01-18 02:01:00.000000',62),(56,_binary '','2020-01-01 02:28:00.000000','2020-05-11 08:55:00.000000',45),(57,_binary '\0','2020-01-01 03:53:00.000000','2020-04-04 10:40:00.000000',86),(58,_binary '\0','2020-01-01 05:55:00.000000','2020-04-16 09:40:00.000000',83),(59,_binary '\0','2020-01-01 09:09:00.000000','2020-01-12 00:42:00.000000',70),(60,_binary '\0','2020-01-01 05:41:00.000000','2020-03-18 08:07:00.000000',81),(61,_binary '\0','2020-01-01 06:39:00.000000','2020-06-01 03:15:00.000000',61),(62,_binary '','2020-01-01 05:09:00.000000','2020-03-02 10:15:00.000000',82),(63,_binary '','2020-01-01 10:05:00.000000','2020-05-17 06:00:00.000000',61),(64,_binary '','2020-01-01 08:03:00.000000','2020-03-05 06:10:00.000000',47),(65,_binary '\0','2020-01-01 10:44:00.000000','2020-06-08 05:53:00.000000',86),(66,_binary '','2020-01-01 05:10:00.000000','2020-06-05 01:55:00.000000',80),(67,_binary '\0','2020-01-01 05:38:00.000000','2020-05-22 10:08:00.000000',78),(68,_binary '','2020-01-01 03:38:00.000000','2020-04-03 02:19:00.000000',47),(69,_binary '\0','2020-01-01 08:48:00.000000','2020-05-13 06:57:00.000000',62),(70,_binary '\0','2020-01-01 01:28:00.000000','2020-02-05 04:40:00.000000',65),(71,_binary '','2020-01-01 09:39:00.000000','2020-03-30 07:16:00.000000',91),(72,_binary '','2020-01-01 08:53:00.000000','2020-01-01 08:00:00.000000',56),(73,_binary '\0','2020-01-01 04:56:00.000000','2020-02-05 02:03:00.000000',55),(74,_binary '\0','2020-01-01 04:38:00.000000','2020-02-15 03:18:00.000000',70),(75,_binary '','2020-01-01 08:37:00.000000','2020-05-20 09:08:00.000000',64),(76,_binary '','2020-01-01 07:00:00.000000','2020-03-14 08:36:00.000000',78),(77,_binary '','2020-01-01 09:36:00.000000','2020-02-16 03:07:00.000000',45),(78,_binary '\0','2020-01-01 03:06:00.000000','2020-03-21 07:23:00.000000',55),(79,_binary '\0','2020-01-01 04:22:00.000000','2020-04-14 10:19:00.000000',69),(80,_binary '','2020-01-01 10:03:00.000000','2020-02-04 09:49:00.000000',63),(81,_binary '\0','2020-01-01 06:26:00.000000','2020-01-25 06:06:00.000000',51),(82,_binary '\0','2020-01-01 06:07:00.000000','2020-01-04 03:48:00.000000',42),(83,_binary '','2020-01-01 10:01:00.000000','2020-03-17 09:50:00.000000',44),(84,_binary '\0','2020-01-01 07:10:00.000000','2020-01-20 03:09:00.000000',60),(85,_binary '','2020-01-01 03:42:00.000000','2020-05-25 09:36:00.000000',68),(86,_binary '\0','2020-01-01 03:05:00.000000','2020-03-03 03:34:00.000000',61),(87,_binary '\0','2020-01-01 04:29:00.000000','2020-05-04 00:55:00.000000',44),(88,_binary '','2020-01-01 09:26:00.000000','2020-03-12 02:23:00.000000',67),(89,_binary '','2020-01-01 06:55:00.000000','2020-06-01 06:53:00.000000',46),(90,_binary '','2020-01-01 09:58:00.000000','2020-01-19 08:15:00.000000',72),(91,_binary '\0','2020-01-01 06:10:00.000000','2020-01-23 06:20:00.000000',72),(92,_binary '\0','2020-01-01 07:11:00.000000','2020-03-08 06:51:00.000000',46),(93,_binary '','2020-01-01 01:41:00.000000','2020-06-07 05:34:00.000000',84),(94,_binary '','2020-01-01 10:40:00.000000','2020-03-21 02:01:00.000000',61),(95,_binary '\0','2020-01-01 02:23:00.000000','2020-06-13 07:35:00.000000',82),(96,_binary '','2020-01-01 04:01:00.000000','2020-04-15 08:03:00.000000',83),(97,_binary '\0','2020-01-01 02:21:00.000000','2020-06-26 07:26:00.000000',79),(98,_binary '\0','2020-01-01 10:52:00.000000','2020-04-10 06:20:00.000000',63),(99,_binary '','2020-01-01 09:33:00.000000','2020-05-22 03:57:00.000000',56),(100,_binary '','2020-01-01 08:21:00.000000','2020-05-22 07:39:00.000000',62),(101,_binary '\0','2020-05-16 01:00:00.000000','2020-05-31 01:00:00.000000',67),(102,_binary '','2020-05-31 01:00:00.000000','2020-06-05 01:00:00.000000',142);
/*!40000 ALTER TABLE `leave_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `medical_record_id` bigint NOT NULL AUTO_INCREMENT,
  `birthday` datetime(6) DEFAULT NULL,
  `blood_pressure` int NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `height` int NOT NULL,
  `medical_history` varchar(255) NOT NULL,
  `weight` int NOT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`medical_record_id`),
  KEY `FKt0lf3feuiurr73bpln2n6x0v` (`patient_id`),
  CONSTRAINT `FKt0lf3feuiurr73bpln2n6x0v` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES (1,'2019-10-03 04:52:00.000000',180,'A-','MALE',195,'MedicalHistory-MW',73,22),(2,'1997-10-03 09:19:00.000000',95,'0+','FEMALE',205,'MedicalHistory-OM',114,23),(3,'2000-01-30 04:13:00.000000',121,'AB-','MALE',195,'MedicalHistory-WL',65,24),(4,'2010-11-23 03:33:00.000000',106,'AB-','MALE',205,'MedicalHistory-JC',45,25),(5,'1979-07-12 10:49:00.000000',35,'B+','FEMALE',161,'MedicalHistory-LS',95,26),(6,'2018-09-21 08:28:00.000000',69,'A-','FEMALE',168,'MedicalHistory-ME',118,27),(7,'2005-02-23 10:06:00.000000',130,'A+','MALE',209,'MedicalHistory-MG',93,28),(8,'1995-10-27 06:10:00.000000',171,'0+','MALE',108,'MedicalHistory-TO',47,29),(9,'2007-11-10 08:01:00.000000',91,'AB+','MALE',137,'MedicalHistory-VZ',49,30),(10,'2018-06-13 01:31:00.000000',138,'B-','FEMALE',103,'MedicalHistory-PH',106,31),(11,'2009-11-01 00:27:00.000000',69,'B-','MALE',209,'MedicalHistory-ZA',96,32),(12,'1966-04-25 06:33:00.000000',200,'AB+','FEMALE',156,'MedicalHistory-FP',92,33),(13,'2008-06-18 01:52:00.000000',125,'A+','MALE',170,'MedicalHistory-ES',85,34),(14,'2014-07-17 01:55:00.000000',123,'A-','MALE',149,'MedicalHistory-DD',68,35),(15,'1995-06-01 03:23:00.000000',144,'AB+','MALE',121,'MedicalHistory-BI',61,36),(16,'2016-01-11 06:53:00.000000',96,'B-','MALE',103,'MedicalHistory-LK',110,37),(17,'1979-10-18 07:54:00.000000',6,'AB-','FEMALE',199,'MedicalHistory-ST',67,38),(18,'2001-08-20 08:29:00.000000',90,'B+','MALE',201,'MedicalHistory-UO',106,39),(19,'2015-05-28 10:53:00.000000',16,'A+','FEMALE',110,'MedicalHistory-VC',71,40),(20,'1963-10-26 04:01:00.000000',68,'B+','MALE',124,'MedicalHistory-XS',130,41),(22,NULL,0,'','',0,'',0,242),(23,NULL,0,'','',0,'',0,242),(24,NULL,0,'','',0,'',0,242),(25,NULL,0,'','',0,'',0,242),(26,NULL,0,'','',0,'',0,242),(27,NULL,0,'','',0,'',0,242),(28,NULL,0,'','',0,'',0,242),(29,NULL,0,'','',0,'',0,242),(30,NULL,0,'','',0,'',0,242),(31,NULL,0,'','',0,'',0,242),(32,NULL,0,'','',0,'',0,242),(33,NULL,0,'','',0,'',0,250);
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record_allergies`
--

DROP TABLE IF EXISTS `medical_record_allergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record_allergies` (
  `medical_redord_id` bigint NOT NULL,
  `allergies` int DEFAULT NULL,
  KEY `FK9x0kqeobfp6dv4i8tngcuxga8` (`medical_redord_id`),
  CONSTRAINT `FK9x0kqeobfp6dv4i8tngcuxga8` FOREIGN KEY (`medical_redord_id`) REFERENCES `medical_record` (`medical_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_allergies`
--

LOCK TABLES `medical_record_allergies` WRITE;
/*!40000 ALTER TABLE `medical_record_allergies` DISABLE KEYS */;
INSERT INTO `medical_record_allergies` VALUES (9,4),(9,10),(9,7),(1,10),(1,9),(1,2),(12,3),(1,8),(1,4),(11,6),(11,2),(11,7),(1,9),(1,7),(1,3),(13,2),(13,3),(13,5),(13,9);
/*!40000 ALTER TABLE `medical_record_allergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record_perscriptions`
--

DROP TABLE IF EXISTS `medical_record_perscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record_perscriptions` (
  `medical_record_medical_record_id` bigint NOT NULL,
  `perscriptions_medication_id` bigint NOT NULL,
  PRIMARY KEY (`medical_record_medical_record_id`,`perscriptions_medication_id`),
  KEY `FK7s0h7p36q0iyipwmumf73ejqy` (`perscriptions_medication_id`),
  CONSTRAINT `FK7s0h7p36q0iyipwmumf73ejqy` FOREIGN KEY (`perscriptions_medication_id`) REFERENCES `medication` (`medication_id`),
  CONSTRAINT `FKrxjxi2vxnbeegb21e3duiapdt` FOREIGN KEY (`medical_record_medical_record_id`) REFERENCES `medical_record` (`medical_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_perscriptions`
--

LOCK TABLES `medical_record_perscriptions` WRITE;
/*!40000 ALTER TABLE `medical_record_perscriptions` DISABLE KEYS */;
INSERT INTO `medical_record_perscriptions` VALUES (16,1),(13,3),(19,3),(1,4),(2,5),(18,6),(20,7),(9,10),(11,11),(6,13),(7,14),(8,15),(4,16),(12,19),(5,21),(15,22),(3,23),(10,24),(14,25),(13,36);
/*!40000 ALTER TABLE `medical_record_perscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_report`
--

DROP TABLE IF EXISTS `medical_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_report` (
  `medical_report_id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `details` varchar(255) NOT NULL,
  `medical_report_nurse_id` bigint DEFAULT NULL,
  PRIMARY KEY (`medical_report_id`),
  KEY `FKcyro5w7l619orw3lrtruxhlgy` (`medical_report_nurse_id`),
  CONSTRAINT `FKcyro5w7l619orw3lrtruxhlgy` FOREIGN KEY (`medical_report_nurse_id`) REFERENCES `nurse` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_report`
--

LOCK TABLES `medical_report` WRITE;
/*!40000 ALTER TABLE `medical_report` DISABLE KEYS */;
INSERT INTO `medical_report` VALUES (1,_binary '','Medical report-DD',142),(2,_binary '','Medical report-ST',142),(3,_binary '','Medical report-VX',142),(4,_binary '','Medical report-YJ',142),(5,_binary '\0','Medical report-IK',142),(6,_binary '\0','Medical report-FA',143),(7,_binary '','Medical report-PN',143),(8,_binary '\0','Medical report-FB',143),(9,_binary '','Medical report-OJ',143),(10,_binary '\0','Medical report-AR',143),(11,_binary '','Medical report-VB',144),(13,_binary '','Medical report-AR',144),(14,_binary '\0','Medical report-FH',144),(15,_binary '\0','Medical report-LQ',144),(16,_binary '','Medical report-CP',145),(17,_binary '','Medical report-FU',145),(18,_binary '\0','Medical report-HX',145),(19,_binary '','Medical report-WE',145),(20,_binary '','Medical report-MD',145),(30,_binary '\0','Patient experiences nausea and headache',148),(33,_binary '\0','Patient experiences nausea and headache',148);
/*!40000 ALTER TABLE `medical_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_report_diagnosis`
--

DROP TABLE IF EXISTS `medical_report_diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_report_diagnosis` (
  `medical_report_medical_report_id` bigint NOT NULL,
  `diagnosis_diagnosis_id` bigint NOT NULL,
  PRIMARY KEY (`medical_report_medical_report_id`,`diagnosis_diagnosis_id`),
  KEY `FKjm3ot020yvu5ct9l3m406xiu0` (`diagnosis_diagnosis_id`),
  CONSTRAINT `FK8krbs23iuaedsexfhx66ev7bu` FOREIGN KEY (`medical_report_medical_report_id`) REFERENCES `medical_report` (`medical_report_id`),
  CONSTRAINT `FKjm3ot020yvu5ct9l3m406xiu0` FOREIGN KEY (`diagnosis_diagnosis_id`) REFERENCES `diagnosis` (`diagnosis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_report_diagnosis`
--

LOCK TABLES `medical_report_diagnosis` WRITE;
/*!40000 ALTER TABLE `medical_report_diagnosis` DISABLE KEYS */;
INSERT INTO `medical_report_diagnosis` VALUES (1,2),(33,2),(1,5),(18,7),(19,8),(18,12),(18,14),(20,15),(20,18),(19,20),(1,22),(17,23);
/*!40000 ALTER TABLE `medical_report_diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_report_medication`
--

DROP TABLE IF EXISTS `medical_report_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_report_medication` (
  `medical_report_medical_report_id` bigint NOT NULL,
  `medication_medication_id` bigint NOT NULL,
  PRIMARY KEY (`medical_report_medical_report_id`,`medication_medication_id`),
  KEY `FK49jhlptb1chk0atcwlg8csemj` (`medication_medication_id`),
  CONSTRAINT `FK49jhlptb1chk0atcwlg8csemj` FOREIGN KEY (`medication_medication_id`) REFERENCES `medication` (`medication_id`),
  CONSTRAINT `FKbk0wkfnbpqvk6aurg9n48amu4` FOREIGN KEY (`medical_report_medical_report_id`) REFERENCES `medical_report` (`medical_report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_report_medication`
--

LOCK TABLES `medical_report_medication` WRITE;
/*!40000 ALTER TABLE `medical_report_medication` DISABLE KEYS */;
INSERT INTO `medical_report_medication` VALUES (15,2),(1,3),(1,7),(14,9),(15,10),(19,22),(15,23),(1,24),(33,26),(33,36);
/*!40000 ALTER TABLE `medical_report_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medication` (
  `medication_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `medication_clinical_center_id` bigint DEFAULT NULL,
  PRIMARY KEY (`medication_id`),
  KEY `FK45am24tctex5ksvrc4tpbj6ed` (`medication_clinical_center_id`),
  CONSTRAINT `FK45am24tctex5ksvrc4tpbj6ed` FOREIGN KEY (`medication_clinical_center_id`) REFERENCES `clinical_center` (`clinic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'MED-240','MEDICATION-FT',1),(2,'MED-093','MEDICATION-CY',1),(3,'MED-835','MEDICATION-RJ',1),(4,'MED-677','MEDICATION-PO',1),(5,'MED-582','MEDICATION-OK',1),(6,'MED-600','MEDICATION-WR',1),(7,'MED-394','MEDICATION-LZ',1),(8,'MED-910','MEDICATION-WH',1),(9,'MED-337','MEDICATION-HC',1),(10,'MED-259','MEDICATION-LA',1),(11,'MED-746','MEDICATION-EQ',1),(13,'MED-787','MEDICATION-VJ',1),(14,'MED-571','MEDICATION-TB',1),(15,'MED-791','MEDICATION-FX',1),(16,'MED-837','MEDICATION-WC',1),(17,'MED-214','MEDICATION-KP',1),(18,'MED-357','MEDICATION-YK',1),(19,'MED-678','MEDICATION-XM',1),(20,'MED-272','MEDICATION-PB',1),(21,'MED-756','MEDICATION-CR',1),(22,'MED-074','MEDICATION-HE',1),(23,'MED-913','MEDICATION-OV',1),(24,'MED-896','MEDICATION-HJ',1),(25,'MED-119','MEDICATION-JT',1),(26,'MED-400','BRUFEN 400mg',1),(27,'MED-200','BRUFEN 200mg',1),(28,'MED-800','BRUFEN 800mg',1),(29,'MED-222','CAFETIN COLD',1),(30,'MED-248','ASPIRIN',1),(31,'MED-243','ANALGIN',1),(32,'MED-211','DIKLOFENAK',1),(33,'MED-209','STREPSILS PLUS',1),(35,'MED-111','BROMAZEPAM',1),(36,'MED-123','RIVOTRIL',1),(39,'MED-666','OLIGOGAL SE',1);
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `nurse_clinic_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK6e3crcdp63re55x1r2kgk2v3t` (`nurse_clinic_id`),
  CONSTRAINT `FK6e3crcdp63re55x1r2kgk2v3t` FOREIGN KEY (`nurse_clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (142,'3107 N. Central Ave.','Yulin','Palau','n@n','Liam','26834256021','Brown','123','666-7426','NURSE',15),(143,'442 Brimming Boulevard','YANGON','Tuvalu','m@m','Andy','10069307498','Plantz','123','666-8169','NURSE',16),(144,'794 Waterford Street','Xiaoshan','Israel','FransFranklin3@libero.de','Max','73755070984','Botsik','Pass263','666-8149','NURSE',16),(145,'80 B. Jackson Rd','Wulumuqi','Macau','I.Alspaugh2@mobileme.dk','Iris','24454368753','Griffith','Pass244','666-0749','NURSE',17),(146,'14 ZW Bruce Street','Tianshui','Thailand','HankJessen@hotmail.no','Anton','01320949003','van der Laar','Pass275','666-3927','NURSE',17),(147,'55 2th Ave.','Dnepropetrovsk','Palau','Bas.Makelaar4@msn.cn','Juan','43953777284','Lawton','Pass087','666-8295','NURSE',18),(148,'1663 North 27th Street','Netzahualcóyotl','China','Ann.Nithman@freeweb.co.uk','Lucy','28490940784','Liddle','Pass621','666-0024','NURSE',18),(149,'3738 Chehalis Road','Jiangyin','Heard and McDonald Islands','Ron.Herring3@mymail.cc','Susan','00866948467','Love','Pass301','666-0404','NURSE',19),(150,'408 Stewart Ave','Abidjan','Guinea-Bissau','Rick.McCormick@aol.nl','Mike','44363330450','Framus','Pass222','666-1823','NURSE',20),(151,'6 Fox Squirrel Lane','Alexandria','St. Helena','M.Rauch3@libero.cn','Jill','33164329376','Paul','Pass079','666-3999','NURSE',20),(152,'340 North MacArthur','Lanzhou','Turkey','I.Poissant2@live.es','Edwin','55321681552','Johnson','Pass439','666-5735','NURSE',20),(153,'427 Medford St','Inchon','Virgin Islands (U.S.)','Maarten.Byrnes@web.be','Leah','50568825550','Lejarette','Pass908','666-6823','NURSE',1),(154,'683 ZW Bruce Street','Ludhiana','Yemen','Frans.Sterrett@gawab.dk','Siska','08047110593','Guyer','Pass317','666-0383','NURSE',2),(155,'8 Gilbert Street','NAIROBI','Armenia','Nick.Dulisse@libero.ca','Benjy','14236816850','Braconi','Pass792','666-2552','NURSE',2),(156,'2082 Prospect Hill','Moscow','Dominican Republic','I.Bogdanovich@excite.nl','Jill','42569268977','Conley','Pass862','666-0908','NURSE',2),(157,'7 W. Sunnyside Ave.','Chittagong','Egypt','GeoffryStorrs@weboffice.no','Carlos','83380449031','Keller','Pass365','666-7229','NURSE',3),(158,'875 Brandon Marshall Street','RABAT','Cuba','HankPress@kpn.de','Peggy','51222256142','Press','Pass724','666-5165','NURSE',4),(159,'015 Dallas Parkway','Inchon','Latvia','EmmaBrown@gawab.de','Rick','88038064505','Poole','Pass595','666-7325','NURSE',5),(160,'151 Scrutton Street','Detroit (MI)','Guatemala','P.Hopper3@aol.net','Sergio','77471123421','Tudisco','Pass872','666-3145','NURSE',6),(161,'12 Trenton Avenue','Zhucheng','Vatican City State (Holy See)','LindsyWooten1@aol.co.uk','Janet','98046941399','Weaver','Pass570','666-7534','NURSE',6),(162,'92 Ninth Avenue','Prague','Bermuda','Frans.King@gawab.org','Jordy','93073014243','Uprovski','Pass927','666-7695','NURSE',6),(163,'7602 Paddington Lane','KINSHASA','Samoa','Martin.Phillips@dolfijn.de','Jesse','05838092769','Hedgecock','Pass146','666-8322','NURSE',7),(164,'451 Wilder St.','Dongtai','Chad','JOrcutt@aol.it','Ed','01924070134','Bright','Pass192','666-9149','NURSE',7),(165,'3 Paddington Lane','Bogor','Poland','RManson2@mymail.gov','Sanne','93132485074','Nithman','Pass992','666-5767','NURSE',8),(166,'33 Pacella Park Drive','MONTEVIDEO','Liechtenstein','DickStannard5@myspace.no','Steven','54086267368','Richter','Pass810','666-9001','NURSE',9);
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse_leave_requests`
--

DROP TABLE IF EXISTS `nurse_leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse_leave_requests` (
  `nurse_id` bigint NOT NULL,
  `leave_requests_leave_request_id` bigint NOT NULL,
  UNIQUE KEY `UK_90gp4c757m7qxq2h3e3vdyb6s` (`leave_requests_leave_request_id`),
  KEY `FKsadjddrtn70lted02t0fnwufo` (`nurse_id`),
  CONSTRAINT `FKfvoejwm4mnf7k9gksm0nbpgpl` FOREIGN KEY (`leave_requests_leave_request_id`) REFERENCES `leave_request` (`leave_request_id`),
  CONSTRAINT `FKsadjddrtn70lted02t0fnwufo` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse_leave_requests`
--

LOCK TABLES `nurse_leave_requests` WRITE;
/*!40000 ALTER TABLE `nurse_leave_requests` DISABLE KEYS */;
INSERT INTO `nurse_leave_requests` VALUES (142,102);
/*!40000 ALTER TABLE `nurse_leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse_medical_reports`
--

DROP TABLE IF EXISTS `nurse_medical_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse_medical_reports` (
  `nurse_id` bigint NOT NULL,
  `medical_reports_medical_report_id` bigint NOT NULL,
  UNIQUE KEY `UK_nbbv5812a3irl4jb4q2pd42wn` (`medical_reports_medical_report_id`),
  KEY `FK1bfdk9we7t88bkx22psqahrqj` (`nurse_id`),
  CONSTRAINT `FK1bfdk9we7t88bkx22psqahrqj` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`user_id`),
  CONSTRAINT `FKhg9kb6b67vitgl0ucxuwp55vt` FOREIGN KEY (`medical_reports_medical_report_id`) REFERENCES `medical_report` (`medical_report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse_medical_reports`
--

LOCK TABLES `nurse_medical_reports` WRITE;
/*!40000 ALTER TABLE `nurse_medical_reports` DISABLE KEYS */;
INSERT INTO `nurse_medical_reports` VALUES (142,1),(142,2),(142,3),(142,4),(142,5),(143,6),(143,7),(143,8),(143,9),(143,10),(144,11),(144,13),(144,14),(144,15),(145,16),(145,17),(145,18),(145,19),(145,20),(148,30),(148,33);
/*!40000 ALTER TABLE `nurse_medical_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordination`
--

DROP TABLE IF EXISTS `ordination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordination` (
  `ordination_number` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `clinic_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ordination_number`),
  KEY `FKb3f9ql0k1s3ilbw0aiikim98t` (`clinic_id`),
  CONSTRAINT `FKb3f9ql0k1s3ilbw0aiikim98t` FOREIGN KEY (`clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordination`
--

LOCK TABLES `ordination` WRITE;
/*!40000 ALTER TABLE `ordination` DISABLE KEYS */;
INSERT INTO `ordination` VALUES (1,'ORD89','OPERATION',20),(2,'ORD23','EXAM',1),(3,'ORD54','EXAM',7),(4,'ORD44','OPERATION',6),(5,'ORD24','EXAM',16),(6,'ORD95','OPERATION',1),(7,'ORD96','EXAM',14),(8,'ORD93','OPERATION',5),(9,'ORD58','EXAM',11),(10,'ORD48','OPERATION',16),(11,'ORD38','OPERATION',15),(12,'ORD08','OPERATION',8),(13,'ORD45','OPERATION',1),(14,'ORD17','EXAM',13),(15,'ORD16','EXAM',13),(16,'ORD98','EXAM',18),(17,'ORD84','EXAM',5),(18,'ORD76','EXAM',10),(19,'ORD73','OPERATION',11),(20,'ORD64','EXAM',11),(21,'ORD69','OPERATION',12),(22,'ORD85','OPERATION',10),(23,'ORD75','OPERATION',11),(24,'ORD22','EXAM',10),(25,'ORD80','OPERATION',17),(26,'ORD05','EXAM',18),(27,'ORD46','EXAM',17),(28,'ORD40','OPERATION',1),(29,'ORD92','OPERATION',5),(30,'ORD47','EXAM',19),(842,'room1','EXAM',3),(843,'room2','EXAM',3);
/*!40000 ALTER TABLE `ordination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordination_appointments`
--

DROP TABLE IF EXISTS `ordination_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordination_appointments` (
  `ordination_id` int NOT NULL,
  `appointments_appointment_id` bigint NOT NULL,
  UNIQUE KEY `UK_jfrwn2gj02cpaea7lsvnekrtn` (`appointments_appointment_id`),
  KEY `FKn23diwu4ogbb1d26hf19hlk38` (`ordination_id`),
  CONSTRAINT `FK4o5kyvsves4ggdbmk66e9qji2` FOREIGN KEY (`appointments_appointment_id`) REFERENCES `appointment` (`appointment_id`),
  CONSTRAINT `FKn23diwu4ogbb1d26hf19hlk38` FOREIGN KEY (`ordination_id`) REFERENCES `ordination` (`ordination_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordination_appointments`
--

LOCK TABLES `ordination_appointments` WRITE;
/*!40000 ALTER TABLE `ordination_appointments` DISABLE KEYS */;
INSERT INTO `ordination_appointments` VALUES (2,3),(2,11),(3,18),(4,13),(6,19),(7,8),(7,15),(10,17),(11,6),(14,7),(15,4),(17,2),(17,12),(18,5),(18,9),(19,14),(20,1),(20,47),(20,48),(20,49),(20,51),(20,52),(21,10),(22,20),(25,16),(28,22),(28,23),(28,28),(28,40),(28,41),(28,50),(842,53),(842,54);
/*!40000 ALTER TABLE `ordination_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `medical_record_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK1w6vp11iqf5ifhw3g5106wjcl` (`medical_record_id`),
  CONSTRAINT `FK1w6vp11iqf5ifhw3g5106wjcl` FOREIGN KEY (`medical_record_id`) REFERENCES `medical_record` (`medical_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (22,'0 Sutter Street','Taiyuan','Egypt','p@p','Alexandert','69371915620','Mcnally','123','666-2887','PATIENT',1),(23,'523 River Rd','Tianmen','Guadeloupe','u@u','Jim','18424517952','Antonucci','123','666-4266','PATIENT',2),(24,'006 Northcreek Parkway','Datong','Gibraltar','BiancaNobles5@myspace.nl','Bengie','08337977360','Tudisco','Pass125','666-9215','PATIENT',3),(25,'228 E. Plumb Lane','MANAGUA','Togo','CiskaHancock@yahoo.cn','Hank','80699596130','Shapiro','Pass833','666-6895','PATIENT',4),(26,'8458 Swallowtail Ct.','SEOUL','Kyrgyzstan','RichardMcnally@yahoo.no','Margaret','61837816809','Ionescu','Pass387','666-3474','PATIENT',5),(27,'7085 Pacella Park Drive','Kobe','Namibia','HShapiro@msn.com','Dirk','88552831630','Gieske','Pass344','666-2026','PATIENT',6),(28,'29 Lakewood Drive','Weifang','Grenada','GGreen@myspace.be','Florian','12137856078','Laudanski','Pass245','666-1477','PATIENT',7),(29,'4 Maxwell Ave.','Heze','Dominican Republic','Eric.Turk5@lycos.com','Luis','50271051075','Wooten','Pass028','666-3159','PATIENT',8),(30,'91 Acorn Dr.','Ahmedabad','Azerbaijan','T.Guethlein@hotmail.org','Erik','55741347386','Helfrich','Pass901','666-2787','PATIENT',9),(31,'453 Brooke Valley Drive','Luoyang','Nicaragua','NickCramer@telfort.gov','Paula','52720183050','Depew','Pass605','666-7026','PATIENT',10),(32,'13 Terminal Way','Neijiang','Peru','LynnIonescu@yahoo.dk','Sophia','75458671654','Cramer','Pass871','666-0804','PATIENT',11),(33,'838 J. Carcione Way','Shijiazhuang','Heard and McDonald Islands','Peter.Williamson@hotmail.es','Leah','58877947196','Conley','Pass767','666-5156','PATIENT',12),(34,'7676 Sutter St','Nanning','Antarctica','E.Herring@mymail.net','Ross','75551112677','Franklin','Pass454','666-5398','PATIENT',13),(35,'934 Sungrave Lane','Shiraz','Cayman Islands','R.Harness5@dolfijn.it','Annie','18534488160','Pengilly','Pass038','666-1082','PATIENT',14),(36,'37 Village Road South','Weifang','Algeria','Paul.Robbins@dolfijn.net','Pauline','53531651909','Lezniak','Pass998','666-4230','PATIENT',15),(37,'916 Business Center Dr.','Changsha','Sweden','Frank.Haynes@mymail.org','Victor','78688741967','Hummel','Pass972','666-0878','PATIENT',16),(38,'600 S. Maryland Ave','Jinan','Finland','J.Harder@libero.ca','Bram','66454424893','Bogdanovich','Pass215','666-7902','PATIENT',17),(39,'1441 University ave.','Ruian','Libya','Will.Riegel@mobileme.co.uk','Victor','54845652099','Bruno','Pass938','666-6504','PATIENT',18),(40,'7 Corporate Drive','GUATEMALA CITY','Chad','FredManson1@mail.ca','Rik','71222841637','Chwatal','Pass398','666-8458','PATIENT',19),(41,'58 Wilder St.','ROMA','Cyprus','Rogier.Walker@hotmail.fr','Trevor','78098681814','Gieske','Pass211','666-0697','PATIENT',20),(242,'3735 Redwood Ave.','Guikong','Somalia','HansAngarano@mail.cn','Harold','21748493896','Donatelli','Pass788','666-8795','PATIENT',32),(250,'Knez Mihailova 66','Beograd','Srbija','marko.markovic@uns.ac.rs','Marko','1234567898','Markovic','123','0654327878','PATIENT',33);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_appointments`
--

DROP TABLE IF EXISTS `patient_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_appointments` (
  `patient_id` bigint NOT NULL,
  `appointments_appointment_id` bigint NOT NULL,
  UNIQUE KEY `UK_b7b9oh1v47h47f9r1b3h7adjd` (`appointments_appointment_id`),
  KEY `FK9t5466bjrp5l4ikqlwq7my18m` (`patient_id`),
  CONSTRAINT `FK9t5466bjrp5l4ikqlwq7my18m` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`user_id`),
  CONSTRAINT `FKe5cng7y1ji3ul5omfta5ubq2r` FOREIGN KEY (`appointments_appointment_id`) REFERENCES `appointment` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_appointments`
--

LOCK TABLES `patient_appointments` WRITE;
/*!40000 ALTER TABLE `patient_appointments` DISABLE KEYS */;
INSERT INTO `patient_appointments` VALUES (22,1),(22,22),(22,23),(22,28),(22,40),(22,41),(22,51),(22,54),(22,55),(23,2),(24,3),(24,52),(25,4),(26,5),(27,6),(27,47),(27,48),(27,49),(27,50),(28,7),(29,8),(30,9),(31,10),(32,11),(33,12),(34,13),(35,14),(36,15),(37,16),(38,17),(39,18),(40,19),(41,20);
/*!40000 ALTER TABLE `patient_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist`
--

DROP TABLE IF EXISTS `pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist` (
  `pricelist_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pricelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` VALUES (1),(2),(3),(4),(5);
/*!40000 ALTER TABLE `pricelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist_clinics`
--

DROP TABLE IF EXISTS `pricelist_clinics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist_clinics` (
  `pricelist_id` bigint NOT NULL,
  `clinics_clinic_id` bigint NOT NULL,
  UNIQUE KEY `UK_plc499909ho72fx21a9w6ce6j` (`clinics_clinic_id`),
  KEY `FKdltp8h4x0wt238jmurqbcl9ho` (`pricelist_id`),
  CONSTRAINT `FKdltp8h4x0wt238jmurqbcl9ho` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKhll1165b9p1n800gb9btase63` FOREIGN KEY (`clinics_clinic_id`) REFERENCES `clinic` (`clinic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist_clinics`
--

LOCK TABLES `pricelist_clinics` WRITE;
/*!40000 ALTER TABLE `pricelist_clinics` DISABLE KEYS */;
INSERT INTO `pricelist_clinics` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,22),(1,23);
/*!40000 ALTER TABLE `pricelist_clinics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist_item`
--

DROP TABLE IF EXISTS `pricelist_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist_item` (
  `pricelist_item_id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `pricelist_id` bigint DEFAULT NULL,
  `appointment_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pricelist_item_id`),
  KEY `FKrj0fqhdlepbpcno6pidue6b0w` (`pricelist_id`),
  CONSTRAINT `FKrj0fqhdlepbpcno6pidue6b0w` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist_item`
--

LOCK TABLES `pricelist_item` WRITE;
/*!40000 ALTER TABLE `pricelist_item` DISABLE KEYS */;
INSERT INTO `pricelist_item` VALUES (1,1.21,1,'ORTHOPEDIA'),(2,33.17,1,'GENERAL'),(3,5.15,1,'CHEMICAL'),(4,41.78,1,'PSIHOLOGY'),(5,7.49,1,'NEUROLOGICAL'),(6,6.69,1,'PLASTICS'),(7,38.84,1,'CARDIO'),(8,14.87,1,'SURGERY'),(9,8.6,1,'GINECOLOGY'),(10,9.68,1,'PULMOLOGY');
/*!40000 ALTER TABLE `pricelist_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist_pricelis_items`
--

DROP TABLE IF EXISTS `pricelist_pricelis_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist_pricelis_items` (
  `pricelist_id` bigint NOT NULL,
  `pricelist_items_pricelist_item_id` bigint NOT NULL,
  UNIQUE KEY `UK_a7nxa2dxc5lwjwbp08p7l5agd` (`pricelist_items_pricelist_item_id`),
  KEY `FKl17nfrtwe44qxhqc64ypj1gtt` (`pricelist_id`),
  CONSTRAINT `FKl17nfrtwe44qxhqc64ypj1gtt` FOREIGN KEY (`pricelist_id`) REFERENCES `pricelist` (`pricelist_id`),
  CONSTRAINT `FKnf7809hku2clo5ca4otkkp2iw` FOREIGN KEY (`pricelist_items_pricelist_item_id`) REFERENCES `pricelist_item` (`pricelist_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist_pricelis_items`
--

LOCK TABLES `pricelist_pricelis_items` WRITE;
/*!40000 ALTER TABLE `pricelist_pricelis_items` DISABLE KEYS */;
INSERT INTO `pricelist_pricelis_items` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10);
/*!40000 ALTER TABLE `pricelist_pricelis_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_request`
--

DROP TABLE IF EXISTS `registration_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration_request` (
  `registration_request_id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `clinical_center_administrator_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`registration_request_id`),
  KEY `FK77lt6gxlwn4pq0yp6ga1kkv0p` (`clinical_center_administrator_id`),
  KEY `FK1g1viwugb84gm74canvatei6a` (`user_id`),
  CONSTRAINT `FK1g1viwugb84gm74canvatei6a` FOREIGN KEY (`user_id`) REFERENCES `patient` (`user_id`),
  CONSTRAINT `FK77lt6gxlwn4pq0yp6ga1kkv0p` FOREIGN KEY (`clinical_center_administrator_id`) REFERENCES `clinical_center_administrator` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_request`
--

LOCK TABLES `registration_request` WRITE;
/*!40000 ALTER TABLE `registration_request` DISABLE KEYS */;
INSERT INTO `registration_request` VALUES (1,_binary '',1,242),(7,_binary '',1,250);
/*!40000 ALTER TABLE `registration_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 21:13:40
