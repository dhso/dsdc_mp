CREATE TABLE `shiro_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `permission_desc` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shiro_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shiro_roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shiro_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shiro_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE `shiro_urls_type` (
  `url_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `url_type_name` varchar(50) NOT NULL,
  `url_type_icon` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`url_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `shiro_users` (`id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 'admin', '8e7a4a6bad4f685bd9da4f78b5f76f9f', 'shiro', 0),
	(2, 'user', 'b5435b43cca8783d13515ecaa3c28a6c', 'shiro', 0);
	
INSERT INTO `shiro_roles` (`id`, `role`, `role_desc`, `available`) VALUES
	(1, 'admin', 'admin', 1),
	(2, 'user', 'user', 1);

INSERT INTO `shiro_permissions` (`id`, `permission`, `permission_desc`, `available`) VALUES
	(1, 'cms:article:edit', NULL, 1),
	(2, 'cms:article:add', NULL, 1),
	(3, 'cms:article:delete', NULL, 1),
	(4, 'cms:setting:edit', NULL, 1);
	
INSERT INTO `shiro_roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 2);

INSERT INTO `shiro_users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2);