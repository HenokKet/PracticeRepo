use simpleschool;

SELECT SubjectId, CourseName from course;
-- Filtering By String
SELECT SubjectId, CourseName from course WHERE CourseName = 'Algebra 1';

SELECT SubjectId, CourseName from course WHERE CourseName BETWEEN 'A' and 'Bz';


-- Filtering by Numeric
SELECT SubjectId, CourseName, CreditHours from course WHERE creditHours = 3.00;

SELECT SubjectId, CourseName, CreditHours from course WHERE creditHours > 3.00;

SELECT SubjectId, CourseName, CreditHours from course WHERE creditHours BETWEEN  3.00 AND 5.00;

-- Filtering BY Date
SELECT SemesterID, StartDate, EndDate FROM semester WHERE StartDate = '2021-09-01';

SELECT SemesterID, StartDate, EndDate FROM semester WHERE StartDate BETWEEN  '2021-09-01' AND '2022-01-01';

SELECT SemesterID, YEAR(StartDate) AS SchoolYear, StartDate, EndDate FROM semester;

SELECT DISTINCT(YEAR(StartDate)) AS SchoolYear FROM semester;

SELECT SemesterID, StartDate, EndDate FROM semester WHERE YEAR(StartDate) BETWEEN 2022 AND 2023 AND MONTH(StartDate) = 9;


-- Working with Null
-- Looking for Null Values
SELECT RoomId, RoomNumber from room WHERE Description IS NULL;

-- Non Null Values
SELECT RoomId, RoomNumber from room WHERE Description IS NOT NULL;

SELECT RoomId, RoomNumber, IFNULL(Description, 'Standard Classroom') AS Description from room;

-- Filtering on Multiple Columns
SELECT RoomId, RoomNumber, IFNULL(Description, 'Standard Classroom') AS Description from room 
WHERE Description IS NULL AND BuildingId = 1;

-- Like Keyword
SELECT SubjectId, CourseName, CreditHours from course 
WHERE CourseName LIKE 'Algebra%' AND creditHours BETWEEN  3.00 AND 5.00;
 
SELECT SubjectId, CourseName, CreditHours from course 
WHERE CourseName LIKE '%2' AND creditHours BETWEEN  3.00 AND 5.00;

SELECT SubjectId, CourseName, CreditHours from course 
WHERE CourseName LIKE '_P%' AND creditHours BETWEEN  3.00 AND 5.00;

-- AND vs OR
SELECT RoomId, RoomNumber, IFNULL(Description, 'Standard Classroom') AS Description from room 
WHERE  Description IS NULL AND BuildingId = 1;

SELECT RoomId, RoomNumber, IFNULL(Description, 'Standard Classroom') AS Description from room 
WHERE Description IS NULL OR BuildingId = 2;

-- Does Not Run but Demonstrates
SELECT CustomerName
FROM Customers
WHERE CustomerName LIKE '%''%';
-- Wingtip Toys (Kapa'a, HI)


SELECT * FROM ROOM
WHERE RoomNumber IN (100, 101, 102);

SELECT SemesterID, YEAR(StartDate) AS SchoolYear, StartDate, EndDate FROM semester
WHERE StartDate IN ('2021-09-01', '2022-01-01', '2022-09-01');

SELECT SemesterID, YEAR(StartDate) AS SchoolYear, StartDate, EndDate FROM semester
WHERE YEAR(StartDate) IN (2021, 2022);

SELECT SubjectId, CourseName, CreditHours from course 
WHERE CourseName IN ('Algebra 1', 'Biology');


-- Order BY
SELECT SemesterID, YEAR(StartDate) AS SchoolYear, StartDate, EndDate FROM semester
ORDER BY SchoolYear DESC, StartDate DESC;



