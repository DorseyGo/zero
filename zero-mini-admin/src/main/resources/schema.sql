CREATE DATABASE IF NOT EXISTS `zero`;
USE `zero`;

-- ---
-- tabs
-- ----
DROP TABLE IF EXISTS `tabs`;
CREATE TABLE `tabs` (
  `id` TINYINT(1) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(24) NOT NULL COMMENT 'the name',
  `leading_img_path` VARCHAR(255) DEFAULT NULL COMMENT 'the image path, which will display before the tab name',
  `activate_when_init` TINYINT(1) DEFAULT '1' COMMENT 'whether add active when initialize, 1 for not, 0 for yes',
  `component` VARCHAR(24) NOT NULL COMMENT 'the component in wx, align with the frontend',
  `sequence` TINYINT(1) NOT NULL DEFAULT '0' COMMENT 'the sequence of tab displayed',
  `creator` VARCHAR(24) NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- --
-- course category
-- --
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category`(
  `id` TINYINT(1) NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(32) NOT NULL COMMENT 'the category name',
  `activate_when_init` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '1 for inactive, 0 for active',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----
-- notification
-- -----
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `slide` VARCHAR(64) NOT NULL COMMENT 'the path for slide, which represents the notification',
  `enable` TINYINT(1) NOT NULL DEFAULT '0' COMMENT 'whether this is enabled, 0 for enable, 1 for disable',
  `creator` VARCHAR(24) NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- course
--
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` TINYINT(2) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(24) NOT NULL COMMENT 'name of course',
  `brief` VARCHAR(32) DEFAULT NULL COMMENT 'the brief introduction of course',
  `img_relative_path` VARCHAR(32) DEFAULT NULL,
  `category_id` TINYINT(1) NOT NULL COMMENT 'the category id',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;