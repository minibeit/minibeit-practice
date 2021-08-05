DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`
(
    `board_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `category_id` BIGINT,
    `title`       VARCHAR(100),
    `content`     VARCHAR(100),
    `place`       VARCHAR(150),
    `deadline`    VARCHAR(50) NOT NULL,
    `phone_num`   VARCHAR(50) NOT NULL,
    `pay`         INTEGER,
    `start_date`  DATETIME,
    `created_at`  DATETIME    NOT NULL,
    `updated_at`  DATETIME DEFAULT NULL,
    `deleted_at`  DATETIME DEFAULT NULL,
    `created_by`  BIGINT   DEFAULT NULL,
    `updated_by`  BIGINT   DEFAULT NULL,
    PRIMARY KEY (`board_id`)
);