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