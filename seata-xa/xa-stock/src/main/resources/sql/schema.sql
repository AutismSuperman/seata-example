DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `commodity_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `name`           varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `count`          int                                     DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
