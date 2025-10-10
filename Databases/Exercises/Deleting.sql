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
 FOREIGN KEY (CategoryId) REFERENCES ProductCategory(CategoryId)
);
INSERT INTO ProductCategory VALUES
(1, 'Books'),
(2, 'Stationery'),
(3, 'Clearance');

-- test for bonus question
INSERT INTO ProductCategory VALUES
(4, 'Toys');

INSERT INTO Product VALUES
(101, 'Notebook', 2, 5.00),
(102, 'Pen Set', 2, 7.50),
(103, 'Calendar 2023', 2, 9.99),
(104, 'Mystery Novel', 1, 15.00),
(105, 'Classic Fiction', 1, 12.50),
(106, 'Sticker Pack', 3, 2.99);

-- part 1 
SELECT ProductName
From Product
WHERE ProductName = 'Calendar 2023';

DELETE FROM product
WHERE productId = 103;

-- part 2 
SELECT ProductName
From Product
WHERE CategoryId = 2;

DELETE FROM product 
WHERE CategoryId = 2;

-- part 3 
DELETE FROM productcategory 
WHERE CategoryId = 1;
-- Cannot delete or update a parent row: a foreign key constraint fails 

DELETE FROM product 
WHERE CategoryId = 1;
DELETE FROM productcategory 
WHERE CategoryId = 1;

-- part 4 
Select * From ProductCategory;

-- used chatGPT for sql safe update
SET SQL_SAFE_UPDATES = 0;

DELETE FROM ProductCategory pc
WHERE NOT EXISTS (
  SELECT Null
  FROM Product p
  WHERE p.CategoryId = pc.CategoryId
);

-- used chatGPT for sql safe update
SET SQL_SAFE_UPDATES = 1;

