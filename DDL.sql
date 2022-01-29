-- public.ers_reimbursement_status definition

-- Drop table

-- DROP TABLE public.ers_reimbursement_status;

CREATE TABLE public.ers_reimbursement_status (
	reimb_status_id int4 NOT NULL,
	reimb_status varchar(10) NULL,
	CONSTRAINT ers_reimbursement_status_pkey PRIMARY KEY (reimb_status_id)
);


-- public.ers_reimbursement_type definition

-- Drop table

-- DROP TABLE public.ers_reimbursement_type;

CREATE TABLE public.ers_reimbursement_type (
	reimb_type_id int4 NOT NULL,
	reimb_type varchar(10) NULL,
	CONSTRAINT ers_reimbursement_type_pkey PRIMARY KEY (reimb_type_id)
);


-- public.ers_user_roles definition

-- Drop table

-- DROP TABLE public.ers_user_roles;

CREATE TABLE public.ers_user_roles (
	ers_user_role_id int4 NOT NULL,
	user_role varchar(10) NULL,
	CONSTRAINT ers_user_roles_pkey PRIMARY KEY (ers_user_role_id)
);


-- public.ers_reimbursement_reimb_id_seq definition

-- DROP SEQUENCE public.ers_reimbursement_reimb_id_seq;

CREATE SEQUENCE public.ers_reimbursement_reimb_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.ers_users_ers_user_id_seq definition

-- DROP SEQUENCE public.ers_users_ers_user_id_seq;

CREATE SEQUENCE public.ers_users_ers_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- public.ers_users definition

-- Drop table

-- DROP TABLE public.ers_users;

CREATE TABLE public.ers_users (
	ers_user_id serial4 NOT NULL,
	ers_username varchar(50) NULL,
	ers_password bytea NULL,
	user_first_name varchar(100) NULL,
	user_last_name varchar(100) NULL,
	user_email varchar(150) NULL,
	user_role_id int4 NULL,
	CONSTRAINT ers_user_pkey PRIMARY KEY (ers_user_id),
	CONSTRAINT ers_user_un UNIQUE (ers_username, user_email),
	CONSTRAINT ers_users_fk FOREIGN KEY (user_role_id) REFERENCES public.ers_user_roles(ers_user_role_id)
);


-- public.ers_reimbursement definition

-- Drop table

-- DROP TABLE public.ers_reimbursement;

CREATE TABLE public.ers_reimbursement (
	reimb_id serial4 NOT NULL,
	reimb_amount int4 NULL,
	reimb_submitted timestamp NULL,
	reimb_resolved timestamp NULL,
	reimb_description varchar(250) NULL,
	reimb_receipt bytea NULL,
	reimb_author int4 NULL,
	reimb_resolver int4 NULL,
	reimb_status_id int4 NULL,
	reimb_type_id int4 NULL,
	CONSTRAINT ers_reimbursement_pkey PRIMARY KEY (reimb_id),
	CONSTRAINT ers_reimbursement_fk FOREIGN KEY (reimb_status_id) REFERENCES public.ers_reimbursement_status(reimb_status_id),
	CONSTRAINT ers_reimbursement_fk2 FOREIGN KEY (reimb_type_id) REFERENCES public.ers_reimbursement_type(reimb_type_id),
	CONSTRAINT ers_reimbursement_fk3 FOREIGN KEY (reimb_author) REFERENCES public.ers_users(ers_user_id),
	CONSTRAINT ers_reimbursement_fk4 FOREIGN KEY (reimb_resolver) REFERENCES public.ers_users(ers_user_id)
);


