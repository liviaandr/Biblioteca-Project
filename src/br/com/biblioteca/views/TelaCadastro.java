package br.com.biblioteca.dao;
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
import br.com.biblioteca.models.Livro;

public class TelaCadastro extends JDialog {
    private JTextField txtTitulo = new JTextField();
    private JTextField txtAutor = new JTextField();
    private JButton btnSalvar = new JButton("Salvar");
    
        public TelaCadastro(JFrame parent) {
            super(parent, "Cadastrar Livro", true);
                setLayout(new GridLayout());
                    add(new JLabel(" Título:")); 
                    add(txtTitulo);
                    add(new JLabel(" Autor:")); 
                    add(txtAutor);
                    add(new JLabel(""));
                    add(btnSalvar);
       
                    btnSalvar.addActionListener(e -> {
                        try {
                            Livro l = new Livro(0, txtTitulo.getText(), txtAutor.getText());
                            new LivroDAO().cadastrar(l);
                            JOptionPane.showMessageDialog(this, "Livro cadastrado!");
                            dispose();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage()); };
                            });
                        }
                       { 
                        pack();
                        setLocationRelativeTo(parent);
    }
}