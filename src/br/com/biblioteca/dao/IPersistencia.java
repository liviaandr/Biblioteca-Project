package br.com.biblioteca.dao;

public interface IPersistencia<T> {
    void inserir(T objeto);
    T consultar(String id);
    void alterar(String id, T objeto);
    void excluir(String id);
}
