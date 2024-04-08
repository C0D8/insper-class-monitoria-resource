CREATE TABLE monitoria
(
    id_monitoria character varying(36) NOT NULL,
    tx_name character varying(256),
    tx_description character varying(1024),
    dt_start character varying(256),
    duration character varying(256),
    id_professor character varying(36) NOT NULL,
    CONSTRAINT monitoria_pkey PRIMARY KEY (id_monitoria)
);