package br.com.biblioteca.views.mdi;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalMDI extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();

    public TelaPrincipalMDI() {
        setTitle("Sistema de Biblioteca - MDI"); setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null);
        add(desktopPane, BorderLayout.CENTER); configurarMenus();
    }

    private void configurarMenus() {
        JMenuBar barra = new JMenuBar();
        JMenu cadastro = new JMenu("Cadastro");
        JMenuItem cadastrar = new JMenuItem("Cadastrar Livro");
        JMenuItem consultar = new JMenuItem("Consultar / Listar Livros");
        JMenuItem alterar = new JMenuItem("Alterar Livro");
        JMenuItem excluir = new JMenuItem("Excluir Livro");
        cadastro.add(cadastrar); cadastro.add(consultar); cadastro.add(alterar); cadastro.add(excluir);
        JMenu sistema = new JMenu("Sistema"); JMenuItem sobre = new JMenuItem("Sobre"); JMenuItem sair = new JMenuItem("Sair");
        sistema.add(sobre); sistema.addSeparator(); sistema.add(sair); barra.add(cadastro); barra.add(sistema); setJMenuBar(barra);
        cadastrar.addActionListener(e -> abrir(new TelaCadastroMDI(), "Cadastro"));
        consultar.addActionListener(e -> abrir(new TelaConsultaMDI(), "Consulta"));
        alterar.addActionListener(e -> abrir(new TelaAlterarMDI(), "Alteração"));
        excluir.addActionListener(e -> abrir(new TelaExcluirMDI(), "Exclusão"));
        sobre.addActionListener(e -> JOptionPane.showMessageDialog(this, "Sistema de Biblioteca\nLP2 - Java Swing\nVersão MDI", "Sobre", JOptionPane.INFORMATION_MESSAGE));
        sair.addActionListener(e -> System.exit(0));
    }

    private void abrir(JInternalFrame frame, String chave) {
        for (JInternalFrame aberta : desktopPane.getAllFrames()) {
            if (aberta.getTitle().contains(chave)) { try { aberta.setSelected(true); } catch (java.beans.PropertyVetoException ignored) { } return; }
        }
        desktopPane.add(frame); frame.setVisible(true);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new TelaPrincipalMDI().setVisible(true)); }
}
