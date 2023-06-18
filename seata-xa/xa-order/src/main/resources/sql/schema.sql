DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `order_no`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `commodity_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `count`          int                                     DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 64
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
