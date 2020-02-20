DROP database `zero`;
CREATE database `zero`;
USE zero;

--
-- table wechat_global_config
--
CREATE TABLE IF NOT EXISTS `wechat_global_config`(
  `wechat_account` VARCHAR(32) NOT NULL,
  `app_id` VARCHAR(32) NOT NULL COMMENT 'app ID',
  `app_secret` VARCHAR(120) NOT NULL COMMENT 'app secret',
  `encoding_aes_key` VARCHAR(43) NOT NULL COMMENT 'the encoding aes key for encryption/decryption',
  `token` VARCHAR(32) NOT NULL COMMENT 'token granted by application itself',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`wechat_account`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- initialize
--
INSERT INTO wechat_global_config(wechat_account, app_id, app_secret, encoding_aes_key, token) VALUE ('nqfbrtzv@163.com','wx3b52ddf8dc6fb5d3', '98741018e331e1af9221bc0e1865665f',
'1xkR1XiI4D7peIPlXQgYppMGe9FZvAa3wLA5WBv8WEN', 'wx3b52ddf8dc6fb5d20200202');

--
-- table wechat_access_token
--
CREATE TABLE IF NOT EXISTS `wechat_access_token` (
  `wechat_account` VARCHAR(32) NOT NULL,
  `access_token` VARCHAR(255) NOT NULL COMMENT 'access token for accessing wechat',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'the modified time',
  PRIMARY KEY (`wechat_account`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- table sys_user
--
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(24) NOT NULL UNIQUE COMMENT 'the username for login',
  `password` VARCHAR(64) NOT NULL COMMENT 'the password',
  `enabled` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '0 for enabled, 1 for disabled',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO `sys_user`(username, password) VALUES ('admin', '$2a$10$YanaH9hjwmllxXz8/dla8.FhEez/q6TMvkWxsT1XyxC/.ZrREFbfm');

--
-- table sys_role
--
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` INT(2) NOT NULL AUTO_INCREMENT COMMENT 'the role id',
  `role` VARCHAR(10) NOT NULL DEFAULT 'ROLE_USER' COMMENT 'the role, two roles predefined: ROLE_ADMIN, ROLE_USER',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO `sys_role`(role) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

--
-- table user_role
--
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` BIGINT NOT NULL COMMENT 'the user id',
  `role_id` INT(2) NOT NULL COMMENT 'the role id',
  PRIMARY KEY (`user_id`, `role_id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- fans
--
CREATE TABLE IF NOT EXISTS `fans` (
  `open_id` VARCHAR(32) NOT NULL,
  `subscribe` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '1, for subscribed; 0 for unsubscribed',
  `nick_name` VARCHAR(32) DEFAULT NULL COMMENT 'nick name',
  `gender` TINYINT(1) DEFAULT '0' COMMENT '0 for unknown, 1 for male, 2 for female',
  `city` VARCHAR(24) DEFAULT NULL,
  `province` VARCHAR(24) DEFAULT NULL ,
  `country` VARCHAR(24) DEFAULT NULL ,
  `language` VARCHAR(12) DEFAULT 'zh_CN',
  `heading_img_url` VARCHAR(255) DEFAULT NULL,
  `subscribe_time` DATETIME DEFAULT NULL COMMENT 'last subscribe time',
  `union_id` VARCHAR(32) DEFAULT NULL,
  `remark` VARCHAR(32) DEFAULT NULL,
  `subscribe_scene` VARCHAR(32) DEFAULT 'ADD_SCENE_OTHERS',
  `qr_scene` BIGINT DEFAULT NULL ,
  `qr_scene_desc` VARCHAR(32) DEFAULT NULL COMMENT 'customized',
  PRIMARY KEY (`open_id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- group
--
CREATE TABLE IF NOT EXISTS `group` (
  `id` BIGINT NOT NULL COMMENT 'group ID',
  `name` VARCHAR(24) NOT NULL COMMENT 'group name',
  `num_fans` INT(4) DEFAULT '0' COMMENT 'The fans under this group',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- fans_group
--
CREATE TABLE IF NOT EXISTS `fans_group`(
  `group_id` BIGINT NOT NULL COMMENT 'group ID',
  `open_id` VARCHAR(32) NOT NULL COMMENT 'fans open id',
  PRIMARY KEY (`group_id`, `open_id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;