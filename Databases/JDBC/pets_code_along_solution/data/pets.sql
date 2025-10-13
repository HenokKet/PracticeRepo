DROP database if exists pets;

create database pets;

use pets;

create table pet (
	pet_id int primary key auto_increment,
    `name` varchar(50) not null,
    `type` varchar(50) null
) AUTO_INCREMENT = 101;

insert into pet
(`name`, `type`)
values
('Meep', 'Mouse'),
('Slithers', 'Snake'),
('Noodles', 'Dog');

SELECT * FROM PET;