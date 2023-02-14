DROP TABLE IF EXISTS notification;

create table notification(
	notification_id bigint not null auto_increment primary key,
    user_id bigint not null,
    `notification_type` int(1),
	`status` int(1),
	create_time datetime
);