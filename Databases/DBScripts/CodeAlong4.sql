Use simpleschool; 

select * FROM Student; 

SELECT StudentId, FirstName, LastName From Student; 

SELECT COUNT(*) FROM student; -- 50

SELECT COUNT(distinct(ClassYear)) FROM student; -- 50

SELECT * FROM student LIMIT 5; -- 50

select FirstName,LastName From Student;

select StudentId, CONCAT(LastName, ', ', Left(FirstName,1)) AS StudentName,ClassYear
 From Student;

Select * From course; 

Select distinct(CourseName) from course 
Where CreditHours >= 4.00
ORDER BY CourseName DESC;