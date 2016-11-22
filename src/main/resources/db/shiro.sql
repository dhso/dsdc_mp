--
-- Table structure for table `shiro_permissions`
--

DROP TABLE IF EXISTS `shiro_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `permission_desc` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_permissions`
--

LOCK TABLES `shiro_permissions` WRITE;
/*!40000 ALTER TABLE `shiro_permissions` DISABLE KEYS */;
INSERT INTO `shiro_permissions` VALUES (1,'premission','权限',1),(2,'monitor','监控',1),(3,'wechat','微信',1);
/*!40000 ALTER TABLE `shiro_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_roles`
--

DROP TABLE IF EXISTS `shiro_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_roles`
--

LOCK TABLES `shiro_roles` WRITE;
/*!40000 ALTER TABLE `shiro_roles` DISABLE KEYS */;
INSERT INTO `shiro_roles` VALUES (1,'admin','超级管理员',1),(2,'user','管理员',1),(3,'wechat','微信管理员',1);
/*!40000 ALTER TABLE `shiro_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_roles_permissions`
--

DROP TABLE IF EXISTS `shiro_roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_roles_permissions`
--

LOCK TABLES `shiro_roles_permissions` WRITE;
/*!40000 ALTER TABLE `shiro_roles_permissions` DISABLE KEYS */;
INSERT INTO `shiro_roles_permissions` VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(3,3);
/*!40000 ALTER TABLE `shiro_roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_urls`
--

DROP TABLE IF EXISTS `shiro_urls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_urls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `url_type_id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `text` varchar(50) NOT NULL,
  `icon` varchar(50) NOT NULL,
  `url_order` int(11) NOT NULL,
  `is_iframe` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_urls`
--

LOCK TABLES `shiro_urls` WRITE;
/*!40000 ALTER TABLE `shiro_urls` DISABLE KEYS */;
INSERT INTO `shiro_urls` VALUES (1,2,1,'/druid/','SQL监控','fa fa-database',1,1),(2,1,2,'/wx/config/role','角色管理','fa fa-user-secret',4,0),(3,1,2,'/wx/config/permission','权限管理','fa fa-ban',3,0),(12,1,2,'/wx/config/url','链接管理','fa fa-link',2,0),(13,1,2,'/wx/config/urltype','链接类型管理','fa fa-link',1,0),(14,3,6,'/wx/config/qacontent','问答管理','fa fa-link',6,0),(15,3,6,'/wx/config/qatype','问答类型','fa fa-link',7,0);
/*!40000 ALTER TABLE `shiro_urls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_urls_type`
--

DROP TABLE IF EXISTS `shiro_urls_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_urls_type` (
  `url_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `url_type_name` varchar(50) NOT NULL,
  `url_type_icon` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`url_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_urls_type`
--

LOCK TABLES `shiro_urls_type` WRITE;
/*!40000 ALTER TABLE `shiro_urls_type` DISABLE KEYS */;
INSERT INTO `shiro_urls_type` VALUES (1,'监控','fa fa-support'),(2,'权限','fa fa-shield'),(6,'微信','fa fa-wechat');
/*!40000 ALTER TABLE `shiro_urls_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_users`
--

DROP TABLE IF EXISTS `shiro_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_users`
--

LOCK TABLES `shiro_users` WRITE;
/*!40000 ALTER TABLE `shiro_users` DISABLE KEYS */;
INSERT INTO `shiro_users` VALUES (1,'admin','8e7a4a6bad4f685bd9da4f78b5f76f9f','shiro',0),(2,'user','b5435b43cca8783d13515ecaa3c28a6c','shiro',0);
/*!40000 ALTER TABLE `shiro_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_users_roles`
--

DROP TABLE IF EXISTS `shiro_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_users_roles`
--

LOCK TABLES `shiro_users_roles` WRITE;
/*!40000 ALTER TABLE `shiro_users_roles` DISABLE KEYS */;
INSERT INTO `shiro_users_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `shiro_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
