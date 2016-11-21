CREATE TABLE `qas_words` (
  `wod_id` int(11) NOT NULL AUTO_INCREMENT,
  `wod_question` text NOT NULL,
  `wod_answer` text NOT NULL,
  `wod_type` varchar(255) DEFAULT 'text' COMMENT 'text 简单文本，class 调用类',
  PRIMARY KEY (`wod_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
