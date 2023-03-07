drop database if exists user_service;

create database user_service;

use user_service;

drop table if exists users;

create table users(
    user_id bigint not null auto_increment primary key,
    uname varchar(255),
    username varchar(255) not null unique,
    pwd varchar(255) not null,
    profile_pic longblob,
    register_time datetime
);

-- ---------------------------------------------------------------

drop database if exists party_service;

create database party_service;

use party_service;

DROP TABLE IF EXISTS party;

CREATE TABLE party (
	`party_id` bigint not null AUTO_INCREMENT primary key,
	`party_name` varchar(255),
	`member_quantity` bigint ,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------

drop database if exists member_service;

create database member_service;

use member_service;

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

-- ---------------------------------------------------------------

drop database if exists bill_service;

create database bill_service;

use bill_service;

DROP TABLE IF EXISTS bill;
DROP TABLE IF EXISTS bill_detail;

CREATE TABLE bill (
	`bill_id` bigint not null AUTO_INCREMENT primary key,
	`party_id` bigint,
	`member_id` bigint,
	`bill_name` varchar(255),
	`bill_type` int,
	`total_amount` bigint,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bill_detail (
	`bill_detail_id` bigint not null AUTO_INCREMENT primary key,
	`bill_id` bigint,
	`member_id` bigint,
	`bill_detail_type` int,
	`amount` bigint
);

-- ---------------------------------------------------------------

drop database if exists notification_service;

create database notification_service;

use notification_service;

DROP TABLE IF EXISTS notification;

create table notification(
	notification_id bigint not null auto_increment primary key,
    user_id bigint not null,
    title varchar(255),
    content text,
	`status` int(1),
	create_time datetime,
	member_id bigint
);