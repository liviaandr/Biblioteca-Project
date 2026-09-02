package br.com.biblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVE_POSTGRES = "org.postgresql.Driver";
    private static final String ENDERECO = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "132574";

    public static Connection getDatabaseConnection() {

        try {
            Class.forName(DRIVE_POSTGRES);
            Connection conn
                    = DriverManager.getConnection(ENDERECO, USUARIO, SENHA);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {

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
