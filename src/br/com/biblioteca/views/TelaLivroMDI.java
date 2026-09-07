package br.com.biblioteca.views;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import javax.swing.*;
import java.awt.*;

public class TelaLivroMDI extends JInternalFrame {private JTextField txtId = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtAutorId = new JTextField();
    private JTextField txtIsbn = new JTextField();
    private JTextField txtAno = new JTextField();
    private JTextField txtEditora = new JTextField();
    private JTextField txtGenero = new JTextField();

    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnExcluir = new JButton("Excluir");

    private LivroDAO livroDAO = new LivroDAO();

    public TelaLivroMDI() {
        super("Cadastro de Livros - MDI", true, true, true, true);
        setSize(350, 250);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel(" ID (Busca):"));
        add(txtId);

        add(new JLabel(" Título:"));
        add(txtNome);

        add(new JLabel(" Autor:"));
        add(txtAutorId);

        add(btnSalvar);
        add(btnBuscar);
        add(btnAtualizar);
        add(btnExcluir);

        btnSalvar.addActionListener(e -> {
            try {
                Livro livro = new Livro();
                livro.setNome(txtNome.getText());
                livro.setAutor(txtAutorId.getText());

                livroDAO.inserir(livro);
                JOptionPane.showMessageDialog(this, "Foi salvo com sucesso!");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar!");
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Livro livro = livroDAO.consultar(txtId.getText());
                if (livro != null) {
                    txtNome.setText(livro.getNome());
                    txtAutorId.setText(livro.getAutor());
                } else {
                    JOptionPane.showMessageDialog(this, "Não encontrado!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar!");
            }
        });

        btnAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Livro livro = new Livro();
                livro.setId(id);
                livro.setNome(txtNome.getText());
                livro.setAutor(txtAutorId.getText());

                livroDAO.alterar(livro);
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                livroDAO.excluir(txtId.getText());
                JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir!");
            }
        });
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtAutorId.setText("");
    }
}