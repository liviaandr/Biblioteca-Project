package br.com.biblioteca.views.sdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import javax.swing.*;
import java.awt.*;

public class TelaExcluirSDI extends JFrame {
    private final JTextField txtCodigo = new JTextField(8);
    private final LivroDAO livroDAO = new LivroDAO();

    public TelaExcluirSDI(JFrame parent) {
        setTitle("Excluir Livro - SDI"); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(330, 150); setLocationRelativeTo(parent);
        JPanel painel = new JPanel(new FlowLayout()); painel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        painel.add(new JLabel("Código:")); painel.add(txtCodigo); JButton excluir = new JButton("Excluir"); painel.add(excluir); add(painel);
        excluir.addActionListener(e -> excluir());
    }

    private void excluir() {
        int codigo;
        try { codigo = Integer.parseInt(txtCodigo.getText().trim()); } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Informe um código válido."); return; }
        try {
            Livro livro = livroDAO.consultar(String.valueOf(codigo));
            if (livro == null) { JOptionPane.showMessageDialog(this, "Livro não encontrado."); return; }
            int resposta = JOptionPane.showConfirmDialog(this, "Excluir \"" + livro.getTitulo() + "\"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resposta == JOptionPane.YES_OPTION) { livroDAO.excluir(String.valueOf(codigo)); JOptionPane.showMessageDialog(this, "Livro excluído com sucesso."); dispose(); }
        } catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }
}
