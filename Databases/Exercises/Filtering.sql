USE SimpleSchool;

-- 1
SELECT StudentID, LastName, FirstName
FROM Student
WHERE LEFT(LastName, 2) = 'Cr';

-- 2
SELECT *
FROM Course
WHERE SubjectID = 1 OR SubjectID = 2 OR SubjectID = 4;

-- 3
SELECT *
FROM Course
WHERE SubjectID IN (1, 2, 4);

-- 4
SELECT *
FROM Student
WHERE StudentID = 42;

-- 5
SELECT FirstName
FROM Student
WHERE FirstName LIKE 'C%';

-- 6
SELECT FirstName
FROM Student
WHERE FirstName BETWEEN 'Ce' AND 'Cez';

-- 7
SELECT DISTINCT LastName
FROM Student
ORDER BY LastName
LIMIT 10;

-- 8
SELECT *
FROM Student
ORDER BY StudentID
LIMIT 10;

-- 9
SELECT *
FROM Student
ORDER BY LastName DESC
LIMIT 5;






