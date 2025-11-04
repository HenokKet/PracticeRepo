drop database if exists pets;

create database pets;

use pets;

create table pet (
 pet_id int primary key auto_increment,
 `name` varchar(50) not null,
 `type` varchar(50) null
);

insert into pet
 (`name`, `type`)
values
 ('Meep','Mouse'),
 ('Slithers','Snake'),
 ('Davey','Dog'),
 ('Chloe', 'Cat');

create table user (
	user_id int primary key auto_increment,
    user_name varchar(100) not null,
    user_email varchar(254) not null,
    user_role varchar(10) not null,
    password_aes VARBINARY(256) not null
);

-- Insert 5 sample users
INSERT INTO user (user_name, user_email, user_role, password_aes) VALUES
('test_user_1', 'user1@email.com', 'admin', AES_ENCRYPT('password123', 'carrot')),
('test_admin_1', 'admin@email.com', 'user', AES_ENCRYPT('securepass', 'carrot'));