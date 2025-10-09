use code_along; 

-- Update a Single Record
UPDATE product
SET Price = 6.00
WHERE ProductID = 101;

-- UPDATE MULTIPLE COLUMNS
UPDATE product
SET 
price = 8.99,
productName = 'Deluxe Pen Set'
WHERE productId = 102;

-- Set End Date for All Stationary Items
-- Update 3 Rows
Update product
SET endDate = '2024-12-31'
WHERE categoryID = 2;

-- This should fail - Invalid Foreign Key Update
Update Product
SET categoryID = 999
WHERE PRoductID = 103;

-- This should Work- Valid Foreign Key Update
Update Product
SET categoryID = 3
WHERE PRoductID = 103;


-- Changes the name of Classic Fiction to Vintage Novel
-- ● Updates its category to Clearance
-- ● Sets its price to 10.00
-- ● All in one query

-- 103
UPDATE product
	set categoryID = (SELECT categoryId FROM productCategory 
						where categoryName = 'Clearance'),
		price = 10.00
WHERE productiD = 104;


