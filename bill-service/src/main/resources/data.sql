INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (1, 1, '海底撈', 1, 5000);
INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (1, 2, '享溫馨', 1, 5389);

INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (2, 1, '老乾杯', 1, 13890);
INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (2, 2, '餅乾', 1, 68);
INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (2, 3, '飲料', 1, 500);

INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (3, 4, '滷肉飯', 1, 160);
INSERT INTO bill(`party_id`, `member_id`, `bill_name`, `type`, `total_amount`) 
VALUES (3, 5, '雜支', 1, 33);


INSERT INTO bill_detail(`bill_id`, `member_id`, `bill_detail_name`, `bill_type`, `amount`)
VALUES (1, 1, 'Po哥', 1, 4000);
INSERT INTO bill_detail(`bill_id`, `member_id`, `bill_detail_name`, `bill_type`, `amount`)
VALUES (1, 1, 'Jimmy', 0, 1000);
INSERT INTO bill_detail(`bill_id`, `member_id`, `bill_detail_name`, `bill_type`, `amount`)
VALUES (1, 1, 'Andy', 0, 1000);
INSERT INTO bill_detail(`bill_id`, `member_id`, `bill_detail_name`, `bill_type`, `amount`)
VALUES (1, 1, 'Simon', 0, 1000);
INSERT INTO bill_detail(`bill_id`, `member_id`, `bill_detail_name`, `bill_type`, `amount`)
VALUES (1, 1, 'Roy小帥哥', 0, 1000);