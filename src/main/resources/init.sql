CREATE SCHEMA IF NOT EXISTS academy_jdbc_homework;

CREATE TABLE `academy_jdbc_homework`.`first_homework_with_jdbc`
(
 `id` INT NOT NULL AUTO_INCREMENT ,
 `recipient` VARCHAR(50) NOT NULL ,
 `subject` VARCHAR(50) NOT NULL ,
 `body` TEXT NOT NULL ,
  `date` DATE NOT NULL ,
PRIMARY KEY (`id`)
) ENGINE = InnoDB;

