use code_along;
-- Standard Insert
INSERT INTO payment (PaymentId, PaymentMethodId, OrderId, Amount)
VALUES (1001, 1, 101, 49.99);

-- Without all Columns
INSERT INTO payment (PaymentId, PaymentMethodId, OrderId)
VALUES (1002, 1, 101);

-- Not Recccomended But Still Works
INSERT INTO payment
VALUES (1003, 2, 102, 5.00);

-- OMIT Payment ID
INSERT INTO payment (PaymentMethodId, OrderId, Amount)
VALUES (1, 101, 49.99);

-- OMIT Payment ID Returning Incremented ID
INSERT INTO payment (PaymentMethodId, OrderId, Amount)
VALUES (1, 102, 49.99);
SELECT LAST_INSERT_ID(); -- Returning

-- This will fail due to FK From Payment to Payment Method
INSERT INTO payment (PaymentMethodId, OrderId, Amount)
VALUES (999, 102, 49.99);

-- Insert Multiple Rows At Once
INSERT INTO payment (PaymentMethodId, OrderId, Amount)
VALUES
(1, 101, 10.00),
(2, 102, 10.00),
(3, 101, 20.00);

-- INSERT FROM A SELECT STATEMENT
INSERT INTO payment (PaymentMethodId, OrderId, Amount)
(SELECT 1, OrderID, 5.00
FROM CustomerOrder);

-- Denormalize
use code_along;

DROP TABLE IF EXISTS flatPaymentData;

CREATE TABLE flatPaymentData AS 
SELECT payment.paymentId,
	   payment.paymentMethodId,
	   paymentMethod.MethodName,
       payment.OrderID,
       customerorder.CustomerName,
       payment.Amount
FROM 
	payment
INNER JOIN customerorder on payment.orderID = customerorder.OrderID
INNER JOIN paymentmethod on payment.PaymentMethodId = paymentmethod.PaymentMethodId;