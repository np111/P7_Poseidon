CREATE TABLE `users` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(125),
  `password` VARCHAR(125),
  `fullname` VARCHAR(125),
  `role` VARCHAR(125),

  PRIMARY KEY (`id`)
)
