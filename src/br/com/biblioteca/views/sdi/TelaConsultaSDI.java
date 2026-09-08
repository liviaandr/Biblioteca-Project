package br.com.biblioteca.views.sdi;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import br.com.biblioteca.views.common.LivroTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaConsultaSDI extends JFrame {
    private final LivroDAO livroDAO = new LivroDAO();
    private final LivroTableModel tableModel = new LivroTableModel();
    private final JTable tabela = new JTable(tableModel);
    private final JComboBox<String> cbFiltro = new JComboBox<>(new String[]{"Todos", "Código", "Título", "Autor"});
    private final JTextField txtBusca = new JTextField(20);

    public TelaConsultaSDI(JFrame parent) {
        setTitle("Consultar Livros - SDI"); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500); setLocationRelativeTo(parent);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); tabela.setAutoCreateRowSorter(true);
        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT)); filtro.add(new JLabel("Pesquisar por:")); filtro.add(cbFiltro); filtro.add(txtBusca);
        JButton pesquisar = new JButton("Pesquisar"); JButton listar = new JButton("Listar Todos"); filtro.add(pesquisar); filtro.add(listar);
        JButton alterar = new JButton("Alterar selecionado"); JButton excluir = new JButton("Excluir selecionado");
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT)); JButton cadastrar = new JButton("Cadastrar novo"); acoes.add(cadastrar); acoes.add(alterar); acoes.add(excluir);
        add(filtro, BorderLayout.NORTH); add(new JScrollPane(tabela), BorderLayout.CENTER); add(acoes, BorderLayout.SOUTH);
        pesquisar.addActionListener(e -> pesquisar()); listar.addActionListener(e -> carregarTodos());
        cadastrar.addActionListener(e -> new TelaCadastroSDI(this, this::carregarTodos).setVisible(true));
        alterar.addActionListener(e -> alterarSelecionado()); excluir.addActionListener(e -> excluirSelecionado());
        carregarTodos();
    }

    public void carregarTodos() { carregar(livroDAO.listar()); }
    private void carregar(List<Livro> livros) { tableModel.setLivros(livros); }

    private void pesquisar() {
        String termo = txtBusca.getText().trim();
        try {
            switch (String.valueOf(cbFiltro.getSelectedItem())) {
                case "Todos" -> carregarTodos();
                case "Código" -> {
                    if (termo.isEmpty()) throw new IllegalArgumentException("Informe o código.");
                    int codigo;
                    try { codigo = Integer.parseInt(termo); } catch (NumberFormatException ex) { throw new IllegalArgumentException("O código deve ser um número inteiro."); }
                    carregar(livroDAO.buscarPorCodigo(codigo));
                }
                case "Título" -> carregar(livroDAO.buscarPorTitulo(termo));
                case "Autor" -> carregar(livroDAO.buscarPorAutor(termo));
                default -> carregarTodos();
            }
        } catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE); }
          catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }

    private Livro selecionado() {
        int row = tabela.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um livro na tabela.", "Atenção", JOptionPane.WARNING_MESSAGE); return null; }
        return tableModel.getLivroAt(tabela.convertRowIndexToModel(row));
    }

    private void alterarSelecionado() {
        Livro livro = selecionado(); if (livro == null) return;
        new TelaAlterarSDI(this, livro.getCodigo(), this::carregarTodos).setVisible(true);
    }

    private void excluirSelecionado() {
        Livro livro = selecionado(); if (livro == null) return;
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja excluir o livro \"" + livro.getTitulo() + "\"?", "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resposta != JOptionPane.YES_OPTION) return;
        try { livroDAO.excluir(String.valueOf(livro.getCodigo())); carregarTodos(); JOptionPane.showMessageDialog(this, "Livro excluído com sucesso."); }
        catch (RuntimeException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }
}
