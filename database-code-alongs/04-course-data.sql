USE SimpleSchool;

-- 4 grade types
INSERT INTO GradeType (GradeTypeName)
VALUES ('Homework'), ('Quiz'), ('Project'), ('Exam');

INSERT INTO GradeItem (GradeTypeID, PointsPossible, Description)
VALUES (4, 100, 'Final Exam'),
(3, 50, 'Capstone Project');

-- 5 subjects
INSERT INTO `Subject` (SubjectName)
VALUES ('Language Arts'), ('Math'), ('Science'), ('Arts'), ('History');

-- 8 periods
INSERT INTO `Period` (PeriodName, StartTime, EndTime)
VALUES ('1st Period', '8:00:00', '8:45:00'),
('2nd Period', '9:00:00', '9:45:00'),
('3rd Period', '10:00:00', '10:45:00'),
('4th Period', '11:00:00', '11:45:00'),
('5th Period', '12:00:00', '12:45:00'),
('6th Period', '13:00:00', '13:45:00'),
('7th Period', '14:00:00', '14:45:00'),
('8th Period', '15:00:00', '15:45:00');

-- 6 semesters
INSERT INTO Semester (StartDate, EndDate, `Description`)
VALUES ('2021-09-01', '2021-12-31', null),
('2022-01-01', '2022-03-31', null),
('2022-04-01', '2022-06-30', null),
('2022-09-01', '2022-12-31', null),
('2023-01-01', '2023-03-31', null),
('2023-04-01', '2023-06-30', null);

-- 2 buildings
INSERT INTO Building (BuildingName)
VALUES ('Main Campus'), ('Vocational Tech Campus');

-- 13 rooms
INSERT INTO Room (BuildingID, RoomNumber, `Description`)
VALUES (1, 100, 'Gymnasium'),
(1, 101, null),
(1, 102, null),
(1, 103, null),
(1, 104, null),
(1, 105, null),
(1, 200, null),
(1, 201, null),
(1, 202, null),
(1, 203, null),
(1, 204, null),
(2, 100, 'Chemistry Lab'),
(2, 101, null);

-- 23 courses
INSERT INTO Course (SubjectID, CourseName, CreditHours)
VALUES (1, 'English 1', 3.0),
(1, 'English 2', 3.0),
(1, 'English 3', 3.0),
(1, 'AP English', 3.0),
(2, 'Algebra 1', 4.0),
(2, 'Geometry', 4.0),
(2, 'Algebra 2', 4.0),
(2, 'Statistics', 3.0),
(2, 'Calculus', 4.0),
(3, 'Biology', 4.0),
(3, 'Chemistry', 4.0),
(3, 'Anatomy', 4.0),
(3, 'Geology', 4.0),
(4, 'Art 1', 3.0),
(4, 'Art 2', 3.0),
(4, 'Choir', 3.0),
(4, 'Band', 3.0),
(4, 'Theater', 3.0),
(5, 'European History', 3.0),
(5, 'American History', 3.0),
(5, 'African History', 3.0),
(5, 'Middle Eastern History', 3.0),
(5, 'Asian History', 3.0);

INSERT INTO Section (CourseID, TeacherID, SemesterID, PeriodID, RoomID) 
VALUES (6, 9, 3, 5, 9),
(15, 10, 1, 1, 6),
(11, 2, 1, 1, 13),
(6, 1, 4, 1, 5),
(17, 1, 6, 6, 3),
(18, 10, 6, 7, 2),
(21, 6, 2, 5, 7),
(3, 4, 2, 8, 5),
(2, 8, 2, 7, 11),
(23, 6, 1, 5, 3),
(20, 6, 5, 2, 1),
(12, 7, 4, 5, 2),
(17, 10, 5, 3, 13),
(3, 8, 6, 2, 4),
(13, 3, 1, 3, 8),
(6, 9, 6, 4, 9),
(20, 5, 5, 1, 7),
(9, 8, 1, 7, 11),
(21, 5, 2, 3, 10),
(10, 3, 3, 1, 11),
(3, 7, 6, 7, 7),
(14, 6, 5, 3, 11),
(20, 6, 6, 5, 7),
(9, 8, 4, 4, 11),
(16, 7, 5, 8, 2),
(4, 6, 4, 6, 7),
(2, 6, 2, 2, 5),
(11, 5, 3, 2, 4),
(8, 5, 3, 5, 4),
(18, 6, 3, 2, 9),
(14, 2, 2, 5, 6),
(17, 1, 2, 2, 9),
(18, 7, 5, 5, 12),
(21, 7, 4, 8, 8),
(14, 8, 1, 4, 6),
(17, 2, 5, 8, 1),
(11, 10, 6, 3, 8),
(13, 1, 2, 6, 12),
(22, 10, 5, 8, 10),
(21, 1, 2, 5, 9),
(1, 6, 3, 3, 8),
(11, 7, 4, 3, 4),
(12, 3, 4, 1, 5),
(16, 6, 5, 7, 8),
(6, 4, 1, 7, 4),
(19, 5, 4, 2, 6),
(16, 9, 5, 3, 12),
(6, 2, 6, 5, 10),
(2, 7, 2, 4, 4),
(21, 3, 2, 8, 4);

-- check data with this query

-- select s.SectionID, t.FirstName, t.LastName, c.CourseName, p.PeriodName, sm.StartDate, sm.EndDate, b.BuildingName, r.RoomNumber, r.Description
-- FROM Section s
-- INNER JOIN Teacher t ON s.teacherid = t.teacherid
-- inner join Room r on s.RoomID = r.RoomID
-- inner join Building b on r.BuildingID = b.BuildingID
-- inner join Semester sm on s.SemesterID =sm.SemesterID
-- inner join Period p on s.PeriodID = p.PeriodID
-- inner join Course c on s.CourseID = c.CourseID
-- order by StartDate, PeriodName, LastName;