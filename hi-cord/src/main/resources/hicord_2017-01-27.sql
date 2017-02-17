# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.19-MariaDB)
# Database: hicord
# Generation Time: 2017-01-27 04:30:12 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table BOARD
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BOARD`;

CREATE TABLE `BOARD` (
  `BOARD_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BOARD_TYPE` varchar(10) NOT NULL,
  `BOARD_SUBJECT` varchar(255) NOT NULL,
  `BOARD_CONTENT` longtext NOT NULL,
  `BOARD_HITS` int(20) DEFAULT '0',
  `BOARD_LIKES` int(10) DEFAULT '0',
  `BOARD_REPLY_DEPTH` int(15) DEFAULT '0',
  `BOARD_COMMENT_DEPTH` int(15) DEFAULT '0',
  `BOARD_CREATED_BY` varchar(60) NOT NULL,
  `BOARD_CREATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `BOARD_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `BOARD_MODIFICATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `BOARD_DELCHECK` tinyint(2) DEFAULT '0',
  `BOARD_CREATED_DATE` datetime DEFAULT NULL,
  `BOARD_MODIFIED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`BOARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `BOARD` WRITE;
/*!40000 ALTER TABLE `BOARD` DISABLE KEYS */;

INSERT INTO `BOARD` (`BOARD_ID`, `BOARD_TYPE`, `BOARD_SUBJECT`, `BOARD_CONTENT`, `BOARD_HITS`, `BOARD_LIKES`, `BOARD_REPLY_DEPTH`, `BOARD_COMMENT_DEPTH`, `BOARD_CREATED_BY`, `BOARD_CREATION_DATE`, `BOARD_MODIFIED_BY`, `BOARD_MODIFICATION_DATE`, `BOARD_DELCHECK`, `BOARD_CREATED_DATE`, `BOARD_MODIFIED_DATE`)
VALUES
	(1,'freeboard','ㅇㄴㅁㅇㄴㅁ','			<div class=\"col-sm-1\" align=\"center\">\n								<a th:href=\"@{|/talk/${tableName}/edit/{id}|(id=${i.id})}\">\n									<span class=\"glyphicon glyphicon-edit\"></span>\n								</a>\n								<a th:href=\"@{|/talk/${tableName}/delete/{id}|(id=${i.id})}\">\n									<span class=\"glyphicon glyphicon-trash\"></span>\n								</a>\n							</div>',1,0,0,0,'ㅇㄴㅁㅇㄴㅁ','2017-01-22 19:03:57',NULL,'2017-01-22 19:45:10',0,NULL,NULL),
	(2,'freeboard','dsa','<p>dsadasdsadsa</p>',1,0,0,0,'dsadsa','2017-01-22 19:31:46',NULL,'2017-01-22 19:45:07',0,NULL,NULL),
	(3,'freeboard','dsa','<p>dsadasdsadsa</p>',1,0,0,0,'dsadsa','2017-01-22 19:33:22',NULL,'2017-01-22 19:44:56',0,NULL,NULL),
	(4,'freeboard','ㅇㄴㅁㅇㄴㅁ','<p>dsadsa<span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span></p>\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n',1,0,0,0,'ㅇㄴㅁ','2017-01-22 19:35:11',NULL,'2017-01-22 19:42:32',0,NULL,NULL),
	(5,'notice','ㅇㄴㅁㅇㄴㅁ','<p>dsadsa<span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span><span class=\"s1\" style=\"font-family: Monaco; font-size: 11px;\">name</span><span class=\"s2\" style=\"font-family: Monaco; font-size: 11px;\">=</span><span style=\"color: rgb(57, 51, 255); font-family: Monaco; font-size: 11px;\">\"createdBy\"</span></p>\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<style type=\"text/css\">\r\np.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 11.0px Monaco; color: #3933ff}\r\nspan.s1 {color: #932192}\r\nspan.s2 {color: #000000}\r\n</style>\r\n\r\n\r\n',1,0,0,0,'ㅇㄴㅁ','2017-01-22 19:35:11',NULL,'2017-01-22 20:00:41',0,NULL,NULL);

