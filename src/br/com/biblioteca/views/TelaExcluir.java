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

public class TelaExcluir extends JDialog {
    private JTextField txtId = new JTextField(5);
    private JButton btnExcluir = new JButton("Excluir");

    public TelaExcluir(JFrame parent) {
        super(parent, "Excluir Livro", true);
        setLayout(new GridLayout());

        add(new JLabel(" ID do Livro:"));
        add(txtId);
        add(new JLabel(""));
        add(btnExcluir);

        btnExcluir.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    new LivroDAO().excluir(id);
                    JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                }
            }
        });
        {
        pack();
        setLocationRelativeTo(parent);
        }
    }
}