drop database if exists pharma;

create database pharma;

use pharma;

create table medUser (
	user_id int primary key auto_increment,
    user_name varchar(100) not null,
    user_email varchar(254) not null,
    user_role varchar(10) not null,
    password_aes VARBINARY(256) not null
);

-- Insert 5 sample users
INSERT INTO medUser (user_name, user_email, user_role, password_aes) VALUES
('test_user_1', 'user1@email.com', 'admin', AES_ENCRYPT('password123', '1234')),
('test_admin_1', 'admin@email.com', 'user', AES_ENCRYPT('securepass', '1234'));

create table medications (
	ApplicationNo int primary key,
    user_id int not null,
    medication_name varchar(256) not null,
    firstDosage DateTime not null,
    LastDosage DateTime not null, 
    dose_interval_hours INT NOT NULL,   
    Qty int not null,
    CONSTRAINT FK_med_user FOREIGN KEY (user_id) REFERENCES medUser(user_id)
);

INSERT INTO medications(ApplicationNo, user_id, medication_name, firstDosage, LastDosage,dose_interval_hours, Qty) VALUES
(1001, 1, 'Amoxicillin', '2025-11-01 08:00:00', '2025-11-10 20:00:00', 8, 20),
(1002, 1, 'Ibuprofen',   '2025-11-03 09:00:00', '2025-11-20 09:00:00', 6, 30),
(1003, 2, 'Metformin',   '2025-11-02 07:30:00', '2026-01-02 07:30:00', 24, 60),
(1004, 2, 'Lisinopril',  '2025-11-05 08:00:00', '2026-02-05 08:00:00', 24, 90);

create table medicationLog (
	logId int primary key auto_increment,
    user_id int not null,
    ApplicationNo int not null,
    DosageTime DateTime not null,
    Qty int not null,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES medUser(user_id),
    CONSTRAINT FK_ApplicationNo FOREIGN KEY (ApplicationNo) REFERENCES medications(ApplicationNo)
);
INSERT INTO medicationLog(user_id, ApplicationNo, DosageTime, Qty) VALUES
(1, 1001, '2025-11-04', 1),
(1, 1001, '2025-11-05', 1),
(1, 1002, '2025-11-04', 1),
(1, 1002, '2025-11-06', 2),
(2, 1003, '2025-11-04', 1),
(2, 1003, '2025-11-05', 1),
(2, 1004, '2025-11-06', 1),
(2, 1004, '2025-11-07', 1);

SELECT * FROM medications;

SELECT * FROM medicationLog;

SELECT u.user_name AS user_name,
m.medication_name AS medication,
l.DosageTime AS dosage_date,
l.Qty AS qty
FROM medicationLog AS l
JOIN medications AS m ON l.ApplicationNo = m.ApplicationNo
JOIN medUser AS u ON l.user_id = u.user_id
ORDER BY l.DosageTime, u.user_name, m.medication_name;
