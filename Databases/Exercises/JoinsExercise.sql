use simpleschool;

-- 1
SELECT courseName, creditHours, SubjectName
FROM course
INNER JOIN subject on course.SubjectID = subject.SubjectID
WHERE subject.SubjectName = "History" ORDER BY courseName;

-- 2
SELECT courseName, creditHours, SubjectName
FROM course C
JOIN subject S on C.SubjectID = S.SubjectID
WHERE S.SubjectName = 'History' ORDER BY courseName;

-- 3
SELECT courseName, creditHours, SubjectName
FROM course C
INNER JOIN subject S on C.SubjectId = S.subjectId
WHERE S.subjectName = 'History' ORDER BY courseName;

-- 4
SELECT courseName, creditHours, SubjectName
FROM course C
INNER JOIN subject S on C.SubjectId = S.subjectId
WHERE S.subjectName LIKE('%Art%') ORDER BY subjectName,courseName;

-- 5 
SELECT roomNumber,Description,BuildingName
FROM room R
INNER JOIN building B ON R.buildingId = B.buildingId
WHERE Description IS NULL ORDER BY roomNumber;

-- 6
SELECT CourseName 
FROM Course 
INNER JOIN Section ON Course.CourseId = Section.CourseId
INNER JOIN Teacher ON Section.TeacherId = Teacher.TeacherId
WHERE CreditHours > 3
AND FirstName = 'Geno'
AND LastName = 'Booy';