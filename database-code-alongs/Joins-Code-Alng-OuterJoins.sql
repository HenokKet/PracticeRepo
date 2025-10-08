use simpleschool;
-- Right Join
SELECT *
FROM SectionRoster 
Right JOIN Student ON Student.StudentID = SectionRoster.StudentID
WHERE SectionRoster.StudentID IS NULL;

-- Full Outer Join
SELECT *
FROM SectionRoster 
Right JOIN Student ON Student.StudentID = SectionRoster.StudentID
WHERE SectionRoster.StudentID IS NULL
UNION ALL
SELECT *
FROM SectionRoster 
LEFT JOIN Student ON Student.StudentID = SectionRoster.StudentID
WHERE Student.StudentID IS NULL;

