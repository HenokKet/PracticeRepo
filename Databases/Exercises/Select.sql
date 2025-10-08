use SimpleSchool;

# 1
SELECT 'All for one, and one for all.';

# 2
SELECT CONCAT('All for one, ', 'and one for all.');

# 3
SELECT 6 + 6;

# 4
SELECT 5 / 2;

# 5
SELECT 5.0 / 2.0;

# 6
SELECT 6 DIV 4 AS quotient, 6 MOD 4 AS remainder;

# 7
SELECT POWER(6, 2);

#Select from tables
#1 
SELECT * FROM Building;

#2 
SELECT PeriodName, StartTime, EndTime
FROM Period;

#3
SELECT TABLE_NAME
FROM information_schema.tables
WHERE TABLE_SCHEMA = 'SimpleSchool'
  AND TABLE_ROWS = 0;

#4 
SELECT CONCAT(CourseName, ' (', CreditHours, ')') AS CourseAndCredits
FROM Course;

#5 
SELECT CONCAT(FirstName, ' ', LEFT(LastName, 1), '.') AS TeacherName
FROM Teacher
ORDER BY TeacherID
LIMIT 5;

#6 
SELECT COUNT(*) AS RoomCount
FROM Room; 

#7 
SELECT MIN(RoomNumber) AS MinRoom, MAX(RoomNumber) AS MaxRoom
FROM Room;

#8 
Select Description 
FROM Room; # That there is Many Null tables and only 2 actual descriptions 

#9 
SELECT COUNT(DISTINCT SubjectID) AS UniqueSubjects
FROM Course;

#10 
SELECT COUNT(*) AS GradeTypeCount
FROM GradeType; 

#11 
SELECT GradeTypeID AS ID, GradeTypeName AS Name
FROM GradeType
ORDER BY GradeTypeID;

#12 
SELECT DISTINCT GradeTypeID 
From GradeItem; 

#13

#1,2

