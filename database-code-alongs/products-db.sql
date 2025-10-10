use homework;

DROP TABLE IF EXISTS ProductCategory, Product;

-- Product categories
CREATE TABLE ProductCategory (
 CategoryId INT PRIMARY KEY,
 CategoryName VARCHAR(50)
);

-- Products table
CREATE TABLE Product (
 ProductId INT PRIMARY KEY,
 ProductName VARCHAR(100),
 CategoryId INT,
 Price DECIMAL(10, 2),
 FOREIGN KEY (CategoryId) REFERENCES ProductCategory(CategoryId)
);


-- Seed ProductCategory data
INSERT INTO ProductCategory VALUES
(1, 'Books'),
(2, 'Stationery'),
(3, 'Clearance');
-- Seed Product data
INSERT INTO Product VALUES
(101, 'Notebook', 2, 5.00),
(102, 'Pen Set', 2, 7.50),
(103, 'Calendar 2023', 2, 9.99),
(104, 'Mystery Novel', 1, 15.00),
(105, 'Classic Fiction', 1, 12.50),
(106, 'Sticker Pack', 3, 2.99);
