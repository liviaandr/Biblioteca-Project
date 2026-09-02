package br.com.biblioteca.models;

import java.time.LocalDate;

public class Autor {
    private String nome;
    private int autorId;
    private LocalDate dataNascimento;

    public Autor(String nome, int autorId, LocalDate dataNascimento) {
        this.nome = nome;
        this.autorId = autorId;
        this.dataNascimento = dataNascimento;
    }

    public Autor() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return autorId;
    }

    public void setId(int autorId) {
        this.autorId = autorId;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
