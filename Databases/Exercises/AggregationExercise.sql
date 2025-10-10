drop database if exists BookBarn;

CREATE DATABASE BookBarn;

USE BookBarn;

-- Tables 
CREATE TABLE Genre (
  GenreId    INT PRIMARY KEY,
  GenreName  VARCHAR(100)
);
INSERT INTO Genre (GenreId, GenreName) VALUES
(1, 'Fiction'),
(2, 'Business');

CREATE TABLE Staff (
  StaffId   INT PRIMARY KEY,
  LastName  VARCHAR(100),
  HireDate  DATE
);
INSERT INTO Staff (StaffId, LastName, HireDate) VALUES
(1, 'Nguyen', '2020-01-01'),
(2, 'Smith',  '2021-03-15');

CREATE TABLE Book (
  BookId   INT PRIMARY KEY,
  GenreId  INT,
  Title    VARCHAR(200),
  Price    DECIMAL(10,2),
  FOREIGN KEY (GenreId) REFERENCES Genre(GenreId)
);
INSERT INTO Book (BookId, GenreId, Title, Price) VALUES
(1, 1, 'Into the Woods',         14.99),
(2, 2, 'Startup Fundamentals',   22.00),
(3, 1, 'Ghost Leaves',           11.50);


CREATE TABLE Sale (
  SaleId   INT PRIMARY KEY,
  StaffId  INT,
  Total    DECIMAL(10,2),
  FOREIGN KEY (StaffId) REFERENCES Staff(StaffId)
);
INSERT INTO Sale (SaleId, StaffId, Total) VALUES
(101, 1, 295.75),
(102, 2, 840.20);

-- part 1 
SELECT
	MIN(Price) AS "Minimum Price",
    MAX(Price) AS "Maximum Price",
    FORMAT(AVG(Price), 2) AS "Average Price"
FROM Book;

SELECT GenreName,FORMAT(AVG(Price), 2) AS "Average Price"
FROM Book b
JOIN Genre g
    ON b.GenreId = g.GenreId
GROUP BY GenreName;

SELECT GenreName,FORMAT(AVG(Price), 2) AS "Average Price"
FROM Book b
JOIN Genre g
    ON b.GenreId = g.GenreId
GROUP BY GenreName
HAVING AVG(Price) > 15;

-- part 2 
SELECT LastName,COUNT(SaleId) AS Total_Sales
FROM Sale sa
JOIN Staff s
  ON sa.StaffId = s.StaffId
GROUP BY LastName;

SELECT LastName,SUM(Total) AS Total_Sales_Value
FROM Sale sa
JOIN Staff s
  ON sa.StaffId = s.StaffId
GROUP BY LastName;

SELECT LastName,SUM(Total) AS Total_Sales_Value
FROM Sale sa
JOIN Staff s
  ON sa.StaffId = s.StaffId
GROUP BY LastName
HAVING SUM(Total)> 800;

SELECT LastName,SUM(Total) AS Total_Sales_Value
FROM Sale sa
JOIN Staff s
  ON sa.StaffId = s.StaffId
GROUP BY LastName
ORDER BY SUM(Total) DESC;

-- part 3 
SELECT HireDate,GROUP_CONCAT(LastName) AS Staff_Names
FROM Staff
GROUP BY HireDate;
