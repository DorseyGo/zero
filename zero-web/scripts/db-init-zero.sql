DROP database `zero`;
CREATE database `zero`;
USE zero;

--
-- table wechat_global_config
--
CREATE TABLE IF NOT EXISTS `wechat_global_config`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(32) NOT NULL COMMENT 'app ID',
  `app_secret` VARCHAR(120) NOT NULL COMMENT 'app secret',
  `encoding_aes_key` VARCHAR(43) NOT NULL COMMENT 'the encoding aes key for encryption/decryption',
  `token` VARCHAR(32) NOT NULL COMMENT 'token granted by application itself',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- initialize
--
INSERT INTO wechat_global_config(app_id, app_secret, encoding_aes_key, token) VALUE ('wx3b52ddf8dc6fb5d3', '98741018e331e1af9221bc0e1865665f',
'1xkR1XiI4D7peIPlXQgYppMGe9FZvAa3wLA5WBv8WEN', 'wx3b52ddf8dc6fb5d20200202');

--
-- table wechat_access_token
--
CREATE TABLE IF NOT EXISTS `wechat_access_token` (
  `uuid` VARCHAR(32) NOT NULL,
  `access_token` VARCHAR(255) NOT NULL COMMENT 'access token for accessing wechat',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'the modified time',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;