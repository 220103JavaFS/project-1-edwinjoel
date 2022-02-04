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

-- Drop table

-- DROP TABLE public.ers_reimbursement_status;

CREATE TABLE public.ers_reimbursement_status (
	reimb_status_id int4 NOT NULL,
	reimb_status varchar(10) NULL,
	CONSTRAINT ers_reimbursement_status_pkey PRIMARY KEY (reimb_status_id)
);

-- Drop table

-- DROP TABLE public.ers_reimbursement_type;

CREATE TABLE public.ers_reimbursement_type (
	reimb_type_id int4 NOT NULL,
	reimb_type varchar(10) NULL,
	CONSTRAINT ers_reimbursement_type_pkey PRIMARY KEY (reimb_type_id)
);

-- Drop table

-- DROP TABLE public.ers_user_roles;

CREATE TABLE public.ers_user_roles (
	ers_user_role_id int4 NOT NULL,
	user_role varchar(10) NULL,
	CONSTRAINT ers_user_roles_pkey PRIMARY KEY (ers_user_role_id)
);

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

CREATE OR REPLACE FUNCTION public.getall()
 RETURNS TABLE(reimb_id integer, reimb_amount integer, reimb_submitted timestamp without time zone, reimb_resolved timestamp without time zone, reimb_description character varying, reimb_receipt bytea, reimb_author integer, reimb_resolver integer, reimb_status_id integer, reimb_type_id integer, reimb_type character varying, reimb_status character varying, ers_user_id integer, ers_username character varying, ers_password bytea, user_first_name character varying, user_last_name character varying, user_email character varying, user_role_id integer, user_role character varying, ers_user_id2 integer, ers_username2 character varying, ers_password2 bytea, user_first_name2 character varying, user_last_name2 character varying, user_email2 character varying, user_role_id2 integer, user_role2 character varying)
 LANGUAGE plpgsql
AS $function$
BEGIN
	
RETURN QUERY SELECT ers_reimbursement.*, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status , ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON ers_reimbursement.reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON ers_reimbursement.reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id;
	
END;
$function$
;

CREATE OR REPLACE FUNCTION public.getbyauthor(author integer)
 RETURNS TABLE(reimb_id integer, reimb_amount integer, reimb_submitted timestamp without time zone, reimb_resolved timestamp without time zone, reimb_description character varying, reimb_receipt bytea, reimb_author integer, reimb_resolver integer, reimb_status_id integer, reimb_type_id integer, reimb_type character varying, reimb_status character varying, ers_user_id integer, ers_username character varying, ers_password bytea, user_first_name character varying, user_last_name character varying, user_email character varying, user_role_id integer, user_role character varying, ers_user_id2 integer, ers_username2 character varying, ers_password2 bytea, user_first_name2 character varying, user_last_name2 character varying, user_email2 character varying, user_role_id2 integer, user_role2 character varying)
 LANGUAGE plpgsql
AS $function$
BEGIN
	
RETURN QUERY SELECT ers_reimbursement.*, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status , ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON ers_reimbursement.reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON ers_reimbursement.reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id
WHERE ers_reimbursement.reimb_author = author;
	
END;
$function$
;

CREATE OR REPLACE FUNCTION public.getbyid(id integer)
 RETURNS TABLE(reimb_id integer, reimb_amount integer, reimb_submitted timestamp without time zone, reimb_resolved timestamp without time zone, reimb_description character varying, reimb_receipt bytea, reimb_author integer, reimb_resolver integer, reimb_status_id integer, reimb_type_id integer, reimb_type character varying, reimb_status character varying, ers_user_id integer, ers_username character varying, ers_password bytea, user_first_name character varying, user_last_name character varying, user_email character varying, user_role_id integer, user_role character varying, ers_user_id2 integer, ers_username2 character varying, ers_password2 bytea, user_first_name2 character varying, user_last_name2 character varying, user_email2 character varying, user_role_id2 integer, user_role2 character varying)
 LANGUAGE plpgsql
