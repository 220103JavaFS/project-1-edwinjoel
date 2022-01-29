DROP TABLE IF EXISTS ers_reimbursement_status CASCADE;
DROP TABLE IF EXISTS ers_reimbursement_type CASCADE;
DROP TABLE IF EXISTS ers_user_roles CASCADE;
DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE IF EXISTS ers_reimbursement CASCADE;
--Inserting some data into ers_users for testing.

TRUNCATE ers_users CASCADE;
ALTER SEQUENCE ers_users_ers_user_id_seq RESTART WITH 1;
--TRUNCATE ers_reimbursement CASCADE;

--create roles for users.
INSERT INTO ers_user_roles (ers_user_role_id, user_role) VALUES
(1, 'MANAGER'),
(2, 'EMPLOYEE');

--Insert data into users.
INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES
('scardo', 'ab2912d3947ceaa37f79a96b22bb030a', 'Shara', 'Cardo' ,'scardo0@lycos.com', 1),
('avoas', '7d1149f287a50e78b52b2cf04be04549', 'Ardene', 'Voas' ,'avoas1@europa.eu', 2),
('vokroy', '3fe1c951edca8d4b2d387dd37b5b73b0', 'Valli', 'Okroy' ,'vokroy2@1688.com', 2),
('aambrogio', '30a14a517ebda498dc2a4cef2f9cb09e', 'Ashleigh', 'Ambrogio' ,'aambrogio3@nbcnews.com', 2),
('zsheldrake', 'f84aad61fdf4beb840456be3fd5d18c2', 'Zuzana', 'Sheldrake' ,'zsheldrake4@cbc.ca',2),
('gboyde', 'bd1551b8f1285459db08ad56322506e6', 'Gianni', 'Boyde' ,'gboyde5@webnode.com',2),
('marger', 'cd600f8a6110a433515c633536a2af12', 'Myrna', 'Arger' ,'marger6@over-blog.com',2),
('ucarleton', 'e15bfbb1d16a3f3eb93e6f739ad6dce7', 'Ulric', 'Carleton' ,'ucarleton7@dagondesign.com',2),
('asergeant', '5fc77bda6bb7bbd8fd1b91e44faf97bc', 'Almeria', 'Sergeant' ,'asergeant8@google.nl',2),
('sdiglin', '662bfa62c4e4cebe0b813eed48ca4820', 'Shayne', 'Diglin' ,'sdiglin9@google.com.br',2),
('hessbergera', 'ecc7a1d382b6b149e921629e1be3c868', 'Henrieta', 'Essberger' ,'hessbergera@cloudflare.com',2),
('emitchardb', 'f85317167ff637c4ffb8e17114c0f389', 'Edin', 'Mitchard' ,'emitchardb@so-net.ne.jp',2),
('tgrishmanovc', '0b082a33b6edd5c1315e4d4af83f1b16', 'Thibaud', 'Grishmanov' ,'tgrishmanovc@clickbank.net',2),
('cclossd', '300f24ec89e277040db6f584ac09776d', 'Chere', 'Closs' ,'cclossd@who.int',2),
('sbankhurste', '712250363dcbaba4750de4cb62d6c4f3', 'Stella', 'Bankhurst' ,'sbankhurste@rakuten.co.jp',2);

SELECT * FROM ers_users;


INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status) VALUES
			(1, 'PENDING'),
			(2, 'APPROVED'),
			(3, 'DENIED');
		
SELECT * FROM ers_reimbursement_status;

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type) VALUES
			(1, 'LODGING'),
			(2, 'TRAVEL'),
			(3, 'FOOD'),
			(4, 'OTHER');
		
SELECT * FROM ers_reimbursement_type;

--Insert status pending reimbursement
INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES
(17, TIMESTAMP '2011-05-16 15:21:38', 'Travel expenses' ,17, 1,2),
(23, TIMESTAMP '2011-06-17 14:56:38', 'Restaurant expenses' ,18, 1,3),
(35, TIMESTAMP '2011-07-18 16:06:38', 'Hotel expenses' ,19, 1,1),
(11, TIMESTAMP '2011-08-19 17:46:38', 'Entertaiment expenses' ,17, 1,4),
(18, TIMESTAMP '2011-09-20 13:16:38', 'Taxi expenses' ,18, 1,2),
(55, TIMESTAMP '2011-10-21 15:27:38', 'Others expenses' ,19, 1,4);

SELECT * FROM  ers_reimbursement;

--Insert status Approved reimbursement
INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES
(27, TIMESTAMP '2011-03-16 15:21:38', TIMESTAMP '2011-04-16 15:21:38', 'Travel expenses' , 16,20, 2,2),
(33, TIMESTAMP '2011-03-17 14:56:38', TIMESTAMP '2011-04-17 14:56:38', 'Restaurant expenses' , 16,21, 2,3),
(27, TIMESTAMP '2011-05-16 15:21:38', TIMESTAMP '2011-06-16 15:21:38', 'Travel expenses' , 16,22, 2,1),
(33, TIMESTAMP '2011-04-17 14:56:38', TIMESTAMP '2011-05-17 14:56:38', 'Restaurant expenses', 16,23, 3,2),
(45, TIMESTAMP '2011-04-21 15:27:38', TIMESTAMP '2011-05-21 15:27:38', 'Others expenses', 16,24, 3,2);
SELECT * FROM  ers_reimbursement;


---------TESTING QUERIES---------

SELECT ers_reimbursement.*, ers_reimbursement_type.*, ers_reimbursement_status.*, ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id;


-----VIEW ALL TICKETS BY EMPLOYEE--------------
SELECT  ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_author, ers_users.user_first_name, 
		ers_users.user_last_name, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status
FROM ers_reimbursement INNER JOIN ers_users ON ers_reimbursement.reimb_author = ers_users.ers_user_id 
INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
WHERE ers_reimbursement.reimb_author IN (16); --FOR USER WITH ID =16.

-----VIEW ALL TICKETS--------------
SELECT  ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_author, ers_users.user_first_name, 
		ers_users.user_last_name, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status
FROM ers_reimbursement INNER JOIN ers_users ON ers_reimbursement.reimb_author = ers_users.ers_user_id 
INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id;

-----VIEW ALL TICKETS BY STATUS--------------
SELECT  ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_author, ers_users.user_first_name, 
		ers_users.user_last_name, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status
FROM ers_reimbursement INNER JOIN ers_users ON ers_reimbursement.reimb_author = ers_users.ers_user_id 
INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
WHERE  ers_reimbursement.reimb_status_id IN (2); --FOR STATUS PENDING.
