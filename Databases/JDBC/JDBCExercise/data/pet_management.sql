DROP DATABASE IF EXISTS pet_management;
CREATE DATABASE pet_management;
USE pet_management;

CREATE TABLE pet (
 pet_id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
 type VARCHAR(50) NOT NULL
);
INSERT INTO pet (name, type) VALUES
('Buddy', 'Dog'),
('Whiskers', 'Cat'),
('Goldie', 'Fish');