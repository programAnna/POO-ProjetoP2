package biblioteca.view;

import biblioteca.DAO.CopiaDAO;
import biblioteca.DAO.LivroDAO;

import biblioteca.classes.Copia;
import biblioteca.classes.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaCopia extends JFrame {

    private JComboBox<String> cbLivro;

    private JTable tabela;
    private DefaultTableModel modelo;

    private CopiaDAO copiaDAO =
            new CopiaDAO();

    public TelaCopia() {

        setTitle("Controle de Cópias");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel painelTopo =
                new JPanel(new GridLayout(2,1));

        cbLivro = new JComboBox<>();

        try {

            LivroDAO livroDAO =
                    new LivroDAO();

            for (String titulo :
                    livroDAO.listarTitulos()) {

                cbLivro.addItem(titulo);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }

        painelTopo.add(
                new JLabel("Livro")
        );

        painelTopo.add(cbLivro);

        JButton btnAdicionar =
                new JButton("Adicionar Cópia");

        JButton btnExcluir =
                new JButton("Excluir Cópia");

        JPanel painelBotoes =
                new JPanel();

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnExcluir);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Tombo");
        modelo.addColumn("Título");

        tabela = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabela);

        setLayout(new BorderLayout());

        add(painelTopo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        btnAdicionar.addActionListener(
                e -> adicionarCopia()
        );

        btnExcluir.addActionListener(
                e -> excluirCopia()
        );
    }

    private void adicionarCopia() {

        try {

            String titulo =
                    cbLivro.getSelectedItem()
                            .toString();

            LivroDAO livroDAO =
                    new LivroDAO();

            Livro livro =
                    livroDAO.buscarPorTitulo(
                            titulo
                    );

            Copia copia =
                    new Copia(livro);

            copiaDAO.salvar(copia);

            carregarTabela();

            JOptionPane.showMessageDialog(
                    this,
                    "Cópia cadastrada!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void excluirCopia() {

        try {

            int linha =
                    tabela.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione uma cópia."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            modelo.getValueAt(
                                    linha,
                                    0
                            ).toString()
                    );

            int resposta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Excluir cópia?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                    );

            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }

            copiaDAO.excluir(id);

            carregarTabela();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void carregarTabela() {

        try {

            modelo.setRowCount(0);

            for (String[] copia :
                    copiaDAO.listarTodos()) {

                modelo.addRow(copia);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}