/*!40000 ALTER TABLE `BOARD` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table COMMENT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `COMMENT`;

CREATE TABLE `COMMENT` (
  `COMMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_BOARD_ID` bigint(20) DEFAULT NULL,
  `COMMENT_COMMENT_DEPTH` int(11) NOT NULL,
  `COMMENT_CONTENT` varchar(300) NOT NULL,
  `COMMENT_CREATED_BY` varchar(30) NOT NULL,
  `COMMENT_CREATION_DATE` datetime NOT NULL,
  `COMMENT_DELCHECK` int(11) NOT NULL,
  `COMMENT_LIKES` int(11) DEFAULT NULL,
  `COMMENT_MODIFICATION_DATE` datetime DEFAULT NULL,
  `COMMENT_MODIFIED_BY` varchar(30) DEFAULT NULL,
  `COMMENT_TYPE` varchar(10) NOT NULL,
  `COMMENT_CREATED_DATE` datetime NOT NULL,
  `COMMENT_MODIFIED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  KEY `COMMENT_BOARD_FK` (`COMMENT_BOARD_ID`),
  CONSTRAINT `COMMENT_BOARD_FK` FOREIGN KEY (`COMMENT_BOARD_ID`) REFERENCES `BOARD` (`BOARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table FILE_DATA
# ------------------------------------------------------------

DROP TABLE IF EXISTS `FILE_DATA`;

CREATE TABLE `FILE_DATA` (
  `FILE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FILE_TYPE` varchar(20) NOT NULL,
  `FILE_CREATED_BY` varchar(30) NOT NULL,
  `FILE_CREATION_DATE` datetime NOT NULL,
  `FILE_DELCHECK` int(11) NOT NULL,
  `FILE_MODIFICATION_DATE` datetime DEFAULT NULL,
  `FILE_MODIFIED_BY` varchar(30) DEFAULT NULL,
  `FILE_ORIGIN_NAME` varchar(100) NOT NULL,
  `FILE_SAVED_DIR` varchar(200) NOT NULL,
  `FILE_SAVED_NAME` varchar(200) NOT NULL,
  `FILE_BOARD_ID` bigint(20) NOT NULL,
  `FILE_CREATED_DATE` datetime NOT NULL,
  `FILE_MODIFIED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`),
  KEY `FILE_BOARD_FK` (`FILE_BOARD_ID`),
  CONSTRAINT `FILE_BOARD_FK` FOREIGN KEY (`FILE_BOARD_ID`) REFERENCES `BOARD` (`BOARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table MUSIC
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MUSIC`;

CREATE TABLE `MUSIC` (
  `MUSIC_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MUSIC_MODIFIED_DATE` datetime NOT NULL,
  `MUSIC_CREATED_DATE` datetime NOT NULL,
  `MUSIC_IMAGE` varchar(255) NOT NULL,
  `MUSIC_LYRICS` longtext,
  `MUSIC_SINGER` varchar(50) NOT NULL,
  `MUSIC_TITLE` varchar(100) NOT NULL,
  `MUSIC_URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MUSIC_ID`),
  UNIQUE KEY `UK_s6h1rlj4c9eclspwtp09wul06` (`MUSIC_TITLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table MUSIC_LIST
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MUSIC_LIST`;

CREATE TABLE `MUSIC_LIST` (
  `MUSIC_LIST_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MUSIC_LIST_JSON_DATA` longtext,
  `MUSIC_LIST_MODIFIED_DATE` datetime NOT NULL,
  `MUSIC_LIST_CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`MUSIC_LIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table PATIENT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PATIENT`;

CREATE TABLE `PATIENT` (
  `PATIENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PATIENT_BIRTH` datetime DEFAULT NULL,
  `PATIENT_BLOOD` varchar(5) DEFAULT NULL,
  `PATIENT_CHART_ID` varchar(100) NOT NULL,
  `PATIENT_CREATION_DATE` datetime DEFAULT NULL,
  `PATIENT_DELCHECK` varchar(5) DEFAULT NULL,
  `PATIENT_GENDER` varchar(5) DEFAULT NULL,
  `PATIENT_HAND` varchar(5) DEFAULT NULL,
  `PATIENT_HEIGHT` varchar(5) DEFAULT NULL,
  `PATIENT_MODIFICATION_DATE` datetime DEFAULT NULL,
  `PATIENT_NAME` varchar(30) NOT NULL,
  `PATIENT_PHONE` varchar(20) DEFAULT NULL,
  `PATIENT_PMID_KEY` varchar(100) DEFAULT NULL,
  `PATIENT_WEIGHT` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`PATIENT_ID`),
  UNIQUE KEY `UK_emxkkg20yptl0pa099rdyq8t8` (`PATIENT_CHART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table PERSISTENT_LOGINS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PERSISTENT_LOGINS`;

CREATE TABLE `PERSISTENT_LOGINS` (
  `PERSISTENT_LOGINS_SERIES` varchar(50) NOT NULL,
  `PERSISTENT_LOGINS_EMAIL` varchar(60) NOT NULL,
  `PERSISTENT_LOGINS_LATESTDATE` datetime NOT NULL,
  `PERSISTENT_LOGINS_TOKEN` varchar(255) NOT NULL,
  PRIMARY KEY (`PERSISTENT_LOGINS_SERIES`),
  UNIQUE KEY `UK_d9w5vnsbqewj6a2c5fxaxw02m` (`PERSISTENT_LOGINS_EMAIL`),
  UNIQUE KEY `UK_baxp761yrva187ah9e8tlkvqj` (`PERSISTENT_LOGINS_TOKEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `PERSISTENT_LOGINS` WRITE;
/*!40000 ALTER TABLE `PERSISTENT_LOGINS` DISABLE KEYS */;

