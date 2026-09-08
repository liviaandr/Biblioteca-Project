package br.com.biblioteca.views.sdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroFormPanel;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroSDI extends JFrame {
    private final LivroFormPanel formulario = new LivroFormPanel();
    private final LivroDAO livroDAO = new LivroDAO();
    private final Runnable onCadastro;

    public TelaCadastroSDI(JFrame parent) { this(parent, null); }

    public TelaCadastroSDI(JFrame parent, Runnable onCadastro) {
        this.onCadastro = onCadastro;
        setTitle("Cadastrar Livro - SDI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 350);
        setLocationRelativeTo(parent);
        JButton salvar = new JButton("Salvar");
        JButton limpar = new JButton("Limpar");
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT)); botoes.add(limpar); botoes.add(salvar);
        add(formulario, BorderLayout.CENTER); add(botoes, BorderLayout.SOUTH);
        limpar.addActionListener(e -> formulario.limpar());
        salvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        try {
            Livro livro = formulario.criarLivro();
            livroDAO.inserir(livro);
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso. Código: " + livro.getCodigo());
            formulario.limpar();
            if (onCadastro != null) onCadastro.run();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
