CREATE TYPE public.enum_name AS ENUM
    ('Administration', 'Clients', 'Billing');

ALTER TYPE public.enum_name
    OWNER TO postgres;