DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `category_id` BIGINT       NOT NULL,
    `title`       VARCHAR(100) NOT NULL,
    `content`     VARCHAR(100),
    `place`       VARCHAR(150) NOT NULL,
    `phone_num`   VARCHAR(50)  NOT NULL,
    `pay`         INTEGER      NOT NULL,
    `time`        INTEGER      NOT NULL,
    `due_date`    DATE         NOT NULL,
    `do_date`     DATETIME     NOT NULL,
    `created_at`  DATETIME     NOT NULL,
    `updated_at`  DATETIME DEFAULT NULL,
    `deleted_at`  DATETIME DEFAULT NULL,
    `created_by`  BIGINT   DEFAULT NULL,
    `updated_by`  BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);