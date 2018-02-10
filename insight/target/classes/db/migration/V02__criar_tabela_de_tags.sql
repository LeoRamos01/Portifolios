CREATE TABLE tag (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tagPortifolio (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	idTag BIGINT(20),
	idPortifolio BIGINT(20),
	
	CONSTRAINT FK_idTag FOREIGN KEY (idTag) REFERENCES tag(id),
	CONSTRAINT FK_idPortifolio FOREIGN KEY (idPortifolio) REFERENCES portifolio(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;