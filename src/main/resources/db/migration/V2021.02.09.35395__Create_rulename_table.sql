CREATE TABLE `rulename` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(125) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,
  `description` VARCHAR(125) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,
  `json` VARCHAR(125) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,
  `template` VARCHAR(512) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,
  `sqlStr` VARCHAR(125) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,
  `sqlPart` VARCHAR(125) CHARACTER SET `utf8mb4` COLLATE `utf8mb4_unicode_ci` NOT NULL,

  PRIMARY KEY (`id`)
)
