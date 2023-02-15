DROP TABLE IF EXISTS users;

create table users(
    user_id bigint not null auto_increment primary key,
    uname varchar(255),
    username varchar(255) not null unique,
    pwd varchar(255) not null,
    profile_pic longblob,
    register_time datetime
);