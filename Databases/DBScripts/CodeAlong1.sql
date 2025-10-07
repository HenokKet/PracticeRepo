USE sys; 

Drop database if exists EShopping;

Create database if not exists EShopping;

Use Eshopping;

DROP TABLE IF EXISTS Products; 
 
create table if not exists Products(
			ProductID int primary key auto_increment,
            ProductName VARCHAR(50) NOT NULL,
			Cost Decimal(13,2)
);

create table Customers(
			CustomerID int primary key auto_increment,
            FirstName VARCHAR(50) NOT NULL,
			LastName VARCHAR(50) NOT NULL
);

create table Orders(
			OrderID int primary key auto_increment,
            CustomerID INT NOT NULL,
			OrderDate DATETIME,
            constraint FK_ORDERS_CUSTOMERS_Customer_ID foreign key
            (CustomerID) references Customers(CustomerID)
);

create table LineItems(
	LineItemID int primary key auto_increment,
    OrderID int not null, 
    ProductId int not null, 
    Quantity int not null, 
    constraint FK_LineItems_Orders_OrderID foreign key
    (OrderId) references Orders(OrderId),
    constraint FK_LineItems_Products_ProductsId foreign key
    (ProductId) references Products(ProductId),
    constraint UC_LineItem unique (OrderID,ProductID)
);