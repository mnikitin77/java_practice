CREATE TABLE IF NOT EXISTS `student` (
  `id` IDENTITY NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);