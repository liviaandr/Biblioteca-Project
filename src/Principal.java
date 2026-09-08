import br.com.biblioteca.views.mdi.TelaPrincipalMDI;
import br.com.biblioteca.views.sdi.TelaPrincipalSDI;
import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Object[] opcoes = {"SDI", "MDI"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha a versão do Sistema de Biblioteca:", "Biblioteca",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (escolha == 0) new TelaPrincipalSDI().setVisible(true);
            else if (escolha == 1) new TelaPrincipalMDI().setVisible(true);
        });
    }
}
