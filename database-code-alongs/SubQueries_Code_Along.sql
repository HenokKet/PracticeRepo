use simpleSchool;

-- Subqueries With EXISTS
SELECT *
FROM Teacher
WHERE EXISTS (
SELECT teacherId FROM section) ;

-- Subqueries With EXISTS
SELECT *
FROM Room
WHERE EXISTS (
SELECT Description FROM room); 

-- Use the KEYS!!!
SELECT * FROM section WHERE roomID IN 
(SELECT roomId FROM room WHERE Description IS NOT NULL);

-- Use the KEYS!!!
SELECT * FROM section WHERE roomID IN 
(SELECT roomId FROM room WHERE Description IS NOT NULL)
AND teacherId IN
(SELECT teacherId FROm Teacher WHERE firstName = 'Geno'AND lastName = 'Booy');


-- Scalar Comparrison
SELECT TeacherID, Count(SectionId) FROM Section
GROUP BY TeacherID
HAVING Count(SectionID) <
	(SELECT Count(SectionID) as sectionCount 
		FROM Section
		WHERE TeacherID IN
			(SELECT teacherId 
			FROM Teacher 
			WHERE firstName = 'Geno'AND lastName = 'Booy'));

-- Subquery in Select
SELECT 
	T.TeacherID, 
    T.FirstName, 
    T.LastName, 
	(SELECT COUNT(S.TeacherID) 
		FROM Section S 
		WHERE S.TeacherID = T.TeacherID) AS ClassCount
FROM Teacher T;

-- Multiple Columns
SELECT 
	T.TeacherID, 
    T.FirstName, 
    T.LastName, 
	(SELECT CONCAT(COUNT(S.TeacherID), ' - ',  Count(S.roomId))
		FROM Section S 
		WHERE S.TeacherID = T.TeacherID) AS Teacher_Room_Count
FROM Teacher T;

-- Correlated Subquery
SELECT T.FirstName, T.LastName,
	(SELECT COUNT(DISTINCT(roomId)) from Section S WHERE T.TeacherID = S.TeacherID
		AND  S.SemesterID = 
        (SELECT sem.SemesterID FROM Semester sem WHERE sem.startDate = '2023-04-01')) AS Room_Count
FROM Teacher T


