CREATE TABLE Endereco(
	id SERIAL,
	pais TEXT,
	estado TEXT,
	cidade TEXT,
	bairro TEXT,
	rua TEXT,
	numero INT,
	PRIMARY KEY(id)
);

CREATE TABLE PESSOA(
	cpf VARCHAR(14) NOT NULL,
	nome TEXT NOT NULL,
	dataNascimento DATE NOT NULL,
	idEndereco INT NOT NULL,
	PRIMARY KEY(cpf),
	FOREIGN KEY(idEndereco) REFERENCES ENDERECO(id)
);

CREATE TABLE TIPOUSUARIO(
	id INT NOT NULL,
	tipo TEXT,
	PRIMARY KEY(id)
);

INSERT INTO TIPOUSUARIO VALUES(1,"BIBLIOTECÁRIO");

CREATE TABLE USUARIO(
	matricula VARCHAR(20) NOT NULL,
	senha TEXT,
	idTipoUsuario INT NOT NULL,
	cpfPessoa VARCHAR(14) NOT NULL,
	PRIMARY KEY(matricula),
	FOREIGN KEY(cpfPessoa) REFERENCES PESSOA(cpf),
	FOREIGN KEY(idTipoUsuario) REFERENCES TIPOUSUARIO(id)
);

CREATE TABLE ALUNO(
	matricula TEXT NOT NULL,
	email TEXT UNIQUE NOT NULL,
	idEstado INT NOT NULL,
	cpfPessoa VARCHAR(14) NOT NULL,
	FOREIGN KEY(cpfPessoa) REFERENCES Pessoa(cpf),
	PRIMARY KEY(matricula)
);

CREATE TABLE LIVRO(
	isbn BIGINT NOT NULL,
	titulo TEXT,
	autor TEXT,
	descricao TEXT,
	estoque INT NOT NULL DEAFULT 0,
	PRIMARY KEY(isbn)
);

CREATE TABLE EMPRESTIMO(
	id SERIAL,
	alunoMatricula TEXT NOT NULL,
	livroIsbn BIGINT NOT NULL,
	dataInicio DATE NOT NULL,
	dataFim DATE NOT NULL,
	dataEntrega DATE,
	idEstado INTEGER NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(alunoMatricula) REFERENCES aluno(matricula),
	FOREIGN KEY(livroIsbn) REFERENCES livro(isbn)
);

CREATE TABLE BLOQUEIO(
	id SERIAL,
	alunoMatricula TEXT NOT NULL,
	dataInicio DATE NOT NULL,
	dataFim DATE NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(alunoMatricula) REFERENCES aluno(matricula)
);

CREATE TABLE emprestimos_notificados(
	emprestimoId INT NOT NULL,
	datetime timestamp NOT NULL,
	FOREIGN KEY(emprestimoId) REFERENCES emprestimo(id),
	primary key(emprestimoId,datetime)
);

-- TRIGGERS

CREATE TRIGGER deleteUserAccount
AFTER DELETE ON usuario
EXECUTE PROCEDURE removePessoa();

CREATE OR REPLACE FUNCTION removePessoa() RETURNS TRIGGER AS'
BEGIN
    DELETE FROM pessoa WHERE cpf = OLD.cpfPessoa;
    RETURN OLD;
END'
LANGUAGE plpgsql;
