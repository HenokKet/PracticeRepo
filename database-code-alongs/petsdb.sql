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
 ('Slithpers','Snake'),
 ('Noodles','Dog');

