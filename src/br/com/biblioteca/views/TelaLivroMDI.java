package br.com.biblioteca.views;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.models.Livro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLivroMDI extends JInternalFrame implements ActionListener {
    private JTextField txtId = new JTextField(5);
    private JTextField txtTitulo = new JTextField();
    private JTextField txtAutor = new JTextField();

    // 1. Declarado como btnBuscar
    private JButton btnBuscar = new JButton("Buscar");
    private JButton btnSalvar = new JButton("Salvar");

    private LivroDAO livroDAO = new LivroDAO();

    public TelaLivroMDI(){
        super("Gerenciamento de Livros - MDI", true, true, true, true);
        setSize(350, 250);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel(" ID (Busca):"));
        add(txtId);

        add(new JLabel(" Título:"));
        add(txtTitulo);

        add(new JLabel(" Autor:"));
        add(txtAutor);

        add(btnSalvar);
        add(btnBuscar); // Usando btnBuscar

        btnSalvar.addActionListener(this);
        btnBuscar.addActionListener(this); // Usando btnBuscar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            salvar();
        } else if (e.getSource() == btnBuscar){ // Usando btnBuscar
            buscar(); // Nome do método ajustado para buscar()
        }
    }

    private void salvar(){
        try {
            Livro a = new Livro();
            a.setNome(txtTitulo.getText());

            livroDAO.inserir(a);
            JOptionPane.showMessageDialog(this, "O livro foi salvo com sucesso!");
            limparCampos();
        } catch (Exception ex) { // Corrigido erro de digitação Excepcion -> Exception
            JOptionPane.showMessageDialog(this, "Erro ao salvar!");
        }
    }

    // Método para o botão Buscar
    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            // Lógica de busca no livroDAO entra aqui
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido para busca!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar livro!");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
    }
}