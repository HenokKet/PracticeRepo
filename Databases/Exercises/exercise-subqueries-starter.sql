Use SimpleSchool; 

-- Part 1: Using NOT EXISTS
--  Write a query to list all Students that are not registerd for a class 
-- Hint(Check the Section Roster) 
SELECT *
FROM Student s
WHERE NOT EXISTS (
SELECT null FROM SectionRoster r
WHERE r.studentId = s.studentId);

-- Part 2: Subqueries in the WHERE Clause
-- Find the name(s) of classes that have the most students registered
SELECT CourseName
FROM Course c
JOIN Section s 
  ON c.CourseID = s.CourseID
JOIN SectionRoster r 
  ON s.SectionID = r.SectionID
GROUP BY c.CourseID, c.CourseName
HAVING COUNT(r.StudentID) = (
  SELECT MAX(StudentCount)
  FROM (
    SELECT COUNT(r2.StudentID) AS StudentCount
    FROM SectionRoster r2
    JOIN Section s2 ON r2.SectionID = s2.SectionID
    GROUP BY s2.CourseID
  ) AS sub
);

-- Part 3: Subquery in the SELECT Clause
-- Select all Classes Show:
-- The Teacher That is assigned to Instruct
-- The Room That the class is in
-- No Joins Allowed
SELECT 
c.CourseName,
  (SELECT CONCAT(t.FirstName, ' ', t.LastName)
   FROM Teacher t
   WHERE t.TeacherID = (
     SELECT s.TeacherID
     FROM Section s
     WHERE s.CourseID = c.CourseID
     LIMIT 1
   )) AS TeacherName,
  (SELECT RoomNumber
   FROM Room r
   WHERE r.RoomID = (
     SELECT s.RoomID
     FROM Section s
     WHERE s.CourseID = c.CourseID
     LIMIT 1
   )) AS RoomNumber
FROM Course c;

-- Part 4: Subquery in the HAVING Clause
-- Use the Query from Part 2. Use Having to determine which class(s)
-- Has the least amount of Students (Must Be in a Subquery)
SELECT CourseName
FROM Course c
JOIN Section s 
  ON c.CourseID = s.CourseID
JOIN SectionRoster r 
  ON s.SectionID = r.SectionID
GROUP BY c.CourseID, c.CourseName
HAVING COUNT(r.StudentID) = (
  SELECT MIN(StudentCount)
  FROM (
    SELECT COUNT(r2.StudentID) AS StudentCount
    FROM SectionRoster r2
    JOIN Section s2 
      ON r2.SectionID = s2.SectionID
    GROUP BY s2.CourseID
  ) AS sub
);

-- Part 5: Correlated Subquery
-- Return the top 3 Teachers that have the most students to teach 
-- across all classes and semesters
SELECT t.TeacherID,
  CONCAT(t.FirstName, ' ', t.LastName) AS TeacherName,
  COUNT(r.StudentID) AS TotalStudents
FROM Teacher t
JOIN Section s 
  ON t.TeacherID = s.TeacherID
JOIN SectionRoster r 
  ON s.SectionID = r.SectionID
GROUP BY t.TeacherID, TeacherName
HAVING (
  SELECT COUNT(DISTINCT t2.TeacherID)
  FROM Teacher t2
  JOIN Section s2 ON t2.TeacherID = s2.TeacherID
  JOIN SectionRoster r2 ON s2.SectionID = r2.SectionID
  GROUP BY t2.TeacherID
  HAVING COUNT(r2.StudentID) > COUNT(r.StudentID)
  LIMIT 1
) 
ORDER BY TotalStudents DESC
LIMIT 3;