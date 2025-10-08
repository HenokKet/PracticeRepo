drop database if exists SimpleSchool;

CREATE DATABASE SimpleSchool;

USE SimpleSchool;

CREATE TABLE GradeType (
    GradeTypeID int primary key auto_increment,
    GradeTypeName varchar(25) not null
);

CREATE TABLE GradeItem (
    GradeItemID int primary key auto_increment,
    GradeTypeID int not null,
    PointsPossible decimal(5,2) not null default 0,
    `Description` varchar(255) not null,
    constraint fk_GradeItem_GradeType_id
        foreign key (GradeTypeID)
        references GradeType(GradeTypeID)
);

CREATE TABLE `Subject` (
    SubjectID int primary key auto_increment,
    SubjectName varchar(50) not null
);

CREATE TABLE Course (
    CourseID int primary key auto_increment,
    SubjectID int not null,
    CourseName varchar(50) not null,
    CreditHours decimal(3,2) not null default 0.0,
    constraint fk_Course_Subject_id
        foreign key (SubjectID)
        references Subject(SubjectID)
);

CREATE TABLE `Period` (
    PeriodID int primary key auto_increment,
    PeriodName varchar(50) not null,
    StartTime time not null,
    EndTime time not null
);

CREATE TABLE Semester (
    SemesterID int primary key auto_increment,
    StartDate date not null,
    EndDate date not null,
    `Description` varchar(50) null
);

CREATE TABLE Building (
    BuildingID int primary key auto_increment,
    BuildingName varchar(50) not null
);

CREATE TABLE Room (
    RoomID int primary key auto_increment,
    BuildingID int not null,
    RoomNumber int not null default(0),
    `Description` varchar(50) null,
    constraint fk_Room_Building_id
        foreign key (BuildingID)
        references Building(BuildingID)
);

CREATE TABLE Teacher (
    TeacherID int primary key auto_increment,
    FirstName varchar(50) not null,
    LastName varchar(50) not null
);

CREATE TABLE Student (
    StudentID int primary key auto_increment,
    FirstName varchar(50) not null,
    LastName varchar(50) not null,
    ClassYear char(4) not null
);

CREATE TABLE Section (
    SectionID int primary key auto_increment,
    CourseID int not null,
    TeacherID int not null,
    SemesterID int not null,
    PeriodID int not null,
    RoomID int not null,
    constraint fk_Section_Course_id
        foreign key (CourseID)
        references Course(CourseID),
	constraint fk_Section_Teacher_id
        foreign key (TeacherID)
        references Teacher(TeacherID),
	constraint fk_Section_Semester_id
        foreign key (SemesterID)
        references Semester(SemesterID),        
	constraint fk_Section_Period_id
        foreign key (PeriodID)
        references `Period`(PeriodID),        
	constraint fk_Section_Room_id
        foreign key (RoomID)
        references Room(RoomID)    
);

CREATE TABLE SectionRoster (
    SectionRosterID int primary key auto_increment,
    SectionID int not null,
    StudentID int not null,
    CurrentGrade decimal(5,2) null,
    constraint fk_SectionRoster_Section_id
        foreign key (SectionID)
        references Section(SectionID),
	constraint fk_SectionRoster_Student_id
        foreign key (StudentID)
        references Student(StudentID)
);

CREATE TABLE Grade (
    GradeID int primary key auto_increment,
    SectionRosterID int not null,
    GradeItemID int not null,
    Score decimal(5,2) null,
    constraint fk_Grade_SectionRoster_id
        foreign key (SectionRosterID)
        references SectionRoster(SectionRosterID),
	constraint fk_Grade_GradeItem_id
        foreign key (GradeItemID)
        references GradeItem(GradeItemID)
);