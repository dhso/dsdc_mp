--
-- Table structure for table `qa_content`
--

DROP TABLE IF EXISTS `qa_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qa_content` (
  `qac_id` int(11) NOT NULL AUTO_INCREMENT,
  `qac_question` text NOT NULL,
  `qac_answer` text NOT NULL,
  `qac_type` int(11) NOT NULL,
  PRIMARY KEY (`qac_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qa_content`
--

LOCK TABLES `qa_content` WRITE;
/*!40000 ALTER TABLE `qa_content` DISABLE KEYS */;
INSERT INTO `qa_content` VALUES (1,'安客诚有招聘计划吗？','有',1),
(2,'安客诚还招人吗？','当然招！只要你够优秀！',1),(3,'安客诚跟欧莱雅什么时候开始合作的？','很久很久之前',1),
(4,'安客诚员工数据？','暂时不能告诉你，这是隐私！',1),(5,'安客诚财务报表？','今年财务很好，不要担心！',1),
(6,'欧莱雅的双十一数据？','正在统计中，请耐心等待！',1),(7,'安客诚的有哪些客户数据？','有欧莱雅，美宝莲，旁氏。',1),
(9,'安客诚图片？','http://robot.minws.com/static/img/developing.jpg',2);
/*!40000 ALTER TABLE `qa_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qa_type`
--

DROP TABLE IF EXISTS `qa_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qa_type` (
  `qat_id` int(11) NOT NULL AUTO_INCREMENT,
  `qat_name` varchar(255) NOT NULL DEFAULT 'text',
  PRIMARY KEY (`qat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qa_type`
--

LOCK TABLES `qa_type` WRITE;
/*!40000 ALTER TABLE `qa_type` DISABLE KEYS */;
INSERT INTO `qa_type` VALUES (1,'text'),(2,'image');
/*!40000 ALTER TABLE `qa_type` ENABLE KEYS */;
UNLOCK TABLES;