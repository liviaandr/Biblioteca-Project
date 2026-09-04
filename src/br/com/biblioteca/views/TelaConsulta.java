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
import br.com.biblioteca.models.Livro;

public class TelaConsulta extends JDialog {
    private JTextField txtBusca = new JTextField(15);
    private JButton btnBuscar = new JButton("Buscar");
    private JTextArea txtResultado = new JTextArea(10, 30);

    public TelaConsulta(JFrame parent) {
        super(parent, "Consultar Livros", true);
        setLayout(new BorderLayout(10, 10));
        JPanel pnlTop = new JPanel();
            pnlTop.add(new JLabel("Termo:")); 
            pnlTop.add(txtBusca); 
            pnlTop.add(btnBuscar);
            add(pnlTop, BorderLayout.NORTH); 
            add(new JScrollPane(txtResultado), BorderLayout.CENTER);

            btnBuscar.addActionListener(e -> {
                try {
                    List<Livro> livros = new LivroDAO().consultar (txtBusca.getText());
                    txtResultado.setText("");
                    for (Livro l : livros) {
                        txtResultado.append (String.format("ID: %d | %s - %s\n", l.getId(), l.getTitulo(), l));
                        }
                    }catch (SQLException ex) { 
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                 }
            });
                 {pack(); 
                setLocationRelativeTo(parent);
    } 
} 