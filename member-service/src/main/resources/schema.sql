DROP TABLE IF EXISTS member;

CREATE TABLE member (
	`member_id` bigint not null AUTO_INCREMENT primary key,
	`party_id` bigint,
	`user_id` bigint,
	`balance` bigint,
	`member_nickname` varchar(255),
	`member_status` int DEFAULT 0,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP
);