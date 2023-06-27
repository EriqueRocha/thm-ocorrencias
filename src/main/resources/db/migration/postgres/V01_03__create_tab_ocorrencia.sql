CREATE TABLE IF NOT EXISTS public.tab_ocorrencia(
    id SERIAL PRIMARY KEY,
    classificacao VARCHAR(255),
    data timestamp without time zone,
    endereco character varying(255),
    intensidade VARCHAR(255),
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES tab_usuario (id)
);


