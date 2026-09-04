package biblioteca;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipalSDI extends JFrame {
    public TelaPrincipalSDI() {
    setTitle("Sistema de Biblioteca - SDI");
    setSize(600,400 );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel painelCentral = new JPanel(new GridBagLayout());
    painelCentral.add(new JLabel(""));
    add(painelCentral, BorderLayout.CENTER);
    
    configurarMenus();
    
    }
private void configurarMenus() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menuOperacoes = new JMenu("Operações");
    JMenuItem itemCadastrar = new JMenuItem("Cadastrar Livro");
    JMenuItem itemConsultar = new JMenuItem("Consultar Livros");
    JMenuItem itemAlterar = new JMenuItem("Alterar Livro");
    JMenuItem itemExcluir = new JMenuItem("Excluir Livro");

    menuOperacoes.add(itemCadastrar);
    menuOperacoes.add(itemConsultar);
    menuOperacoes.add(itemAlterar);
    menuOperacoes.add(itemExcluir); 
    menuBar.add(menuOperacoes);
    setJMenuBar(menuBar);

    itemCadastrar.addActionListener(e -> new TelaCadastro(this).setVisible(true));
    itemConsultar.addActionListener(e -> new TelaConsulta(this).setVisible(true));
    itemAlterar.addActionListener(e -> new TelaAlterar(this).setVisible(true));
    itemExcluir.addActionListener(e -> new TelaExcluir(this).setVisible(true));
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TelaPrincipalSDI().setVisible(true));
    }
}
