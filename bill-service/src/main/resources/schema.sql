DROP TABLE IF EXISTS bill;
DROP TABLE IF EXISTS bill_detail;

CREATE TABLE bill (
	`bill_id` bigint not null AUTO_INCREMENT primary key,
	`party_id` bigint,
	`member_id` bigint,
	`bill_name` varchar(255),
	`type` int,
	`total_amount` bigint,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bill_detail (
	`bill_detail_id` bigint not null AUTO_INCREMENT primary key,
	`bill_id` bigint,
	`member_id` bigint,
	`bill_detail_name` varchar(255),
	`bill_type` int,
	`amount` bigint
);