-- Aggregates (MATH)

-- What's the cheapest Item on the menu
-- What's the most expensive Item on The Menu
-- What's the average Price of our menu items

SELECT
	MIN(UnitPrice) AS "Minimum Price",
    MAX(UnitPrice) AS "Maximum Price",
    FORMAT(AVG(UnitPrice), 2) AS "Average Price"
FROM item;

-- Average Price of Items
SELECT ItemCategoryID, 
		FORMAT(AVG(UnitPrice), 2) AS "Average Price"
FROM ITEM
GROUP BY ItemCategoryID;

-- Aggregate and Group By Item Category
SELECT ItemCategory.ItemCategoryID, ItemCategory.ItemCategoryName,
		FORMAT(AVG(UnitPrice), 2) AS "Average Price"
FROM ITEM
INNER JOIN itemcategory ON Item.ItemCategoryID = ItemCategory.ItemCategoryID
GROUP BY ItemCategory.ItemCategoryID, ItemCategory.ItemCategoryName;

-- GROUP_CONCAT
SELECT HireDate, group_concat(LastName) as ServersHired
FROM Server
Group By HireDate
Order By HireDate;

SELECT HireDate, LastName as ServersHired
FROM Server
Group By HireDate, LastName
Order By HireDate;


SELECT s.LastName, 
		SUM(o.total) as Order_Totals
FROM `order` o
JOIN Server s ON o.ServerId = s.ServerId
GROUP BY s.LastName
HAVING Order_Totals < 1100;

SELECT
	ic.ItemCategoryName,
		FORMAT(AVG(i.unitPrice),2) as AverageUnitPrice
FROM item i
	INNER JOIN ItemCategory ic ON i.ItemCategoryId = ic.ItemCategoryId
GROUP BY
	ic.ItemCategoryName
HAVING
	AverageUnitPrice > 8
ORDER BY AVG(i.unitPrice) DESC;
