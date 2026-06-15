package biblioteca.view;

import biblioteca.DAO.ReservaDAO;
import biblioteca.DAO.LeitorDAO;
import biblioteca.DAO.LivroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaReserva extends JFrame {

    private JComboBox<String> cbLeitor;
    private JComboBox<String> cbLivro;
    private JTextField txtData;

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaReserva() {

        setTitle("Controle de Reservas");
        setSize(900, 550);
        setLocationRelativeTo(null);

        JPanel painelCampos =
                new JPanel(new GridLayout(3, 2, 5, 5));

        cbLeitor = new JComboBox<>();
        cbLivro = new JComboBox<>();
        txtData = new JTextField();

        try {

            LeitorDAO leitorDAO = new LeitorDAO();

            for (String leitor : leitorDAO.listarNomes()) {
                cbLeitor.addItem(leitor);
            }

            LivroDAO livroDAO = new LivroDAO();

            for (String livro : livroDAO.listarTitulos()) {
                cbLivro.addItem(livro);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }

        txtData.setText(
                java.time.LocalDate.now().toString()
        );

        painelCampos.add(new JLabel("Leitor"));
        painelCampos.add(cbLeitor);

        painelCampos.add(new JLabel("Livro"));
        painelCampos.add(cbLivro);

        painelCampos.add(new JLabel("Data"));
        painelCampos.add(txtData);

        JButton btnReservar =
                new JButton("Realizar Reserva");

        JButton btnAtualizar =
                new JButton("Atualizar Data");

        JButton btnCancelar =
                new JButton("Cancelar Reserva");

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

        painelBotoes.add(btnReservar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnCancelar);

        setLayout(new BorderLayout());

        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        tabela.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tabela.getSelectedRow() != -1) {

                int linha =
                        tabela.getSelectedRow();

                cbLeitor.setSelectedItem(
                        modelo.getValueAt(linha, 1)
                );

                cbLivro.setSelectedItem(
                        modelo.getValueAt(linha, 2)
                );

                txtData.setText(
                        modelo.getValueAt(linha, 3)
                                .toString()
                );
            }
        });

        btnReservar.addActionListener(e -> {

            try {

                String leitor =
                        cbLeitor.getSelectedItem().toString();

                String livro =
                        cbLivro.getSelectedItem().toString();

                ReservaDAO dao =
                        new ReservaDAO();

                dao.salvar(
                        leitor,
                        livro,
                        txtData.getText()
                );

                carregarTabela();

                JOptionPane.showMessageDialog(
                        this,
                        "Reserva registrada!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        btnAtualizar.addActionListener(e -> {

            try {

                int linha =
                        tabela.getSelectedRow();

                if (linha == -1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Selecione uma reserva."
                    );

                    return;
                }

                int id = Integer.parseInt(
                        modelo.getValueAt(linha, 0)
                                .toString()
                );

                ReservaDAO dao =
                        new ReservaDAO();

                dao.atualizar(
                        id,
                        cbLeitor.getSelectedItem().toString(),
                        cbLivro.getSelectedItem().toString(),
                        txtData.getText()
                );

                carregarTabela();

                JOptionPane.showMessageDialog(
                        this,
                        "Data da reserva atualizada!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        btnCancelar.addActionListener(e -> {

            try {

                int linha =
                        tabela.getSelectedRow();

                if (linha == -1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Selecione uma reserva."
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
                                "Cancelar reserva?",
                                "Confirmação",
                                JOptionPane.YES_NO_OPTION
                        );

                if (resposta != JOptionPane.YES_OPTION) {
                    return;
                }

                ReservaDAO dao =
                        new ReservaDAO();

                dao.excluir(id);

                carregarTabela();

                JOptionPane.showMessageDialog(
                        this,
                        "Reserva cancelada!"
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

            ReservaDAO dao =
                    new ReservaDAO();

            for (String[] reserva :
                    dao.listarTodos()) {

                modelo.addRow(reserva);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}