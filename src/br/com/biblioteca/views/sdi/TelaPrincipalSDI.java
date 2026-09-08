package br.com.biblioteca.views.sdi;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalSDI extends JFrame {
    public TelaPrincipalSDI() {
        setTitle("Sistema de Biblioteca - SDI");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Sistema de Biblioteca", SwingConstants.CENTER), BorderLayout.CENTER);
        configurarMenus();
    }

    private void configurarMenus() {
        JMenuBar barra = new JMenuBar();
        JMenu cadastro = new JMenu("Cadastro");
        JMenuItem cadastrar = new JMenuItem("Cadastrar Livro");
        JMenuItem consultar = new JMenuItem("Consultar / Listar Livros");
        JMenuItem alterar = new JMenuItem("Alterar Livro");
        JMenuItem excluir = new JMenuItem("Excluir Livro");
        cadastro.add(cadastrar); cadastro.add(consultar); cadastro.add(alterar); cadastro.add(excluir);

        JMenu sistema = new JMenu("Sistema");
        JMenuItem sobre = new JMenuItem("Sobre");
        JMenuItem sair = new JMenuItem("Sair");
        sistema.add(sobre); sistema.addSeparator(); sistema.add(sair);
        barra.add(cadastro); barra.add(sistema); setJMenuBar(barra);

        cadastrar.addActionListener(e -> new TelaCadastroSDI(this).setVisible(true));
        consultar.addActionListener(e -> new TelaConsultaSDI(this).setVisible(true));
        alterar.addActionListener(e -> new TelaAlterarSDI(this).setVisible(true));
        excluir.addActionListener(e -> new TelaExcluirSDI(this).setVisible(true));
        sobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Sistema de Biblioteca\nLP2 - Java Swing\nVersão SDI", "Sobre", JOptionPane.INFORMATION_MESSAGE));
        sair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipalSDI().setVisible(true));
    }
}
