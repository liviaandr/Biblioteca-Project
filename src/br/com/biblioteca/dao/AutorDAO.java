package br.com.biblioteca.dao;

import br.com.biblioteca.database.DatabaseConnection;
import br.com.biblioteca.models.Autor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorDAO implements IPersistencia<Autor> {

    @Override
    public void inserir(Autor objeto) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO AUTOR (nome, data_nascimento) VALUES (?, ?)"
            );

            stmt.setString(1, objeto.getNome());
            stmt.setDate(2, Date.valueOf(objeto.getDataNascimento()));

            stmt.executeUpdate();

            System.out.println(
                    "Autor " + objeto.getNome() + " inserido com sucesso."
            );

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException(
                    "Erro ao tentar inserir informação no banco de dados."
            );

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }

    @Override
    public Autor consultar(String id) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(
                    "SELECT id, nome, data_nascimento " +
                            "FROM AUTOR WHERE id = ?"
            );

            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Autor autor = new Autor();

                autor.setId(rs.getInt("id"));
                autor.setNome(rs.getString("nome"));
                autor.setDataNascimento(
                        rs.getDate("data_nascimento").toLocalDate()
                );

                return autor;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }

        return null;
    }

    @Override
    public void alterar(String id, Autor objeto) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "UPDATE AUTOR " +
                            "SET nome = ?, data_nascimento = ? " +
                            "WHERE id = ?"
            );

            stmt.setString(1, objeto.getNome());
            stmt.setDate(2, Date.valueOf(objeto.getDataNascimento()));
            stmt.setString(3, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException(
                    "Erro ao alterar informação no banco de dados"
            );

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }

    @Override
    public void excluir(String id) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "DELETE FROM AUTOR WHERE id = ?"
            );

            stmt.setString(1, id);
            stmt.executeUpdate();

            System.out.println(
                    "Autor " + id + " excluído com sucesso"
            );

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException(
                    "Erro ao excluir informação no banco de dados"
            );

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }
}
