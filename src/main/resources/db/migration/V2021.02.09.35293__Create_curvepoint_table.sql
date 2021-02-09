CREATE TABLE `curvepoint` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `curveId` TINYINT,
  `asOfDate` TIMESTAMP,
  `term` DOUBLE,
  `value` DOUBLE,
  `creationDate` TIMESTAMP,

  PRIMARY KEY (`id`)
)
