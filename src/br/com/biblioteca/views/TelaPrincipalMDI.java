package br.com.biblioteca.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalMDI extends JFrame implements ActionListener {
    private JDesktopPane desktopPane;
    private JMenuItem itemGerenciar;

    public TelaPrincipalMDI() {
        setTitle("Sistema de Biblioteca - MDI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuLivros = new JMenu("Livros");

        itemGerenciar = new JMenuItem("Gerenciar Livros");

        itemGerenciar.addActionListener(this);

        menuLivros.add(itemGerenciar);
        menuBar.add(menuLivros);
        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemGerenciar) {
            TelaLivroMDI telaLivro = new TelaLivroMDI();
            desktopPane.add(telaLivro);
            telaLivro.setVisible(true);
        }
    }

    public static void main(String[] args) {
        TelaPrincipalMDI tela = new TelaPrincipalMDI();
        tela.setVisible(true);
    }
}