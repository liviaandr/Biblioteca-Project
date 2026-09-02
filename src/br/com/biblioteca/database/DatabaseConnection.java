package br.com.biblioteca.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String ARQUIVO_CONFIG = "database.properties";

    public static Connection getDatabaseConnection() {
        Properties propriedades = new Properties();

        try (FileInputStream arquivo = new FileInputStream(ARQUIVO_CONFIG)) {
            propriedades.load(arquivo);

            String driver = propriedades.getProperty("driver");
            String endereco = propriedades.getProperty("endereco");
            String usuario = propriedades.getProperty("usuario");
            String senha = propriedades.getProperty("senha");

            Class.forName(driver);
            Connection conn
                    = DriverManager.getConnection(endereco, usuario, senha);
            return conn;

        } catch (IOException | ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

            throw new RuntimeException("Erro ao estabelecer uma conexao com o banco");
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }
}
