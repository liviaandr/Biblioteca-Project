package br.com.biblioteca.dao;

import br.com.biblioteca.database.DatabaseConnection;
import br.com.biblioteca.models.Autor;
import br.com.biblioteca.models.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO implements IPersistencia<Livro> {

    @Override
    public void inserir(Livro objeto) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO LIVRO (nome, autor_id, isbn, ano_publicacao, editora, genero) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getAutor().getId());
            stmt.setString(3, objeto.getIsbn());
            stmt.setInt(4, objeto.getAnoPublicacao());
            stmt.setString(5, objeto.getEditora());
            stmt.setString(6, objeto.getGenero());

            stmt.executeUpdate();

            System.out.println("Livro " + objeto.getNome() + " inserido com sucesso.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao tentar inserir informação no banco de dados.");

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }

    @Override
    public Livro consultar(String id) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT livro_id, nome, autor_id, isbn, ano_publicacao, editora, genero " +
                            "FROM LIVRO WHERE livro_id = ?");

            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int livroId = rs.getInt("livro_id");
                String nome = rs.getString("nome");
                int autorId = rs.getInt("autor_id");
                String isbn = rs.getString("isbn");
                int anoPublicacao = rs.getInt("ano_publicacao");
                String editora = rs.getString("editora");
                String genero = rs.getString("genero");

                AutorDAO autorDAO = new AutorDAO();
                Autor autor = autorDAO.consultar(String.valueOf(autorId));

                Livro livro = new Livro();

                livro.setId(livroId);
                livro.setNome(nome);
                livro.setAutor(autor);
                livro.setIsbn(isbn);
                livro.setAnoPublicacao(anoPublicacao);
                livro.setEditora(editora);
                livro.setGenero(genero);

                return livro;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }

        return null;
    }

    @Override
    public void alterar(String id, Livro objeto) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE LIVRO SET nome = ?, autor_id = ?, isbn = ?, " +
                            "ano_publicacao = ?, editora = ?, genero = ? WHERE livro_id = ?");

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getAutor().getId());
            stmt.setString(3, objeto.getIsbn());
            stmt.setInt(4, objeto.getAnoPublicacao());
            stmt.setString(5, objeto.getEditora());
            stmt.setString(6, objeto.getGenero());
            stmt.setString(7, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao alterar informação no banco de dados");

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }

    @Override
    public void excluir(String id) {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM LIVRO WHERE livro_id = ?");

            stmt.setString(1, id);
            stmt.executeUpdate();

            System.out.println("Livro " + id + " excluído com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao excluir informação no banco de dados");

        } finally {
            DatabaseConnection.closeConnection(con, stmt);
        }
    }
}
