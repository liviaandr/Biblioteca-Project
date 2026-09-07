package br.com.biblioteca.dao;

import br.com.biblioteca.models.Livro;

public interface IPersistencia<T> {
    void inserir(T objeto);
    T consultar(int id);
    void alterar(int id, T objeto);
    void excluir(int id);
}
