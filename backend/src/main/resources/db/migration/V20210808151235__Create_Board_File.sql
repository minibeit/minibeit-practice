DROP TABLE IF EXISTS `board_file`;
CREATE TABLE `board_file`
(
    `id`         BIGINT   NOT NULL AUTO_INCREMENT,
    `board_id`   BIGINT   NOT NULL,
    `file_id`    BIGINT   NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    `created_by` BIGINT   DEFAULT NULL,
    `updated_by` BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);