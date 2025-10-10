use code_along;

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    account_id INT PRIMARY KEY,
    account_name VARCHAR(100),
    balance DECIMAL(10, 2)
);

INSERT INTO accounts (account_id, account_name, balance) VALUES
(1, 'Alice', 1000.00),
(2, 'Bob', 500.00);

SELECT * FROM accounts;

SET AUTOCOMMIT = 0;

-- Start a new transaction
START TRANSACTION;

-- Deduct money from account 1
UPDATE accounts
SET balance = balance - 100
WHERE account_id = 1;

-- Add money to account 2
UPDATE accounts
SET balance = balance + 100
WHERE account_id = 2;

-- Check if the balances are now consistent (optional, for verification)
SELECT * FROM accounts WHERE account_id IN (1, 2);

-- If both updates were successful, commit the transaction to make changes permanent
-- COMMIT;

-- If an error occurred or the transfer needs to be cancelled, roll back the transaction
ROLLBACK;

SELECT * FROM accounts;

SET AUTOCOMMIT = 1;


