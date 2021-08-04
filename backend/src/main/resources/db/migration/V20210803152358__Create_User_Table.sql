DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                       BIGINT      NOT NULL AUTO_INCREMENT,
    `oauth_id`                 VARCHAR(100),
    `email`                    VARCHAR(100),
    `name`                     VARCHAR(100),
    `password`                 VARCHAR(150),
    `provider`                 VARCHAR(50) NOT NULL,
    `role`                     VARCHAR(50) NOT NULL,
    `created_at`               DATETIME    NOT NULL,
    `updated_at`               DATETIME DEFAULT NULL,
    `deleted_at`               DATETIME DEFAULT NULL,
    `created_by`               BIGINT   DEFAULT NULL,
    `updated_by`               BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE INDEX `email_index` ON `user` (`email`);