CREATE TABLE IF NOT EXISTS public.tab_usuario(
    id SERIAL PRIMARY KEY,
    login character varying(255),
    nome character varying(255),
    num_ocorrencias integer NOT NULL,
    role character varying(255),
    senha character varying(255)
);