AS $function$
BEGIN
	
RETURN QUERY SELECT ers_reimbursement.*, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status , ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON ers_reimbursement.reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON ers_reimbursement.reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id
WHERE ers_reimbursement.reimb_id = id;
	
END;
$function$
;

CREATE OR REPLACE FUNCTION public.getbyresolver(resolver integer)
 RETURNS TABLE(reimb_id integer, reimb_amount integer, reimb_submitted timestamp without time zone, reimb_resolved timestamp without time zone, reimb_description character varying, reimb_receipt bytea, reimb_author integer, reimb_resolver integer, reimb_status_id integer, reimb_type_id integer, reimb_type character varying, reimb_status character varying, ers_user_id integer, ers_username character varying, ers_password bytea, user_first_name character varying, user_last_name character varying, user_email character varying, user_role_id integer, user_role character varying, ers_user_id2 integer, ers_username2 character varying, ers_password2 bytea, user_first_name2 character varying, user_last_name2 character varying, user_email2 character varying, user_role_id2 integer, user_role2 character varying)
 LANGUAGE plpgsql
AS $function$
BEGIN
	
RETURN QUERY SELECT ers_reimbursement.*, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status , ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON ers_reimbursement.reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON ers_reimbursement.reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id
WHERE ers_reimbursement.reimb_resolver = resolver;
	
END;
$function$
;

CREATE OR REPLACE FUNCTION public.getbystatus(status character varying)
 RETURNS TABLE(reimb_id integer, reimb_amount integer, reimb_submitted timestamp without time zone, reimb_resolved timestamp without time zone, reimb_description character varying, reimb_receipt bytea, reimb_author integer, reimb_resolver integer, reimb_status_id integer, reimb_type_id integer, reimb_type character varying, reimb_status character varying, ers_user_id integer, ers_username character varying, ers_password bytea, user_first_name character varying, user_last_name character varying, user_email character varying, user_role_id integer, user_role character varying, ers_user_id2 integer, ers_username2 character varying, ers_password2 bytea, user_first_name2 character varying, user_last_name2 character varying, user_email2 character varying, user_role_id2 integer, user_role2 character varying)
 LANGUAGE plpgsql
AS $function$
BEGIN
	
RETURN QUERY SELECT ers_reimbursement.*, ers_reimbursement_type.reimb_type, ers_reimbursement_status.reimb_status , ers_users1.*, ers_user_roles1.user_role , ers_users2.ers_user_id AS ers_user_id2,
ers_users2.ers_username AS ers_username2, ers_users2.ers_password AS ers_password2, ers_users2.user_first_name AS user_first_name2, ers_users2.user_last_name AS user_last_name2,
ers_users2.user_email AS user_email2, ers_users2.user_role_id AS user_role_id2, ers_user_roles2.user_role AS user_role2
FROM ers_reimbursement INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id 
INNER JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
INNER JOIN ers_users AS ers_users1 ON ers_reimbursement.reimb_author = ers_users1.ers_user_id
INNER JOIN ers_user_roles AS ers_user_roles1 ON ers_users1.user_role_id = ers_user_roles1.ers_user_role_id
LEFT JOIN ers_users AS ers_users2 ON ers_reimbursement.reimb_resolver = ers_users2.ers_user_id
LEFT JOIN ers_user_roles AS ers_user_roles2 ON ers_users2.user_role_id = ers_user_roles2.ers_user_role_id
WHERE ers_reimbursement_status.reimb_status = status;
	
	
	
END;
$function$
;

-- DROP SEQUENCE public.ers_reimbursement_reimb_id_seq;

CREATE SEQUENCE public.ers_reimbursement_reimb_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.ers_reimbursement_reimb_id_seq1;

CREATE SEQUENCE public.ers_reimbursement_reimb_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.ers_users_ers_user_id_seq;

CREATE SEQUENCE public.ers_users_ers_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.ers_users_ers_user_id_seq1;

CREATE SEQUENCE public.ers_users_ers_user_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
