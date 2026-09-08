package br.com.biblioteca.views.sdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroFormPanel;
import javax.swing.*;
import java.awt.*;

public class TelaAlterarSDI extends JFrame {
    private final LivroDAO livroDAO = new LivroDAO();
    private final LivroFormPanel formulario = new LivroFormPanel();
    private final JTextField txtCodigo = new JTextField(8);
    private final Runnable onAlteracao;

    public TelaAlterarSDI(JFrame parent) { this(parent, 0, null); }
    public TelaAlterarSDI(JFrame parent, int codigo, Runnable onAlteracao) {
        this.onAlteracao = onAlteracao;
        setTitle("Alterar Livro - SDI"); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 420); setLocationRelativeTo(parent);
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT)); topo.add(new JLabel("Código:")); topo.add(txtCodigo);
        JButton buscar = new JButton("Buscar"); topo.add(buscar);
        JButton salvar = new JButton("Salvar alteração");
        add(topo, BorderLayout.NORTH); add(formulario, BorderLayout.CENTER); add(salvar, BorderLayout.SOUTH);
        buscar.addActionListener(e -> buscar()); salvar.addActionListener(e -> alterar());
        if (codigo > 0) { txtCodigo.setText(String.valueOf(codigo)); buscar(); }
    }

    private Integer codigo() {
        try { int codigo = Integer.parseInt(txtCodigo.getText().trim()); if (codigo <= 0) throw new NumberFormatException(); return codigo; }
        catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Informe um código válido.", "Validação", JOptionPane.WARNING_MESSAGE); return null; }
    }

    private void buscar() {
        Integer codigo = codigo(); if (codigo == null) return;
        try { Livro livro = livroDAO.consultar(String.valueOf(codigo)); if (livro == null) { JOptionPane.showMessageDialog(this, "Livro não encontrado."); return; } formulario.preencher(livro); }
        catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }

    private void alterar() {
        Integer codigo = codigo(); if (codigo == null) return;
        try { Livro livro = formulario.criarLivro(); livroDAO.alterar(String.valueOf(codigo), livro); JOptionPane.showMessageDialog(this, "Livro alterado com sucesso."); if (onAlteracao != null) onAlteracao.run(); dispose(); }
        catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE); }
        catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }
}
