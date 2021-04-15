CREATE TABLE public.bottles
(
    id uuid NOT NULL,
    creatorId uuid NOT NULL,
    message character varying(2048) NOT NULL,
    viewed boolean NOT NULL ,
    CONSTRAINT emptest4_pkey PRIMARY KEY (id)
);
COMMIT;

CREATE INDEX index_name
    ON public.bottles (creatorId, viewed);
COMMIT;

CREATE ROLE bottle_user NOSUPERUSER CREATEDB NOCREATEROLE NOINHERIT LOGIN PASSWORD 'password';
GRANT SELECT ON public.bottles TO bottle_user;
GRANT INSERT ON public.bottles TO bottle_user;
GRANT UPDATE ON public.bottles TO bottle_user;
COMMIT;