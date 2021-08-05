DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `category_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `type` VARCHAR (100),
    PRIMARY KEY (`category_id`)
);