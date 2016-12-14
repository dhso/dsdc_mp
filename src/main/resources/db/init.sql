--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `cfg_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_key` varchar(255) NOT NULL,
  `cfg_value` text NOT NULL,
  `cfg_type_id` int(11) NOT NULL,
  PRIMARY KEY (`cfg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'wx.tip.hello','Hello, I\'m Abby. Tell you a secret. I know everything about the data in Acxiom. Do you want to know these?',1),(2,'wx.token','adad',1),(3,'wx.tip.subscribe','Welcome to subscribe Acxiom wechat!',1),(4,'qa.hit','3',2),(5,'qa.tip.notfound','Sorry, can\'t find the answer.',2),(6,'wx.tip.query','这是您的数据需求：\\n\\n ',1),(7,'wx.tip.queryafter','我现在去查询，有结果马上联系您，稍安勿躁',1),(8,'wx.cst.step.last.number','4',1),(9,'wx.cst.step.0','亲，我是安客诚数据达人，我能帮你找到你要的影响受众数据。请问你需要什么行业的数据？',1),(11,'wx.cst.step.1','谢谢， 你需要的数据是用于什么营销活动 ？',1),(12,'wx.cst.step.2','好的， 你需要大约多少受众？',1),(13,'wx.cst.step.3','嗯，什么时候要用呢？',1),(14,'wx.cst.step.4','我了解了， 我马上去找， 请留下您的联系电话或邮件',1),(15,'wx.cst.serviceaccount.openid','oNSykwnTA3JBynQ1flF1ru6yLRKg',1),(16,'wx.cst.step.recordname.0','行业:',1),(17,'wx.cst.step.recordname.1','目的：',1),(18,'wx.cst.step.recordname.2','数据量：',1),(19,'wx.cst.step.recordname.3','需要时间：',1),(20,'wx.cst.step.recordname.4','联系方式：',1);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config_type`
--

DROP TABLE IF EXISTS `sys_config_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config_type` (
  `cfg_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`cfg_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config_type`
--

LOCK TABLES `sys_config_type` WRITE;
/*!40000 ALTER TABLE `sys_config_type` DISABLE KEYS */;
INSERT INTO `sys_config_type` VALUES (1,'微信配置'),(2,'问答配置');
/*!40000 ALTER TABLE `sys_config_type` ENABLE KEYS */;
UNLOCK TABLES;