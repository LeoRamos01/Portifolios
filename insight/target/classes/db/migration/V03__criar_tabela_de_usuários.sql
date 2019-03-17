CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL,
	role VARCHAR(20) NOT NULL,
	hash varchar(30)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, senha, role, hash) VALUES ('anderson', 'YW5kZXJzb24=', 'ADMIN', '');

ALTER TABLE portifolio MODIFY descricao VARCHAR(3000);