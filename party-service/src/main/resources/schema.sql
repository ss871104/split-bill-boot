DROP TABLE IF EXISTS party;

CREATE TABLE party (
	`party_id` bigint not null AUTO_INCREMENT primary key,
	`party_name` varchar(255),
	`member_quantity` bigint ,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP
);