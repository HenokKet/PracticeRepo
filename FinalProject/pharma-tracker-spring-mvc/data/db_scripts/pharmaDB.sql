DROP DATABASE IF EXISTS pharma;
CREATE DATABASE pharma;
USE pharma;

-- Users
CREATE TABLE medUser (
  user_id       INT PRIMARY KEY AUTO_INCREMENT,
  user_name     VARCHAR(100)  NOT NULL,
  user_email    VARCHAR(254)  NOT NULL,
  first_name    VARCHAR(100)  NOT NULL,
  last_name     VARCHAR(100)  NOT NULL,
  password_aes  VARBINARY(256) NOT NULL
);

-- Sample users (passwords encrypted with key '1234')
INSERT INTO medUser (user_name, user_email, first_name, last_name, password_aes) VALUES
('test_user_1',  'user1@email.com',  'Alex', 'Johnson', AES_ENCRYPT('password123', '1234')),
('test_admin_1', 'admin@email.com', 'Taylor', 'Smith', AES_ENCRYPT('securepass',  '1234'));

-- Medications
CREATE TABLE medications (
  ApplicationNo        INT PRIMARY KEY,
  user_id              INT NOT NULL,
  medication_name      VARCHAR(256) NOT NULL,
  firstDosage          DATETIME NOT NULL,
  LastDosage           DATETIME NOT NULL,
  dose_interval_hours  INT NOT NULL,
  Qty                  INT NOT NULL,
  CONSTRAINT FK_med_user FOREIGN KEY (user_id) REFERENCES medUser(user_id)
);

INSERT INTO medications (ApplicationNo, user_id, medication_name, firstDosage, LastDosage, dose_interval_hours, Qty) VALUES
(1001, 1, 'Amoxicillin', '2025-11-01 08:00:00', '2025-11-10 20:00:00', 8,  20),
(1002, 1, 'Ibuprofen',   '2025-11-03 09:00:00', '2025-11-20 09:00:00', 6,  30),
(1003, 2, 'Metformin',   '2025-11-02 07:30:00', '2026-01-02 07:30:00', 24, 60),
(1004, 2, 'Lisinopril',  '2025-11-05 08:00:00', '2026-02-05 08:00:00', 24, 90);

-- Medication log
CREATE TABLE medicationLog (
  logId          INT PRIMARY KEY AUTO_INCREMENT,
  user_id        INT NOT NULL,
  ApplicationNo  INT NOT NULL,
  DosageTime     DATETIME NOT NULL,
  Qty            INT NOT NULL,
  CONSTRAINT FK_user_id        FOREIGN KEY (user_id)       REFERENCES medUser(user_id),
  CONSTRAINT FK_ApplicationNo  FOREIGN KEY (ApplicationNo) REFERENCES medications(ApplicationNo)
);

INSERT INTO medicationLog (user_id, ApplicationNo, DosageTime, Qty) VALUES
(1, 1001, '2025-11-04 08:00:00', 1),
(1, 1001, '2025-11-05 16:00:00', 1),
(1, 1002, '2025-11-04 09:00:00', 1),
(1, 1002, '2025-11-06 21:00:00', 2),
(2, 1003, '2025-11-04 07:30:00', 1),
(2, 1003, '2025-11-05 07:30:00', 1),
(2, 1004, '2025-11-06 08:00:00', 1),
(2, 1004, '2025-11-07 08:00:00', 1);

-- Quick checks
SELECT * FROM medications;
SELECT * FROM medicationLog;

-- Joined view with first/last names (also includes a derived full_name)
SELECT 
  u.user_name                        AS user_name,
  u.first_name,
  u.last_name,
  CONCAT(u.first_name, ' ', u.last_name) AS full_name,
  m.medication_name                  AS medication,
  l.DosageTime                       AS dosage_date,
  l.Qty                              AS qty
FROM medicationLog AS l
JOIN medications  AS m ON l.ApplicationNo = m.ApplicationNo
JOIN medUser      AS u ON l.user_id       = u.user_id
ORDER BY l.DosageTime, u.user_name, m.medication_name;