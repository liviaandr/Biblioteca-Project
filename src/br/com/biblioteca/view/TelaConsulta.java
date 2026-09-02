package biblioteca;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
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
    }
}