package br.com.biblioteca.models;

public class Livro {
    private String nome;
    private int livroId;
    private Autor autor;
    private String isbn;
    private int anoPublicacao;
    private String editora;
    private String genero;

    public Livro(String nome, int livroId, Autor autor, String isbn, int anoPublicacao,
                 String editora, String genero) {
        this.nome = nome;
        this.livroId = livroId;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.genero = genero;
    }

    public Livro() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return livroId;
    }

    public void setId(int livroId) {
        this.livroId = livroId;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
