CREATE TABLE public."USER_ROLE"
(
    id integer NOT NULL,
    user_id integer,
    role_id integer,
    CONSTRAINT "USER_ROLE_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."USER_ROLE"
    OWNER to postgres;