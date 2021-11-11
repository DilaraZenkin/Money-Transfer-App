
-- Creating Users ---
INSERT INTO users (user_id, username, password_hash)
VALUES (DEFAULT, 'Matt', 'javablue');

INSERT INTO users (user_id, username, password_hash)
VALUES (DEFAULT, 'Dilara', 'javablue');



-- Creating Accounts --
INSERT INTO accounts (account_id, user_id, balance)
VALUES (DEFAULT, 1001, 500.00);

INSERT INTO accounts (account_id, user_id, balance)
VALUES (DEFAULT, 1006, 1000.00);

                                 
-- Creating transfer:  default id   -  request (1)     - pending (1)        - from Matt    to Dilara, 200.00  
INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (DEFAULT, 1, 1, 2001, 2005, 200.00);
                                                                           -- 2002          2001
-- Creating transfer:  default id  -  send (2)          - approved (2)     - from Dilara  - To Matt, 303.15
INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (DEFAULT, 2, 2, 2005, 2001, 303.15);

-- Creating transfer:  default id  -  send (2)          - rejected (3)   - from Dilara  -To Matt,   200.00
INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (DEFAULT, 2, 2, 2005, 2001, 100.00);