INSERT INTO `PERSISTENT_LOGINS` (`PERSISTENT_LOGINS_SERIES`, `PERSISTENT_LOGINS_EMAIL`, `PERSISTENT_LOGINS_LATESTDATE`, `PERSISTENT_LOGINS_TOKEN`)
VALUES
	('dtIhkYlSJuYECsQ41caRYg==','shun10114@gmail.com','2017-01-24 22:32:30','SrjYFyNI+0q2Z4Tb730ZTw==');

/*!40000 ALTER TABLE `PERSISTENT_LOGINS` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table PRICE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PRICE`;

CREATE TABLE `PRICE` (
  `PRICE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRICE_CREATED_BY` varchar(60) NOT NULL,
  `PRICE_CREATION_DATE` datetime NOT NULL,
  `PRICE_DELCHECK` int(11) DEFAULT NULL,
  `PRICE_DISCOUNT_RATE` int(11) DEFAULT NULL,
  `PRICE_EVENT_END_DATE` datetime NOT NULL,
  `PRICE_EVENT_START_DATE` datetime NOT NULL,
  `PRICE_MODIFICATION_DATE` datetime DEFAULT NULL,
  `PRICE_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `PRICE_NAME` varchar(50) NOT NULL,
  `PRICE_VALUE` int(11) NOT NULL,
  `PRICE_CREATED_DATE` datetime NOT NULL,
  `PRICE_MODIFIED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PRICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table PRICE_RECORD
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PRICE_RECORD`;

CREATE TABLE `PRICE_RECORD` (
  `PRICE_RECORD_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRICE_RECORD_ALLOW_STATE` varchar(10) NOT NULL,
  `PRICE_RECORD_CREATION_DATE` datetime NOT NULL,
  `PRICE_RECORD_DELCHECK` int(11) DEFAULT NULL,
  `PRICE_RECORD_DISCOUNT_RATE` varchar(20) DEFAULT NULL,
  `PRICE_RECORD_DISCOUNTED_VALUE` int(11) DEFAULT NULL,
  `PRICE_RECORD_END_DATE` datetime NOT NULL,
  `PRICE_RECORD_MODIFICATION_DATE` datetime DEFAULT NULL,
  `PRICE_RECORD_NAME` varchar(50) NOT NULL,
  `PRICE_RECORD_ORIGINAL_VALUE` int(11) NOT NULL,
  `PRICE_RECORD_PAID_BY_USER` bigint(20) NOT NULL,
  `PRICE_VALUE_FROM_PRICE` bigint(20) NOT NULL,
  `PRICE_RECORD_CREATED_DATE` datetime NOT NULL,
  `PRICE_RECORD_MODIFIED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PRICE_RECORD_ID`),
  KEY `PRICE_USER_FK` (`PRICE_RECORD_PAID_BY_USER`),
  KEY `PRICE_RECOURD_FK` (`PRICE_VALUE_FROM_PRICE`),
  CONSTRAINT `PRICE_RECOURD_FK` FOREIGN KEY (`PRICE_VALUE_FROM_PRICE`) REFERENCES `PRICE` (`PRICE_ID`),
  CONSTRAINT `PRICE_USER_FK` FOREIGN KEY (`PRICE_RECORD_PAID_BY_USER`) REFERENCES `USER` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table REPLY
# ------------------------------------------------------------

DROP TABLE IF EXISTS `REPLY`;

CREATE TABLE `REPLY` (
  `REPLY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REPLY_COMMENT_DEPTH` int(11) DEFAULT NULL,
  `REPLY_CONTENT` longtext NOT NULL,
  `REPLY_CREATED_BY` varchar(60) NOT NULL,
  `REPLY_CREATION_DATE` datetime NOT NULL,
  `REPLY_DELCHECK` int(11) DEFAULT NULL,
  `REPLY_HITS` int(11) DEFAULT NULL,
  `REPLY_LIKES` int(11) DEFAULT NULL,
  `REPLY_MODIFICATION_DATE` datetime DEFAULT NULL,
  `REPLY_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `REPLY_SUBJECT` varchar(200) NOT NULL,
  `REPLY_TYPE` varchar(10) NOT NULL,
  `REPLY_BOARD_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`REPLY_ID`),
  KEY `REPLY_BOARD_FK` (`REPLY_BOARD_ID`),
  CONSTRAINT `REPLY_BOARD_FK` FOREIGN KEY (`REPLY_BOARD_ID`) REFERENCES `BOARD` (`BOARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table SURVEY
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SURVEY`;

CREATE TABLE `SURVEY` (
  `SURVEY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `HOW_TO_SURVEY_CONTENT` longtext,
  `HOW_TO_SURVEY_ETC` varchar(255) DEFAULT NULL,
  `HOW_TO_SURVEY_SUBJECT` varchar(255) DEFAULT NULL,
  `MASTER_OF_SURVEY` varchar(255) DEFAULT NULL,
  `SURVEY_FIRST_QUESTION_JSON` longtext,
  `SURVEY_NAME` varchar(60) DEFAULT NULL,
  `SURVEY_TYPE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SURVEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table SURVEY_BINDING
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SURVEY_BINDING`;

CREATE TABLE `SURVEY_BINDING` (
  `SURVEY_BINDING_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PATIENT_ACCESS_PRIVILEGE_KEY` varchar(100) NOT NULL,
  `SURVEY_BINDING_IN_PATIENT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SURVEY_BINDING_ID`),
  KEY `SURVEY_RESULT_FK` (`SURVEY_BINDING_IN_PATIENT_ID`),
  CONSTRAINT `SURVEY_RESULT_FK` FOREIGN KEY (`SURVEY_BINDING_IN_PATIENT_ID`) REFERENCES `PATIENT` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table SURVEY_BINDING_REFER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SURVEY_BINDING_REFER`;

CREATE TABLE `SURVEY_BINDING_REFER` (
  `SURVEY_BINDING_ID` bigint(20) NOT NULL,
  `SURVEY_ID` int(11) NOT NULL,
  PRIMARY KEY (`SURVEY_BINDING_ID`,`SURVEY_ID`),
  KEY `SURVEY_REFER_FK` (`SURVEY_ID`),
  CONSTRAINT `SURVEY_BINDING_FK` FOREIGN KEY (`SURVEY_BINDING_ID`) REFERENCES `SURVEY_BINDING` (`SURVEY_BINDING_ID`),
  CONSTRAINT `SURVEY_REFER_FK` FOREIGN KEY (`SURVEY_ID`) REFERENCES `SURVEY` (`SURVEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table SURVEY_RESULT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SURVEY_RESULT`;

CREATE TABLE `SURVEY_RESULT` (
  `SURVEY_RESULT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SURVEY_ID` int(11) DEFAULT NULL,
  `SURVEY_NAME` varchar(60) DEFAULT NULL,
  `SURVEY_RESULT_SELECT_JSON` longtext,
  `SURVEY_STATE` varchar(10) DEFAULT NULL,
  `SURVEY_TYPE` varchar(30) DEFAULT NULL,
  `SURVEY_BINDING_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`SURVEY_RESULT_ID`),
  KEY `SURVEY_RESULT_BINDING_FK` (`SURVEY_BINDING_ID`),
  CONSTRAINT `SURVEY_RESULT_BINDING_FK` FOREIGN KEY (`SURVEY_BINDING_ID`) REFERENCES `SURVEY_BINDING` (`SURVEY_BINDING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table USER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_EMAIL` varchar(60) NOT NULL,
  `USER_NAME` varchar(30) NOT NULL,
  `USER_PASSWORD` varchar(100) NOT NULL,
  `USER_RECEIVE_MAIL` tinyint(2) DEFAULT '0',
  `USER_STATE` varchar(20) NOT NULL,
  `USER_CREATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_MODIFICATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `USER_ACCOUNT_NON_LOCKED` tinyint(2) DEFAULT '0',
  `USER_ACCOUNT_NON_EXPIRED` tinyint(2) DEFAULT '0',
  `USER_CREDENTIALS_NON_EXPIRED` tinyint(2) DEFAULT '0',
  `USER_DELCHECK` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_mp4s4dwn90qh0vqq4rejdp0q7` (`USER_EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;

INSERT INTO `USER` (`USER_ID`, `USER_EMAIL`, `USER_NAME`, `USER_PASSWORD`, `USER_RECEIVE_MAIL`, `USER_STATE`, `USER_CREATION_DATE`, `USER_MODIFICATION_DATE`, `USER_ACCOUNT_NON_LOCKED`, `USER_ACCOUNT_NON_EXPIRED`, `USER_CREDENTIALS_NON_EXPIRED`, `USER_DELCHECK`)
VALUES
	(1,'shun10114@gmail.com','seolhun','$2a$10$/1xYj9gbzv/sXwzrTjRE5Oqfb5EZ6L.icl9kkXDYUwxJUGAFvOA1a',1,'active','2017-01-24 22:05:01','2017-01-24 22:05:01',0,0,0,NULL);

/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USER_ATTEMPTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER_ATTEMPTS`;

CREATE TABLE `USER_ATTEMPTS` (
  `USER_ATTEMPTS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ATTEMPTS` int(11) NOT NULL,
  `USER_EMAIL` varchar(255) NOT NULL,
  `USER_LATESTDATE` datetime NOT NULL,
  `USER_LOGIN_IP` varchar(255) DEFAULT NULL,
  `USER_SUCCESS_FLAG` int(11) DEFAULT NULL,
  `USER_VALIDATION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ATTEMPTS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table USER_PROFILE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER_PROFILE`;

CREATE TABLE `USER_PROFILE` (
  `USER_PROFILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_PROFILE_TYPE` varchar(15) NOT NULL,
  PRIMARY KEY (`USER_PROFILE_ID`),
  UNIQUE KEY `UK_664k95onhfsreofag94xc31oj` (`USER_PROFILE_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `USER_PROFILE` WRITE;
/*!40000 ALTER TABLE `USER_PROFILE` DISABLE KEYS */;

