package br.com.biblioteca.dao
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.com.biblioteca.models.Livro

public class TelaAlterar extends JDialog {
     private JTextField txtId = new JTextField(5);
    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtAutor = new JTextField(20);
    private JButton btnAlterar = new JButton("Alterar");

    public TelaAlterar(JFrame parent) {
        super(parent, "Alterar Livro", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" ID do Livro:")); add(txtId);
        add(new JLabel(" Novo Título:")); add(txtTitulo);
        add(new JLabel(" Novo Autor:")); add(txtAutor);
        add(new JLabel("")); add(btnAlterar);

        btnAlterar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Livro l = new Livro(id, txtTitulo.getText(), txtAutor.getText());
                new LivroDAO().alterar(l);
                JOptionPane.showMessageDialog(this, "Livro alterado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
        pack();
        setLocationRelativeTo(parent);
    }
}
