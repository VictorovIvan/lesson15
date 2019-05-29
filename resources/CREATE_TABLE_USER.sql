CREATE TABLE public."USER"
(
    id integer,
    name text COLLATE pg_catalog."default",
    birthday date,
    login_id integer NOT NULL,
    city text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    CONSTRAINT "USER_pkey" PRIMARY KEY (login_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."USER"
    OWNER to postgres;