package br.com.biblioteca.dao;

import br.com.biblioteca.database.DatabaseConnection;
import br.com.biblioteca.models.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO implements IPersistencia<Autor> {
    @Override
    public void inserir(Autor autor) {
        String sql = "INSERT INTO autor (nome) VALUES (?)";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) autor.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível cadastrar o autor.", ex);
        }
    }

    public Autor buscarPorNome(String nome) {
        String sql = "SELECT id, nome FROM autor WHERE LOWER(nome) = LOWER(?) LIMIT 1";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return new Autor(rs.getInt("id"), rs.getString("nome"));
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível consultar o autor.", ex);
        }
    }

    public Autor obterOuCriar(String nome) {
        Autor autor = buscarPorNome(nome);
        if (autor != null) return autor;
        autor = new Autor(0, nome);
        inserir(autor);
        return autor;
    }

    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT id, nome FROM autor ORDER BY nome";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) autores.add(new Autor(rs.getInt("id"), rs.getString("nome")));
            return autores;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível listar os autores.", ex);
        }
    }

    @Override
    public Autor consultar(String id) {
        try {
            return buscarPorId(Integer.parseInt(id));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Autor buscarPorId(int id) {
        String sql = "SELECT id, nome FROM autor WHERE id = ?";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return new Autor(rs.getInt("id"), rs.getString("nome"));
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível consultar o autor.", ex);
        }
    }

    @Override
    public void alterar(String id, Autor objeto) {
        String sql = "UPDATE autor SET nome = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException | NumberFormatException ex) {
            throw new RuntimeException("Não foi possível alterar o autor.", ex);
        }
    }

    @Override
    public void excluir(String id) {
        String sql = "DELETE FROM autor WHERE id = ?";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException | NumberFormatException ex) {
            throw new RuntimeException("Não foi possível excluir o autor.", ex);
        }
    }
}
