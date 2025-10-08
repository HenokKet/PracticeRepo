use code_along;


SELECT * FROM customers WHERE first_name = 'JOHN';
-- Concatonate
SELECT
	CONCAT(
		UPPER(LEFT(first_name, 1)), LOWER(SUBSTRING(first_name, 2)), ' ',
        UPPER(LEFT(last_name, 1)), LOWER(SUBSTRING(last_name, 2))
	) AS formatted_name
FROM customers;

-- Cast
SELECT 
	order_total,
    CAST(order_total AS DECIMAL(10,2)) as cast_total
FROM orders;

-- Format
SELECT 
	order_total,
    FORMAT(order_total,2) as cast_total
FROM orders;

-- Date Format
SELECT
	order_date,
    DATE_FORMAT(order_date, '%m %d %y') as formatted_date_lower,
    DATE_FORMAT(order_date, '%M %D %Y') as formatted_date_upper
FROM 
	orders;

-- Only Month?
SELECT
	order_date,
    DATE_FORMAT(order_date, '%m') as formatted_date_lower,
    DATE_FORMAT(order_date, '%M') as formatted_date_upper
FROM 
	orders;

-- Nice Report
SELECT
	CONCAT(
		UPPER(LEFT(c.first_name, 1)), LOWER(SUBSTRING(c.first_name, 2)), ' ',
        UPPER(LEFT(c.last_name, 1)), LOWER(SUBSTRING(c.last_name, 2))
	) AS customer_name,
	DATE_FORMAT(o.order_date, '%m/%d/%y') as order_date,
	FORMAT(CAST(o.order_total AS DECIMAL(10,2)),2) as cast_total
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id;

