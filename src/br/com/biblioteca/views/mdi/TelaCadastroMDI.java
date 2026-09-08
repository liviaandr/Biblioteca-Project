package br.com.biblioteca.views.mdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroFormPanel;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroMDI extends JInternalFrame {
    private final LivroFormPanel formulario = new LivroFormPanel(); private final LivroDAO livroDAO = new LivroDAO(); private final Runnable onCadastro;
    public TelaCadastroMDI() { this(null); }
    public TelaCadastroMDI(Runnable onCadastro) {
        super("Cadastro de Livro - MDI", true, true, true, true);
        this.onCadastro = onCadastro;
        setSize(540, 390); setLayout(new BorderLayout());
        JButton salvar = new JButton("Salvar"); JButton limpar = new JButton("Limpar"); JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT)); botoes.add(limpar); botoes.add(salvar);
        add(formulario, BorderLayout.CENTER); add(botoes, BorderLayout.SOUTH);
        limpar.addActionListener(e -> formulario.limpar()); salvar.addActionListener(e -> salvar());
    }
    private void salvar() {
        try { Livro livro = formulario.criarLivro(); livroDAO.inserir(livro); JOptionPane.showMessageDialog(this, "Livro cadastrado. Código: " + livro.getCodigo()); formulario.limpar(); if (onCadastro != null) onCadastro.run(); }
        catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE); }
        catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }
}
