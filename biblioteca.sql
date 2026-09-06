-- Banco de dados da Biblioteca
-- PostgreSQL
-- Schema alinhado aos modelos Autor.java e Livro.java e aos DAOs atuais.

DROP VIEW IF EXISTS vw_livros;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS autor;

CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE livro (
    livro_id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    autor_id INTEGER NOT NULL,
    isbn VARCHAR(20),
    ano_publicacao INTEGER,
    editora VARCHAR(100),
    genero VARCHAR(50),

    CONSTRAINT fk_livro_autor
        FOREIGN KEY (autor_id)
        REFERENCES autor(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE INDEX idx_livro_autor_id ON livro(autor_id);

CREATE VIEW vw_livros AS
SELECT
    l.livro_id,
    l.nome,
    l.autor_id,
    a.nome AS autor,
    l.isbn,
    l.ano_publicacao,
    l.editora,
    l.genero
FROM livro l
INNER JOIN autor a ON a.id = l.autor_id;

-- Dados de exemplo (opcionais)
-- INSERT INTO autor (nome, data_nascimento)
-- VALUES ('Machado de Assis', '1839-06-21');

-- INSERT INTO livro (nome, autor_id, isbn, ano_publicacao, editora, genero)
-- VALUES ('Dom Casmurro', 1, '9780000000000', 1899, 'Exemplo Editora', 'Romance');
