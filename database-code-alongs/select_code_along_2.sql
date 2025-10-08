USE simpleschool;

SELECT * FROM Student;

SELECT StudentId, FirstName, Lastname FROM Student;

SELECT Count(*) FROM student; -- 50

SELECT COUNT(DISTINCT (ClassYear)) FROM Student;

SELECT * FROM Student LIMIT 5;

Select FirstName, LastName FROM student;

Select StudentId, CONCAT(LastName, ', ', FirstName) AS StudentName, CLassYear 
FROM Student;

Select StudentId, CONCAT(LastName, ', ', Left(FirstName, 1)) AS StudentName, CLassYear 
FROM Student;

SELECT * FROM course;

SELECT Distinct(CourseName) FROM course
WHERE CreditHours >= 4.00
ORDER BY CourseName;
