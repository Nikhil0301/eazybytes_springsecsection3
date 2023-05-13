create table users(
    `id` INT NOT NULL AUTO_INCREMENT,
	`username` varchar(45) not null,
	`password` varchar(45) not null,
	`enabled` boolean not null,
    primary key (`id`)
);

create table authorities (
    `id` INT NOT NULL AUTO_INCREMENT,
	`username` varchar(45) not null,
	`authority` varchar(45) not null,
    primary key (`id`)
);
