DROP DATABASE IF EXISTS exercise_stored_procedures_and_transactions;
CREATE DATABASE exercise_stored_procedures_and_transactions;

USE exercise_stored_procedures_and_transactions;

-- Create Customer table
CREATE TABLE Customers (
    CustomerId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Phone VARCHAR(20),
    Status VARCHAR(20) NOT NULL
);

-- Insert sample data
INSERT INTO Customers (CustomerName, Email, Phone, Status) VALUES
('John Doe', 'john.doe@email.com', '555-0101', 'Active'),
('Jane Smith', 'jane.smith@email.com', '555-0102', 'Active'),
('Bob Johnson', 'bob.johnson@email.com', '555-0103', 'Inactive'),
('Alice Williams', 'alice.williams@email.com', '555-0104', 'Active'),
('Charlie Brown', 'charlie.brown@email.com', '555-0105', 'Inactive'),
('Diana Davis', 'diana.davis@email.com', '555-0106', 'Active');

DELIMITER //
CREATE PROCEDURE GetActiveCustomers()
BEGIN
 SELECT * 
 FROM Customers
 WHERE Status = 'Active';
END //
DELIMITER ;

CALL GetActiveCustomers(); 

DELIMITER //
CREATE PROCEDURE GetCustomerDetails(IN CustomerIdIn INT)
BEGIN
 SELECT * FROM Customers
 WHERE CustomerId = CustomerIdIn;
END //
DELIMITER ;

CALL GetCustomerDetails(2); 

DELIMITER //
CREATE PROCEDURE GetCustomerInfo(IN CustomerIdIn INT, IN CustomerNameIn
VARCHAR(255))
BEGIN
 SELECT * FROM Customers
 WHERE CustomerId = CustomerIdIn AND CustomerName = CustomerNameIn;
END //
DELIMITER ;

CALL GetCustomerInfo(1, 'John Doe');

DROP PROCEDURE IF EXISTS GetCustomerDetails;
DELIMITER //
CREATE PROCEDURE GetCustomerDetails(IN CustomerIdIn INT)
BEGIN
 SELECT * FROM Customers
 WHERE CustomerId = CustomerIdIn AND Status = 'Active';
END //
DELIMITER ;
CALL GetCustomerDetails(1);

DROP PROCEDURE IF EXISTS GetCustomerInfo;
