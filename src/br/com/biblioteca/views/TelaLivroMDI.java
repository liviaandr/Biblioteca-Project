package br.com.biblioteca.views;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Autor;
import br.com.biblioteca.models.Livro;

import javax.swing.*;
import java.awt.*;

public class TelaLivroMDI extends JInternalFrame {

    private JTextField txtId = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtAutorId = new JTextField();
    private JTextField txtIsbn = new JTextField();
    private JTextField txtAno = new JTextField();
    private JTextField txtEditora = new JTextField();
    private JTextField txtGenero = new JTextField();

    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnExcluir = new JButton("Excluir");

    private LivroDAO livroDAO = new LivroDAO();

    public TelaLivroMDI() {
        super("Gerenciamento de Livros - MDI", true, true, true, true);
        setSize(420, 350);
        setLayout(new GridLayout(10, 2, 5, 5));

        add(new JLabel(" ID (Busca/Alt/Excl):"));
        add(txtId);

        add(new JLabel(" Título:"));
        add(txtNome);

        add(new JLabel(" ID do Autor:"));
        add(txtAutorId);

        add(new JLabel(" ISBN:"));
        add(txtIsbn);

        add(new JLabel(" Ano Publicação:"));
        add(txtAno);

        add(new JLabel(" Editora:"));
        add(txtEditora);

        add(new JLabel(" Gênero:"));
        add(txtGenero);

        add(btnSalvar);
        add(btnBuscar);
        add(btnAtualizar);
        add(btnExcluir);

        btnSalvar.addActionListener(e -> {
            try {
                Livro livro = montarObjetoLivro();
                livroDAO.inserir(livro);
                JOptionPane.showMessageDialog(this, "Livro inserido com sucesso!");
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se 'ID do Autor' e 'Ano' são números válidos.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Livro livro = livroDAO.consultar(id);

                if (livro != null) {
                    txtNome.setText(livro.getNome());
                    if (livro.getAutor() != null) {
                        txtAutorId.setText(String.valueOf(livro.getAutor().getId()));
                    }
                    txtIsbn.setText(livro.getIsbn());
                    txtAno.setText(String.valueOf(livro.getAnoPublicacao()));
                    txtEditora.setText(livro.getEditora());
                    txtGenero.setText(livro.getGenero());
                } else {
                    JOptionPane.showMessageDialog(this, "Livro não encontrado para o ID informado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido (numérico) para buscar.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
            }
        });

        btnAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Livro livro = montarObjetoLivro();

                livroDAO.alterar(id, livro);
                JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID de Livro e ID de Autor válidos.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirmacao = JOptionPane.showConfirmDialog(
                        this,
                        "Deseja realmente excluir o livro de ID " + id + "?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    livroDAO.excluir(id);
                    JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");
                    limparCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido para excluir.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        });
    }

    private Livro montarObjetoLivro() {
        Livro livro = new Livro();
        livro.setNome(txtNome.getText());

        Autor autor = new Autor();
        autor.setId(Integer.parseInt(txtAutorId.getText()));
        livro.setAutor(autor);

        livro.setIsbn(txtIsbn.getText());
        livro.setAnoPublicacao(Integer.parseInt(txtAno.getText()));
        livro.setEditora(txtEditora.getText());
        livro.setGenero(txtGenero.getText());

        return livro;
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtAutorId.setText("");
        txtIsbn.setText("");
        txtAno.setText("");
        txtEditora.setText("");
        txtGenero.setText("");
    }
}