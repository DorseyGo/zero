CREATE DATABASE IF NOT EXISTS `zero`;
USE `zero`;

-- ---
-- tabs
-- ----
DROP TABLE IF EXISTS `tabs`;
CREATE TABLE `tabs` (
  `id` TINYINT(1) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(24) NOT NULL COMMENT 'the name',
  `leading_img_url` VARCHAR(255) DEFAULT NULL COMMENT 'the image url address, which will display before the tab name',
  `activate_when_init` TINYINT(1) DEFAULT '1' COMMENT 'whether add active when initialize, 1 for not, 0 for yes',
  `creator` VARCHAR(24) NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;