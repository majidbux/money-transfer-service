INSERT INTO bank (id, name, swift_code) VALUES
                 ('6886dde2-9161-471b-ade6-fa53ad3a692d', 'ABC', 'DEABC2343');


INSERT INTO account_type (id, type) VALUES
                          (1, 'Current'),
                          (2, 'Saving');


INSERT INTO currency (id, iso_code) VALUES
                     (1, 'EUR'),
                     (2, 'GBP');


INSERT INTO customer (id, first_name, last_name, middle_name, email) VALUES
                 ('3beb5a43-fd23-42c4-9289-e2ac70125f52', 'Majid', 'Ali', null, 'majid.ali@test.com'),
                 ('753afbfd-806d-4842-8534-0858eefdd87f', 'De', 'Jong', null, 'de.jong@test.com');



INSERT INTO account (id, bank_id, name, customer_id, current_balance, currency_id, status, type_id) VALUES
                     ('c1834642-2add-4b11-a0a6-decd09f4d237', '6886dde2-9161-471b-ade6-fa53ad3a692d', 'Majid Ali', '3beb5a43-fd23-42c4-9289-e2ac70125f52', 30000, 1, 1, 1),
                     ('d1834642-2add-4b11-a0a6-decd09f4d237', '6886dde2-9161-471b-ade6-fa53ad3a692d', 'DE Jong', '753afbfd-806d-4842-8534-0858eefdd87f', 20000, 1, 1, 1);