CREATE TABLE IF NOT EXISTS `task` (
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME NULL,
	`deleted_at` DATETIME NULL,
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`task_definition_id` varchar(36) NOT NULL,
	`status` varchar(12) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_task_definition_id` FOREIGN KEY (`task_definition_id`) REFERENCES `task_definition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;