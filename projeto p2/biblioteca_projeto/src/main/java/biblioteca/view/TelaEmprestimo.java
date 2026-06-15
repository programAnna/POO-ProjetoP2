package biblioteca.view;

import biblioteca.DAO.EmprestimoDAO;
import biblioteca.DAO.LeitorDAO;
import biblioteca.DAO.LivroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaEmprestimo extends JFrame {

    private JComboBox<String> cbLeitor;
    private JComboBox<String> cbLivro;

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaEmprestimo() {

        setTitle("Controle de Empréstimos");
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel painelCampos =
                new JPanel(new GridLayout(2, 2, 5, 5));

        cbLeitor = new JComboBox<>();
        cbLivro = new JComboBox<>();

        try {

            LeitorDAO leitorDAO =
                    new LeitorDAO();

            for (String leitor :
                    leitorDAO.listarNomes()) {

                cbLeitor.addItem(leitor);
            }

            LivroDAO livroDAO =
                    new LivroDAO();

            for (String livro :
                    livroDAO.listarTitulos()) {

                cbLivro.addItem(livro);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }

        painelCampos.add(
                new JLabel("Leitor")
        );

        painelCampos.add(cbLeitor);

        painelCampos.add(
                new JLabel("Livro")
        );

        painelCampos.add(cbLivro);

        JButton btnEmprestar =
                new JButton("Realizar Empréstimo");

        JButton btnFinalizar =
                new JButton("Finalizar Empréstimo");

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Leitor");
        modelo.addColumn("Livro");
        modelo.addColumn("Data");

        tabela = new JTable(modelo);

        carregarTabela();

        JScrollPane scroll =
                new JScrollPane(tabela);

        JPanel painelBotoes =
                new JPanel();

        painelBotoes.add(btnEmprestar);
        painelBotoes.add(btnFinalizar);

        setLayout(new BorderLayout());

        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnEmprestar.addActionListener(e -> {

            try {

                String leitor =
                        cbLeitor.getSelectedItem().toString();

                String livro =
                        cbLivro.getSelectedItem().toString();

                EmprestimoDAO dao =
                        new EmprestimoDAO();

                dao.salvar(
                        leitor,
                        livro,
                        java.time.LocalDate.now().toString()
                );

                carregarTabela();

                JOptionPane.showMessageDialog(
                        this,
                        "Empréstimo registrado!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        btnFinalizar.addActionListener(e -> {

            try {

                int linha =
                        tabela.getSelectedRow();

                if (linha == -1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Selecione um empréstimo."
                    );

                    return;
                }

                int id = Integer.parseInt(
                        modelo.getValueAt(linha, 0)
                                .toString()
                );

                int resposta =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Finalizar empréstimo?",
                                "Confirmação",
                                JOptionPane.YES_NO_OPTION
                        );

                if (resposta != JOptionPane.YES_OPTION) {
                    return;
                }

                EmprestimoDAO dao =
                        new EmprestimoDAO();

                dao.excluir(id);

                carregarTabela();

                JOptionPane.showMessageDialog(
                        this,
                        "Empréstimo finalizado!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });
    }

    private void carregarTabela() {

        try {

            modelo.setRowCount(0);

            EmprestimoDAO dao =
                    new EmprestimoDAO();

            for (String[] emprestimo :
                    dao.listarTodos()) {

                modelo.addRow(emprestimo);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}