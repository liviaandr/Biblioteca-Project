package br.com.biblioteca.models;

import java.time.LocalDate;

public record Autor(String nome, LocalDate dataNascimento, String email){
}
