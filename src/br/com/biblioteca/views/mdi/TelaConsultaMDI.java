package br.com.biblioteca.views.mdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaConsultaMDI extends JInternalFrame {
    private final LivroDAO livroDAO = new LivroDAO(); private final LivroTableModel model = new LivroTableModel(); private final JTable tabela = new JTable(model);
    private final JComboBox<String> filtro = new JComboBox<>(new String[]{"Todos", "Código", "Título", "Autor"}); private final JTextField busca = new JTextField(18);
    public TelaConsultaMDI() {
        super("Consulta de Livros - MDI", true, true, true, true); setSize(960, 500); setLayout(new BorderLayout());
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); tabela.setAutoCreateRowSorter(true);
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT)); topo.add(new JLabel("Pesquisar por:")); topo.add(filtro); topo.add(busca); JButton pesquisar = new JButton("Pesquisar"); JButton listar = new JButton("Listar Todos"); topo.add(pesquisar); topo.add(listar);
        JPanel baixo = new JPanel(new FlowLayout(FlowLayout.RIGHT)); JButton cadastrar = new JButton("Cadastrar novo"); baixo.add(cadastrar); JButton alterar = new JButton("Alterar selecionado"); JButton excluir = new JButton("Excluir selecionado"); baixo.add(alterar); baixo.add(excluir);
        add(topo, BorderLayout.NORTH); add(new JScrollPane(tabela), BorderLayout.CENTER); add(baixo, BorderLayout.SOUTH);
        pesquisar.addActionListener(e -> pesquisar()); listar.addActionListener(e -> carregarTodos());
        cadastrar.addActionListener(e -> { TelaCadastroMDI tela = new TelaCadastroMDI(this::carregarTodos); getDesktopPane().add(tela); tela.setVisible(true); });
        alterar.addActionListener(e -> alterar()); excluir.addActionListener(e -> excluir()); carregarTodos();
    }
    public void carregarTodos() { carregar(livroDAO.listar()); }
    private void carregar(List<Livro> livros) { model.setLivros(livros); }
    private void pesquisar() {
        String termo = busca.getText().trim();
        try { switch (String.valueOf(filtro.getSelectedItem())) {
            case "Todos" -> carregarTodos();
            case "Código" -> { try { carregar(livroDAO.buscarPorCodigo(Integer.parseInt(termo))); } catch (NumberFormatException ex) { throw new IllegalArgumentException("O código deve ser um número inteiro."); } }
            case "Título" -> carregar(livroDAO.buscarPorTitulo(termo)); case "Autor" -> carregar(livroDAO.buscarPorAutor(termo)); default -> carregarTodos();
        }} catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE); }
          catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }
    private Livro selecionado() { int row = tabela.getSelectedRow(); if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um livro na tabela."); return null; } return model.getLivroAt(tabela.convertRowIndexToModel(row)); }
    private void alterar() { Livro l = selecionado(); if (l != null) { TelaAlterarMDI tela = new TelaAlterarMDI(l.getCodigo(), this::carregarTodos); getDesktopPane().add(tela); tela.setVisible(true); } }
    private void excluir() { Livro l = selecionado(); if (l == null) return; int r = JOptionPane.showConfirmDialog(this, "Deseja excluir \"" + l.getTitulo() + "\"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); if (r == JOptionPane.YES_OPTION) try { livroDAO.excluir(String.valueOf(l.getCodigo())); carregarTodos(); JOptionPane.showMessageDialog(this, "Livro excluído."); } catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); } }
}
