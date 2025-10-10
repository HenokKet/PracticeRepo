Use SimpleSchool;

-- 1 
INSERT INTO Subject (SubjectID, SubjectName)
VALUES (6, "Comp. Science");

Select * From Subject;
-- 2 
UPDATE Subject
 SET SubjectName = "Computer Science"
WHERE SubjectID = 6;

-- 3 
INSERT INTO Course (SubjectID, CourseName, CreditHours)
VALUES 
 (6, "Java", 3),
 (6, "C#", 3),
 (6, "JavaScript", 3),
 (6, "Advanced Java", 3);
 
SELECT *
 FROM Course
WHERE SubjectID = 6;
-- 4
UPDATE Course
 SET CourseName = "Java 101"
WHERE CourseID = 24; 

-- 5 
UPDATE Course
 SET CourseName = "Java 201",
	 CreditHours = 4
WHERE CourseID = 27;

-- 6 
DELETE FROM Subject
WHERE SubjectId = 6;
-- Cannot delete or update a parent row: a foreign key constraint fails  

-- 7 
DELETE FROM Course
WHERE CourseId = 24;  

-- 8 
Delete 
From Course 
WHERE SubjectId = 6;

-- 9 
DELETE FROM Subject
WHERE SubjectId = 6;