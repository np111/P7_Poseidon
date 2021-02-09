CREATE TABLE `rulename` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(125),
  `description` VARCHAR(125),
  `json` VARCHAR(125),
  `template` VARCHAR(512),
  `sqlStr` VARCHAR(125),
  `sqlPart` VARCHAR(125),

  PRIMARY KEY (`id`)
)
