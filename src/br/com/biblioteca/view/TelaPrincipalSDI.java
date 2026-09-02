package biblioteca;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipalSDI extends JFrame {
    public TelaPrincipalSDI() {
    setTitle("Sistema de Biblioteca - SDI");
    setSize(, );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel painelCentral = new JPanel(new GridBagLayout());
    painelCentral.add(new JLabel(""));
    add(painelCentral, BorderLayout.CENTER);
    
    configurarMenus();
    
    }
}
