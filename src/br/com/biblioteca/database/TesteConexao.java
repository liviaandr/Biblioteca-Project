package br.com.biblioteca.database;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conexao = DatabaseConnection.getDatabaseConnection()) {

            System.out.println("=================================");
            System.out.println("CONEXÃO REALIZADA COM SUCESSO!");
            System.out.println("Banco: " + conexao.getCatalog());
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("=================================");
            System.out.println("ERRO AO CONECTAR!");
            System.out.println("=================================");

            e.printStackTrace();
        }
    }
}