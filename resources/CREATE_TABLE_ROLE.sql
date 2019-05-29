CREATE TABLE public."ROLE"
(
    id integer NOT NULL,
    name enum_name,
    description text COLLATE pg_catalog."default",
    CONSTRAINT "ROLE_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ROLE"
    OWNER to postgres;