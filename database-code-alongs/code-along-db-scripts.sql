-- DDL
USE sys;

DROP DATABASE IF EXISTS EShopping;

CREATE DATABASE IF NOT EXISTS EShopping;

USE EShopping;


CREATE TABLE Products (
	ProductId INT PRIMARY KEY AUTO_INCREMENT,
	ProductName VARCHAR(50) NOT NULL,
    Cost Decimal(13,2)
);

CREATE TABLE Customers (
	CustomerId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE ORDERS (
	OrderId INT Primary Key AUTO_INCREMENT,
    CustomerId INT NOT NULL,
    OrderDate DATETIME,
    CONSTRAINT FK_ORDERS_CUSTOMERS_Customer_Id FOREIGN KEY 
		(CustomerId) REFERENCES Customers(CustomerId)
);

create table LineItems (
	LineItemId int primary key auto_increment,
    OrderId int not null,
    ProductId int not null,
    Quantity int not null,
    Constraint FK_LineItems_Orders_OrderId foreign key
		(OrderId) references Orders(OrderId),
	Constraint FK_LineItems_Products_ProductId foreign key
		(ProductId) references Products(ProductId),
	Constraint UC_LineItem unique (OrderId,ProductId)
);




