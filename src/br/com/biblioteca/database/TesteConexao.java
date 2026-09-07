package br.com.biblioteca.database;
import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {
            Connection conexao = DatabaseConnection.getDatabaseConnection();

            System.out.println("CONEXÃO REALIZADA COM SUCESSO!");

            DatabaseConnection.closeConnection(conexao);

        } catch (Exception e) {

            System.out.println("ERRO AO CONECTAR COM O BANCO!");
            e.printStackTrace();
        }
    }
}
