package br.com.biblioteca.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {
    private static final String ARQUIVO_CONFIG = "database.properties";

    private DatabaseConnection() { }

    public static Connection getDatabaseConnection() {
        Properties propriedades = new Properties();
        try (FileInputStream arquivo = new FileInputStream(ARQUIVO_CONFIG)) {
            propriedades.load(arquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo database.properties.", ex);
        }

        String driver = propriedades.getProperty("driver");
        String endereco = propriedades.getProperty("endereco");
        String usuario = propriedades.getProperty("usuario");
        String senha = propriedades.getProperty("senha");

        if (driver == null || endereco == null || usuario == null || senha == null) {
            throw new RuntimeException("Configuração do banco incompleta no database.properties.");
        }

        try {
            Class.forName(driver);
            return DriverManager.getConnection(endereco, usuario, senha);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado. Adicione o postgresql JDBC ao classpath.", ex);
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao PostgreSQL: " + ex.getMessage(), ex);
        }
    }
}
