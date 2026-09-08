package br.com.biblioteca.dao;

import br.com.biblioteca.database.DatabaseConnection;
import br.com.biblioteca.models.Autor;
import br.com.biblioteca.models.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements IPersistencia<Livro> {
    private static final String BASE_SELECT =
            "SELECT l.livro_id, l.titulo, l.autor_id, a.nome AS autor, " +
            "l.editora, l.ano_publicacao, l.categoria, l.disponivel " +
            "FROM livro l INNER JOIN autor a ON a.id = l.autor_id ";

    @Override
    public void inserir(Livro livro) {
        validarLivro(livro);
        Autor autor = new AutorDAO().obterOuCriar(livro.getAutor().getNome());
        livro.setAutor(autor);
        String sql = "INSERT INTO livro (titulo, autor_id, editora, ano_publicacao, categoria, disponivel) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(stmt, livro);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) livro.setCodigo(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível cadastrar o livro.", ex);
        }
    }

    public List<Livro> listar() {
        return consultarLista(BASE_SELECT + "ORDER BY l.livro_id");
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return consultarLista(BASE_SELECT + "WHERE LOWER(l.titulo) LIKE LOWER(?) ORDER BY l.titulo", "%" + titulo + "%");
    }

    public List<Livro> buscarPorAutor(String autor) {
        return consultarLista(BASE_SELECT + "WHERE LOWER(a.nome) LIKE LOWER(?) ORDER BY l.titulo", "%" + autor + "%");
    }

    public List<Livro> buscarPorCodigo(int codigo) {
        return consultarLista(BASE_SELECT + "WHERE l.livro_id = ?", codigo);
    }

    private List<Livro> consultarLista(String sql, Object... parametros) {
        List<Livro> livros = new ArrayList<>();
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                if (parametros[i] instanceof Integer) stmt.setInt(i + 1, (Integer) parametros[i]);
                else stmt.setString(i + 1, String.valueOf(parametros[i]));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) livros.add(mapear(rs));
            }
            return livros;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível consultar os livros.", ex);
        }
    }

    @Override
    public Livro consultar(String id) {
        try {
            List<Livro> encontrados = buscarPorCodigo(Integer.parseInt(id));
            return encontrados.isEmpty() ? null : encontrados.get(0);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public void alterar(String id, Livro livro) {
        validarLivro(livro);
        Autor autor = new AutorDAO().obterOuCriar(livro.getAutor().getNome());
        livro.setAutor(autor);
        String sql = "UPDATE livro SET titulo = ?, autor_id = ?, editora = ?, ano_publicacao = ?, categoria = ?, disponivel = ? WHERE livro_id = ?";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            preencherStatement(stmt, livro);
            stmt.setInt(7, Integer.parseInt(id));
            if (stmt.executeUpdate() == 0) throw new RuntimeException("Livro não encontrado para alteração.");
        } catch (SQLException | NumberFormatException ex) {
            throw new RuntimeException("Não foi possível alterar o livro.", ex);
        }
    }

    @Override
    public void excluir(String id) {
        String sql = "DELETE FROM livro WHERE livro_id = ?";
        try (Connection con = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            if (stmt.executeUpdate() == 0) throw new RuntimeException("Livro não encontrado para exclusão.");
        } catch (SQLException | NumberFormatException ex) {
            throw new RuntimeException("Não foi possível excluir o livro.", ex);
        }
    }

    private void preencherStatement(PreparedStatement stmt, Livro livro) throws SQLException {
        stmt.setString(1, livro.getTitulo());
        stmt.setInt(2, livro.getAutor().getId());
        stmt.setString(3, livro.getEditora());
        stmt.setInt(4, livro.getAnoPublicacao());
        stmt.setString(5, livro.getCategoria());
        stmt.setBoolean(6, livro.isDisponivel());
    }

    private Livro mapear(ResultSet rs) throws SQLException {
        Autor autor = new Autor(rs.getInt("autor_id"), rs.getString("autor"));
        return new Livro(rs.getInt("livro_id"), rs.getString("titulo"), autor,
                rs.getString("editora"), rs.getInt("ano_publicacao"),
                rs.getString("categoria"), rs.getBoolean("disponivel"));
    }

    private void validarLivro(Livro livro) {
        if (livro == null || livro.getTitulo() == null || livro.getTitulo().isBlank())
            throw new IllegalArgumentException("O título é obrigatório.");
        if (livro.getAutor() == null || livro.getAutor().getNome() == null || livro.getAutor().getNome().isBlank())
            throw new IllegalArgumentException("O autor é obrigatório.");
        if (livro.getAnoPublicacao() <= 0) throw new IllegalArgumentException("O ano de publicação deve ser válido.");
        if (livro.getEditora() == null || livro.getEditora().isBlank()) throw new IllegalArgumentException("A editora é obrigatória.");
        if (livro.getCategoria() == null || livro.getCategoria().isBlank()) throw new IllegalArgumentException("A categoria é obrigatória.");
    }
}