INSERT INTO `USER_PROFILE` (`USER_PROFILE_ID`, `USER_PROFILE_TYPE`)
VALUES
	(5,'ADMIN'),
	(3,'CAPTAIN'),
	(4,'DIRECTOR'),
	(1,'GUEST'),
	(2,'PLAYER'),
	(6,'SUPERADMIN');

/*!40000 ALTER TABLE `USER_PROFILE` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USER_PROFILE_REFER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER_PROFILE_REFER`;

CREATE TABLE `USER_PROFILE_REFER` (
  `USER_ID` bigint(20) NOT NULL,
  `USER_PROFILE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`USER_PROFILE_ID`),
  KEY `USER_PROFILE_REFER_FK` (`USER_PROFILE_ID`),
  CONSTRAINT `USER_PROFILE_REFER_FK` FOREIGN KEY (`USER_PROFILE_ID`) REFERENCES `USER_PROFILE` (`USER_PROFILE_ID`),
  CONSTRAINT `USER_REFER_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `USER_PROFILE_REFER` WRITE;
/*!40000 ALTER TABLE `USER_PROFILE_REFER` DISABLE KEYS */;

INSERT INTO `USER_PROFILE_REFER` (`USER_ID`, `USER_PROFILE_ID`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `USER_PROFILE_REFER` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
