drop database if exists SmallProducts;

CREATE DATABASE SmallProducts;

USE SmallProducts;

CREATE TABLE ProductCategory (
 CategoryId INT PRIMARY KEY,
 CategoryName VARCHAR(50)
);
CREATE TABLE Product (
 ProductId INT PRIMARY KEY,
 ProductName VARCHAR(100),
 CategoryId INT,
 Price DECIMAL(10, 2),
 EndDate DATE,
 FOREIGN KEY (CategoryId) REFERENCES ProductCategory(CategoryId)
);
INSERT INTO ProductCategory VALUES
(1, 'Books'),
(2, 'Stationery'),
(3, 'Clearance');

INSERT INTO Product VALUES
(101, 'Notebook', 2, 5.00, NULL),
(102, 'Pen Set', 2, 7.50, NULL),
(103, 'Calendar 2023', 2, 9.99, NULL),
(104, 'Mystery Novel', 1, 15.00, NULL),
(105, 'Classic Fiction', 1, 12.50, NULL);

-- part 1
UPDATE Product
SET Price = 6.25
WHERE ProductId = 101; 

-- part 2
UPDATE Product
SET ProductName = 'Executive Pen Set',
	Price = 8.99
WHERE ProductId = 102; 

-- part 3
UPDATE Product p
JOIN ProductCategory pc ON p.CategoryId = pc.CategoryId
SET p.EndDate = '2024-12-31'
WHERE pc.CategoryId = 2 ;

-- part 4 
UPDATE Product
SET CategoryId = 999
WHERE ProductId = '103';
-- Cannot add or update a child row: a foreign key constraint fails 

UPDATE Product
SET CategoryId = 3
WHERE ProductId = '103';

-- part 5
UPDATE Product
SET ProductName = 'Vintage Novel',
	CategoryId = 3,
	Price = 10.00
WHERE ProductId = 105; 