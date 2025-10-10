-- SELECT * FROM product;

-- SELECT * FROM productCategory;

-- Safe Individual Record Delete
DELETE FROM product
WHERE productId = 103;

-- DELETE MULTIPLE RECORDS
DELETE FROM product where CategoryId = 2;

-- DELETE FROM PRODUCTCategory
DELETE FROM productcategory WHERE CategoryId IN (2,3);

-- Proper Delete Across Foreign Keys
DELETE FROM product WHERE CategoryId = 1;
DELETE FROM productcategory WHERE CategoryId = 1;

