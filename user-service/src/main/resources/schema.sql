DROP TABLE IF EXISTS users;

create table users(
    user_id bigint not null primary key,
    uname varchar(255),
    username varchar(255) not null unique,
    email varchar(255) not null unique,
    pwd varchar(255) not null,
    `role` int(1),
    profile_pic longblob,
    register_time datetime,
    email_verified tinyint(1)
);