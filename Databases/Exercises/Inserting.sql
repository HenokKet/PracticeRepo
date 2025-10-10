drop database if exists SmallRetail;

CREATE DATABASE SmallRetail;

USE SmallRetail;

CREATE TABLE Customer (
 CustomerId INT AUTO_INCREMENT PRIMARY KEY,
 CustomerName VARCHAR(100)
);
CREATE TABLE CustomerOrder (
 OrderId INT AUTO_INCREMENT PRIMARY KEY,
 CustomerId INT,
 OrderDate DATE,
 FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);
CREATE TABLE PaymentMethod (
 PaymentMethodId INT PRIMARY KEY,
 MethodName VARCHAR(50)
);
CREATE TABLE Payment (
 PaymentId INT AUTO_INCREMENT PRIMARY KEY,
 OrderId INT,
 PaymentMethodId INT,
 Amount DECIMAL(10, 2),
 FOREIGN KEY (OrderId) REFERENCES CustomerOrder(OrderId),
 FOREIGN KEY (PaymentMethodId) REFERENCES PaymentMethod(PaymentMethodId)
);

INSERT INTO PaymentMethod VALUES
(1, 'Credit Card'),
(2, 'Cash'),
(3, 'Mobile Payment');

-- part 1 
INSERT INTO Customer (CustomerName)
VALUES
('Emma Rivera'),
('Noah Gray');

INSERT INTO CustomerOrder (CustomerId, OrderDate)
VALUES
(1, CURDATE()),
(2, CURDATE());  

-- part 2 
INSERT INTO Payment (OrderId, PaymentMethodId, Amount)
VALUES (1, 1, 49.99);

INSERT INTO Payment (OrderId, PaymentMethodId, Amount)
VALUES (1, 999, 49.99);
-- Cannot add or update a child row: a foreign key constraint fails 

-- part 3 
INSERT INTO Customer (CustomerName)
VALUES
('Liam Davis'),
('Olivia Brooks'),
('Sophia Martinez');

INSERT INTO CustomerOrder (CustomerId, OrderDate)
VALUES
(3, CURDATE()),
(4, CURDATE());  


