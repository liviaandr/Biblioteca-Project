package br.com.biblioteca.views.mdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroFormPanel;
import javax.swing.*;
import java.awt.*;

public class TelaAlterarMDI extends JInternalFrame {
    private final LivroDAO livroDAO = new LivroDAO(); private final LivroFormPanel formulario = new LivroFormPanel(); private final JTextField codigo = new JTextField(8); private final Runnable callback;
    public TelaAlterarMDI() { this(0, null); }
    public TelaAlterarMDI(int codigoInicial, Runnable callback) {
        super("Alteração de Livro - MDI", true, true, true, true); this.callback = callback; setSize(540, 440); setLayout(new BorderLayout());
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT)); topo.add(new JLabel("Código:")); topo.add(codigo); JButton buscar = new JButton("Buscar"); topo.add(buscar);
        add(topo, BorderLayout.NORTH); add(formulario, BorderLayout.CENTER); JButton salvar = new JButton("Salvar alteração"); add(salvar, BorderLayout.SOUTH);
        buscar.addActionListener(e -> buscar()); salvar.addActionListener(e -> alterar()); if (codigoInicial > 0) { codigo.setText(String.valueOf(codigoInicial)); buscar(); }
    }
    private Integer codigo() { try { int n = Integer.parseInt(codigo.getText().trim()); if (n <= 0) throw new NumberFormatException(); return n; } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Informe um código válido."); return null; } }
    private void buscar() { Integer n = codigo(); if (n == null) return; try { Livro l = livroDAO.consultar(String.valueOf(n)); if (l == null) { JOptionPane.showMessageDialog(this, "Livro não encontrado."); return; } formulario.preencher(l); } catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); } }
    private void alterar() { Integer n = codigo(); if (n == null) return; try { livroDAO.alterar(String.valueOf(n), formulario.criarLivro()); JOptionPane.showMessageDialog(this, "Livro alterado com sucesso."); if (callback != null) callback.run(); dispose(); } catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE); } catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); } }
}
