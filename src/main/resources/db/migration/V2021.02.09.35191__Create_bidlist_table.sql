CREATE TABLE `bidlist` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(30) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `bidQuantity` DOUBLE,
  `askQuantity` DOUBLE,
  `bid` DOUBLE,
  `ask` DOUBLE,
  `benchmark` VARCHAR(125),
  `bidListDate` TIMESTAMP,
  `commentary` VARCHAR(125),
  `security` VARCHAR(125),
  `status` VARCHAR(10),
  `trader` VARCHAR(125),
  `book` VARCHAR(125),
  `creationName` VARCHAR(125),
  `creationDate` TIMESTAMP,
  `revisionName` VARCHAR(125),
  `revisionDate` TIMESTAMP,
  `dealName` VARCHAR(125),
  `dealType` VARCHAR(125),
  `sourceListId` VARCHAR(125),
  `side` VARCHAR(125),

  PRIMARY KEY (`id`)
)
