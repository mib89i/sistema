
INSERT INTO grupo (id, nome) VALUES (1, 'Comum');
INSERT INTO grupo (id, nome) VALUES (2, 'Administrador');
SELECT setval('grupo_id_seq', max(id)) FROM grupo;

INSERT INTO usuario (id, administrador, senha, usuario, id_grupo) VALUES (1, TRUE, '123', 'claudemir', 2);
SELECT setval('usuario_id_seq', max(id)) FROM usuario;

INSERT INTO pessoa (id, nome, sobre_nome, documento, id_usuario, data_cadastro, hora_cadastro) 
   VALUES (1, 'Claudemir', 'Custódio', '', 1, CURRENT_DATE, EXTRACT(HOUR FROM CURRENT_TIME) || ':' || EXTRACT(MINUTE FROM CURRENT_TIME));
SELECT setval('pessoa_id_seq', max(id)) FROM pessoa;


INSERT INTO fornecedor (id, nome, cnpj) VALUES (1, 'RIBERGELO LDTA', null);
SELECT setval('fornecedor_id_seq', max(id)) FROM fornecedor;


INSERT INTO produto_fornecedor (id, nome) VALUES (1, 'CARVÃO');
INSERT INTO produto_fornecedor (id, nome) VALUES (2, 'GELO');
SELECT setval('produto_fornecedor_id_seq', max(id)) FROM produto_fornecedor;


INSERT INTO produto (id, nome, peso) VALUES (1, 'CARVÃO 5 kg', 5);
INSERT INTO produto (id, nome, peso) VALUES (2, 'CARVÃO 10 kg', 10);
INSERT INTO produto (id, nome, peso) VALUES (3, 'CARVÃO 50 kg', 50);
INSERT INTO produto (id, nome, peso) VALUES (4, 'GELO 5 kg', 5);
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

