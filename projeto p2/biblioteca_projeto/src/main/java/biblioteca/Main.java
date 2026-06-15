package biblioteca;

import biblioteca.DAO.Banco;
import biblioteca.view.TelaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Banco.criarTabelas();

        SwingUtilities.invokeLater(() -> {

            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);

        });
    }
}