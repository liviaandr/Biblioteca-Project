package biblioteca;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

    class TelaCadastro extends JDialog {
    private JTextField txtTitulo = new JTextField();
    private JTextField txtAutor = new JTextField();
    private JButton btnSalvar = new JButton("Salvar");
    
        public TelaCadastro(JFrame parent) {
            super(parent, "Cadastrar Livro", true);
                setLayout(new GridLayout(, , , ));
                    add(new JLabel(" Título:")); 
                    add(txtTitulo);
                    add(new JLabel(" Autor:")); 
                    add(txtAutor);
                    add(new JLabel(""));
                    add(btnSalvar);
        } 
}