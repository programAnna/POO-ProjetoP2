package biblioteca.view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        setTitle("Sistema de Biblioteca");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnLivro =
                new JButton("Gerenciar Livros");

        JButton btnLeitor =
                new JButton("Gerenciar Leitores");

        JButton btnFuncionario =
                new JButton("Gerenciar Funcionários");

        JButton btnCopia =
                new JButton("Gerenciar Cópias");

        JButton btnEmprestimo =
                new JButton("Gerenciar Empréstimos");

        JButton btnReserva =
                new JButton("Gerenciar Reservas");

        JButton btnSair =
                new JButton("Sair");

        btnLivro.addActionListener(e ->
                new TelaLivro().setVisible(true));

        btnLeitor.addActionListener(e ->
                new TelaLeitor().setVisible(true));

        btnFuncionario.addActionListener(e ->
                new TelaFuncionario().setVisible(true));

        btnCopia.addActionListener(e ->
                new TelaCopia().setVisible(true));

        btnEmprestimo.addActionListener(e ->
                new TelaEmprestimo().setVisible(true));

        btnReserva.addActionListener(e ->
                new TelaReserva().setVisible(true));

        btnSair.addActionListener(e ->
                dispose());

        setLayout(new GridLayout(7, 1, 10, 10));

        add(btnLivro);
        add(btnLeitor);
        add(btnFuncionario);
        add(btnCopia);
        add(btnEmprestimo);
        add(btnReserva);
        add(btnSair);
    }
}