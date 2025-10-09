use simpleschool;
-- 1. Write a SELECT query that gets all course names, hours, and subject names
-- where the subject name is "History," without table aliases, using the INNER
-- keyword, and ordered by course name.
SELECT course.courseName, course.creditHours, subject.SubjectName
FROM course
INNER JOIN subject on course.SubjectID = subject.SubjectID
WHERE subject.SubjectName = "History" ORDER BY course.courseName;

-- 2. Write a SELECT query that gets all course names, hours, and subject names
-- where the subject name is "History," with table aliases, without the INNER
-- keyword, and ordered by course name.
SELECT C.courseName, C.creditHours, S.SubjectName
FROM course C
JOIN subject S on C.SubjectID = S.SubjectID
WHERE S.SubjectName = 'History' ORDER BY C.courseName;


-- 3. Write a SELECT query that gets all course names, hours, and subject names
-- where the subject name is "History," with table aliases, using the INNER
-- keyword, and ordered by course name.
SELECT
	C.courseName, 
	C.creditHours,
	S.subjectName
FROM course C
INNER JOIN subject S on C.SubjectId = S.subjectId
WHERE S.subjectName = 'History'
ORDER BY C.courseName;

-- 4. Write a SELECT query that gets all course names, hours, and subject names
-- where the subject name contains the word "Art" anywhere in the name, ordered
-- by subject name then course name.
SELECT
	C.courseName, 
	C.creditHours,
	S.subjectName
FROM course C
INNER JOIN subject S on C.SubjectId = S.subjectId
WHERE S.subjectName LIKE('%Art%')
ORDER BY S.subjectName, C.courseName;


-- 5. Write a SELECT query that gets all room numbers, descriptions, and building
-- names for rooms missing description information.

SELECT 
	R.roomNumber,
	R.Description,
	B.BuildingName
FROM room R
INNER JOIN building B ON R.buildingId = B.buildingId
WHERE 
	R.Description IS NULL
ORDER BY R.roomNumber;

-- 6. Get all the course names that are more than three credit hours for the teacher
-- named "Geno Booy."
SELECT Course.CourseName 
FROM Course 
INNER JOIN Section ON Course.CourseId = Section.CourseId
INNER JOIN Teacher ON Section.TeacherId = Teacher.TeacherId
WHERe Course.CreditHours > 3
AND Teacher.FirstName = 'Geno'
AND Teacher.LastName = 'Booy';

-- 7. Same as the last query, but add the subject name to the query.
SELECT 
	CONCAT(Teacher.FirstName, ' ', Teacher.LastName) AS Teacher, 
    Course.CourseName,
	Course.CreditHours,
    Subject.SubjectName
FROM Course 
	INNER JOIN Section ON Course.CourseId = Section.CourseId
	INNER JOIN Teacher ON Section.TeacherId = Teacher.TeacherId
    INNER JOIN Subject ON Course.SubjectId = Subject.SubjectId
WHERE Course.CreditHours > 3
	AND Teacher.FirstName = 'Geno'
	AND Teacher.LastName = 'Booy';