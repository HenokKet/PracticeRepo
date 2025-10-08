-- SELECT
-- 	Field
-- FROM
-- 	Table
-- JOIN
-- 	Table
-- WHERE
-- 	Clause
-- ORDER
-- 	Clause
use simpleschool;

SELECT FirstName, LastName
FROM Teacher;

-- Join or Left Join
SELECT 
	Teacher.FirstName, 
	Teacher.LastName,
    Section.SectionID
FROM Teacher
JOIN Section ON Teacher.TeacherID = Section.TeacherID;

-- Join with Alias
SELECT 
	T.TeacherID,
	T.FirstName, 
	T.LastName,
    S.SectionID
FROM Teacher T
LEFT JOIN Section S ON T.TeacherID = S.TeacherID
WHERE S.SemesterID = 6;

-- Join Multiple Tables
SELECT
	T.FirstName,
    T.LastName,
	C.CourseName,
    C.CreditHours
FROM
	Teacher T
 JOIN
	Section S ON T.TeacherID = S.TeacherID
JOIN
	Course C ON S.CourseID = C.CourseID
WHERE 
	S.SemesterID = 6;

SELECT
	T.FirstName AS "Teacher First Name",
    T.LastName AS "Teacher Last Name",
    Sub.SubjectName  AS "Subject Name",
	C.CourseName AS "Course Name",
    C.CreditHours  AS "Credit Hours"
FROM
	Teacher T
 JOIN
	Section S ON T.TeacherID = S.TeacherID
JOIN
	Course C ON S.CourseID = C.CourseID
JOIN 
	Subject Sub ON C.SubjectID = Sub.SubjectID
WHERE 
	S.SemesterID = 6
ORDER BY
	S.roomId;