CREATE TABLE IF NOT EXISTS `task_definition` (
	`id` varchar(36) NOT NULL,
	`name` varchar(64) NOT NULL,
	`type` varchar(255) NOT NULL,
	`description` varchar(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;