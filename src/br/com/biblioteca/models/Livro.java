package br.com.biblioteca.models;

public record Livro(String nome, String isbn, int anoPublicacao, String editora,
                    String genero) {
}
