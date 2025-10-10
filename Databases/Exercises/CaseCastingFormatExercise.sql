drop database if exists Shopsmart;

CREATE DATABASE Shopsmart;

USE Shopsmart;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
 customer_id INT PRIMARY KEY,
 First_Name VARCHAR(50),
 Last_Name VARCHAR(50)
);
INSERT INTO customers (customer_id, First_Name, Last_Name) VALUES
(101, 'john', 'DOE'),
(102, 'SARAH', 'smith'); 

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
 order_id INT PRIMARY KEY,
 customer_id INT,
 order_date DATETIME,
 order_total VARCHAR(20), -- intentionally using string for exercise
 FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
INSERT INTO orders (order_id, customer_id, order_date, order_total) VALUES
(1, 101, '2025-07-01 14:33:00', '1234.5'),
(2, 102, '2025-07-03 10:15:22', '8899.99');

-- part 1 
-- 1 
SELECT 
  customer_id,
  CONCAT(
    UPPER(LEFT(First_Name, 1)), LOWER(SUBSTRING(First_Name, 2)),
    ' ',
    UPPER(LEFT(Last_Name, 1)),  LOWER(SUBSTRING(Last_Name, 2))
  ) AS FullName_TitleCase
FROM customers;

-- 2 
SELECT * FROM customers WHERE First_Name = 'JOHN';

-- part 2
SELECT 
	order_total,
    CAST(order_total AS DECIMAL(10,2)) as formatted_total
FROM orders;

-- part 3 
-- 1
SELECT
	order_date,
    DATE_FORMAT(order_date, '%M %D %Y') as formatted_date
FROM 
	orders;

-- 2 
SELECT 
	order_total,
    FORMAT(CAST(order_total AS DECIMAL(10,2)), 2) as formatted_total
FROM orders;

-- part 4
SELECT
	CONCAT(
		UPPER(LEFT(first_name, 1)), LOWER(SUBSTRING(first_name, 2)), ' ',
        UPPER(LEFT(last_name, 1)), LOWER(SUBSTRING(last_name, 2))
	) AS customer_name,
	DATE_FORMAT(order_date, '%M %D %Y') as formatted_order_date,
	FORMAT(CAST(order_total AS DECIMAL(10,2)),2) as formatted_order_total
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id;