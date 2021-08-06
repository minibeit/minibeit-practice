DROP TABLE IF EXISTS `board_category`;
CREATE TABLE `board_category`
(
    `id`   BIGINT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(100),
    PRIMARY KEY (`id`)
);