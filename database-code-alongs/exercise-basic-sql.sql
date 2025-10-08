-- Select Literal Values
-- 1. Write a SELECT query that uses the string All for one, and one for all.
SELECT 'All for One', 'One for all';

-- 2. Write a SELECT query that uses the CONCAT function to combine All for one,
-- and and one for all.
SELECT CONCAT('All for One', ' One for All');
-- 3. Write a SELECT query that adds 6 and 6.
SELECT 6+6;
-- 4. Write a SELECT query that divides 5 by 2.
SELECT 5/2;
-- 5. Write a SELECT query that divides 5.0 by 2.0.
SELECT 5.0/2.0;
-- 6. Write a SELECT query with 2 values - 6 divided by 4 and the remainder.
SELECT 6/4, 6%4;
-- 7. Write a SELECT query for 6 squared.
SELECT pow(6,2);

-- Select Against Tables
use simpleschool;
-- 1. Select all the rows from the Building table.
SELECT * FROM building;
-- 2. What are the period name, start, and end times?
SELECT periodId as id, periodName AS name, startTime as start, endTime as end FROM period ORDER BY name;
-- 3. Which table is empty?
SELECT * FROM grade;
-- 4. List all courses and credit hours in the format: CourseName (CreditHours)
SELECT CONCAT(courseName, CONCAT(' (' , creditHours, ')')) as Course FROM course;
-- 5. What are the teachers' full names (first names and last initials) for the first five
-- teachers?
SELECT firstName, LEFT(lastName,1) FROM Teacher LIMIT 5;
-- 6. How many rooms are there?
SELECT COUNT(DISTINCT(roomId)) as RoomCount FROM room;
-- 7. RoomNumber is an integer type. What is the range of room numbers? (Hint:
-- Consider BuildingID too.)
SELECT MIN(roomNumber) as Start, MAX(roomNumber) as End, 
BuildingId from room GROUP BY BuildingId;
-- 8. Examine the Description field of the Room table. What are your observations of
-- the description field?
SELECT Description from room;
-- It Can BE null Definitely not a Primary Key
-- Only Some Rooms are dedicated to one class.

-- 9. How many unique SubjectIDs appear in the Course table?
SELECT COUNT(DISTINCT(SubjectId)) as SubjectCount FROM Course;
-- 10.How many grade types are there?
SELECT COUNT(GradeTypeId) FROM GradeType;
-- 11. What are the IDs and Names of the grade types? (Hint: Name the columns
-- appropriately in the ResultSet)
SELECT GradeTypeID as ID, GradeTypeName as Name FROM GradeType;
-- 12.What grade types appear in the GradeItem table?
SELECT GradeTypeName FROM GradeType 
WHERE GradeTypeID IN (SELECT GradeTypeID FROM GradeItem);

-- 13.What grade types are not utilized in the GradeItem table? (Hint: You can't write a
-- query for this yet. You have to utilize the previous queries.)
SELECT GradeTypeName FROM GradeType 
WHERE GradeTypeID NOT IN (SELECT GradeTypeID FROM GradeItem);

