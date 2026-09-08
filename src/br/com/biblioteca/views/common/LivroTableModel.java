package br.com.biblioteca.views.common;

import br.com.biblioteca.models.Livro;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LivroTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Título", "Autor", "Editora", "Ano", "Genero", "Disponibilidade"};
    private List<Livro> livros = new ArrayList<>();

    public void setLivros(List<Livro> livros) {
        this.livros = livros == null ? new ArrayList<>() : new ArrayList<>(livros);
        fireTableDataChanged();
    }
    public Livro getLivroAt(int row) { return row >= 0 && row < livros.size() ? livros.get(row) : null; }
    public int getRowCount() { return livros.size(); }
    public int getColumnCount() { return colunas.length; }
    public String getColumnName(int column) { return colunas[column]; }
    public Object getValueAt(int row, int column) {
        Livro l = livros.get(row);
        return switch (column) {
            case 0 -> l.getCodigo();
            case 1 -> l.getTitulo();
            case 2 -> l.getAutor() == null ? "" : l.getAutor().getNome();
            case 3 -> l.getEditora();
            case 4 -> l.getAnoPublicacao();
            case 5 -> l.getGenero();
            case 6 -> l.isDisponivel() ? "Disponível" : "Indisponível";
            default -> "";
        };
    }
}
