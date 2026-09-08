package br.com.biblioteca.views.mdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import javax.swing.*;
import java.awt.*;

public class TelaExcluirMDI extends JInternalFrame {
    private final JTextField codigo = new JTextField(8); private final LivroDAO livroDAO = new LivroDAO();
    public TelaExcluirMDI() { super("Exclusão de Livro - MDI", true, true, true, true); setSize(340, 150); JPanel p = new JPanel(new FlowLayout()); p.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); p.add(new JLabel("Código:")); p.add(codigo); JButton excluir = new JButton("Excluir"); p.add(excluir); add(p); excluir.addActionListener(e -> excluir()); }
    private void excluir() { int id; try { id = Integer.parseInt(codigo.getText().trim()); } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Informe um código válido."); return; } try { Livro l = livroDAO.consultar(String.valueOf(id)); if (l == null) { JOptionPane.showMessageDialog(this, "Livro não encontrado."); return; } int r = JOptionPane.showConfirmDialog(this, "Excluir \"" + l.getTitulo() + "\"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); if (r == JOptionPane.YES_OPTION) { livroDAO.excluir(String.valueOf(id)); JOptionPane.showMessageDialog(this, "Livro excluído com sucesso."); dispose(); } } catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); } }
}
