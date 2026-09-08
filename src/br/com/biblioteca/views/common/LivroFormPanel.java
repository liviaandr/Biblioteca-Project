package br.com.biblioteca.views.common;

import br.com.biblioteca.models.Autor;
import br.com.biblioteca.models.Livro;
import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class LivroFormPanel extends JPanel {
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtAutor = new JTextField();
    private final JTextField txtEditora = new JTextField();
    private final JTextField txtAno = new JTextField();
    private final JComboBox<String> cbCategoria = new JComboBox<>(new String[]{"Literatura", "Tecnologia", "Ciências", "História", "Filosofia", "Educação", "Outros"});
    private final JComboBox<String> cbDisponibilidade = new JComboBox<>(new String[]{"Disponível", "Indisponível"});

    public LivroFormPanel() {
        setLayout(new GridLayout(6, 2, 8, 8));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Autor:")); add(txtAutor);
        add(new JLabel("Editora:")); add(txtEditora);
        add(new JLabel("Ano de publicação:")); add(txtAno);
        add(new JLabel("Categoria:")); add(cbCategoria);
        add(new JLabel("Disponibilidade:")); add(cbDisponibilidade);
    }

    public Livro criarLivro() {
        String titulo = txtTitulo.getText().trim();
        String autorNome = txtAutor.getText().trim();
        String editora = txtEditora.getText().trim();
        String categoria = String.valueOf(cbCategoria.getSelectedItem());
        if (titulo.isEmpty()) throw new IllegalArgumentException("Informe o título.");
        if (autorNome.isEmpty()) throw new IllegalArgumentException("Informe o autor.");
        if (editora.isEmpty()) throw new IllegalArgumentException("Informe a editora.");
        int ano;
        try { ano = Integer.parseInt(txtAno.getText().trim()); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("O ano deve ser um número inteiro."); }
        int anoAtual = Year.now().getValue();
        if (ano < 1000 || ano > anoAtual) throw new IllegalArgumentException("Informe um ano entre 1000 e " + anoAtual + ".");
        return new Livro(0, titulo, new Autor(0, autorNome), editora, ano, categoria,
                "Disponível".equals(cbDisponibilidade.getSelectedItem()));
    }

    public void preencher(Livro livro) {
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor() == null ? "" : livro.getAutor().getNome());
        txtEditora.setText(livro.getEditora());
        txtAno.setText(String.valueOf(livro.getAnoPublicacao()));
        cbCategoria.setSelectedItem(livro.getCategoria());
        cbDisponibilidade.setSelectedItem(livro.isDisponivel() ? "Disponível" : "Indisponível");
    }

    public void limpar() {
        txtTitulo.setText(""); txtAutor.setText(""); txtEditora.setText(""); txtAno.setText("");
        cbCategoria.setSelectedIndex(0); cbDisponibilidade.setSelectedIndex(0); txtTitulo.requestFocus();
    }
}
