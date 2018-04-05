
INSERT INTO grupo (id, nome) VALUES (1, 'Comum');
INSERT INTO grupo (id, nome) VALUES (2, 'Administrador');
SELECT setval('grupo_id_seq', max(id)) FROM grupo;

INSERT INTO usuario (id, administrador, senha, usuario, id_grupo) VALUES (1, TRUE, '123', 'claudemir', 2);
SELECT setval('usuario_id_seq', max(id)) FROM usuario;

INSERT INTO pessoa (id, nome, sobre_nome, documento, id_usuario, data_cadastro, hora_cadastro) 
   VALUES (1, 'Claudemir', 'Custódio', '', 1, CURRENT_DATE, EXTRACT(HOUR FROM CURRENT_TIME) || ':' || EXTRACT(MINUTE FROM CURRENT_TIME));
SELECT setval('pessoa_id_seq', max(id)) FROM pessoa;


INSERT INTO fornecedor (id, nome) VALUES (1, 'RIBERGELO LDTA');
SELECT setval('fornecedor_id_seq', max(id)) FROM fornecedor;


INSERT INTO medida (id, nome, sigla) VALUES (1, 'PESO', 'Kg');
INSERT INTO medida (id, nome, sigla) VALUES (2, 'QUANTIDADE', 'Qnt');
SELECT setval('medida_id_seq', max(id)) FROM medida;


INSERT INTO material (id, nome, id_medida) VALUES (1, 'Carvão', 1);
INSERT INTO material (id, nome, id_medida) VALUES (2, 'Gelo', 1);
INSERT INTO material (id, nome, id_medida) VALUES (3, 'Pacote de gelo 5Kg', 2);
INSERT INTO material (id, nome, id_medida) VALUES (4, 'Pacote de gelo 10Kg', 2);
INSERT INTO material (id, nome, id_medida) VALUES (5, 'Pacote de carvão 5Kg', 2);
INSERT INTO material (id, nome, id_medida) VALUES (6, 'Pacote de carvão 50Kg', 2);
SELECT setval('material_id_seq', max(id)) FROM material;


INSERT INTO produto (id, nome, id_medida) VALUES (1, 'Carvão 5 kg', 1);
INSERT INTO produto (id, nome, id_medida) VALUES (2, 'Carvão 10 kg', 1);
INSERT INTO produto (id, nome, id_medida) VALUES (3, 'Carvão 50 kg', 1);
INSERT INTO produto (id, nome, id_medida) VALUES (4, 'Gelo 5 kg', 1);
SELECT setval('produto_id_seq', max(id)) FROM produto;


CREATE OR REPLACE FUNCTION TRANSLATE_STRING(
	character varying)
RETURNS character varying
    LANGUAGE 'sql'
    COST 100
AS $BODY$

SELECT * from TRANSLATE($1, 
'áéíóúàèìòùãõâêîôôäëïöüçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÄËÏÖÜÇ','aeiouaeiouaoaeiooaeioucAEIOUAEIOUAOAEIOOAEIOUC'); 

$BODY$;

ALTER FUNCTION TRANSLATE_STRING(character varying)
    OWNER TO postgres;

