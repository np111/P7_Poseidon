CREATE TABLE `rating` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `moodysRating` VARCHAR(125),
  `sandPRating` VARCHAR(125),
  `fitchRating` VARCHAR(125),
  `orderNumber` TINYINT,

  PRIMARY KEY (`id`)
)
