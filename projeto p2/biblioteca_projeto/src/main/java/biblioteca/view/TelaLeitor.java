package biblioteca.view;

import biblioteca.DAO.LeitorDAO;
import biblioteca.classes.Leitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaLeitor extends JFrame {

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;

    private JTable tabela;
    private DefaultTableModel modelo;

    private LeitorDAO dao = new LeitorDAO();

    public TelaLeitor() {

        setTitle("Gerenciamento de Leitores");
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 5, 5));

        txtId = new JTextField();
        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();
        txtEmail = new JTextField();

        txtId.setEditable(false);

        painelCampos.add(new JLabel("ID"));
        painelCampos.add(txtId);

        painelCampos.add(new JLabel("Nome"));
        painelCampos.add(txtNome);

        painelCampos.add(new JLabel("CPF"));
        painelCampos.add(txtCpf);

        painelCampos.add(new JLabel("Telefone"));
        painelCampos.add(txtTelefone);

        painelCampos.add(new JLabel("Email"));
        painelCampos.add(txtEmail);

        JPanel painelBotoes = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        modelo.addColumn("Email");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        setLayout(new BorderLayout(10, 10));

        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        btnSalvar.addActionListener(e -> salvarLeitor());

        btnAtualizar.addActionListener(e -> atualizarLeitor());

        btnExcluir.addActionListener(e -> excluirLeitor());

        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tabela.getSelectedRow() != -1) {

                txtId.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 0
                        ).toString()
                );

                txtNome.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 1
                        ).toString()
                );

                txtCpf.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 2
                        ).toString()
                );

                txtTelefone.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 3
                        ).toString()
                );

                txtEmail.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 4
                        ).toString()
                );
            }
        });
    }

    private void carregarTabela() {

        try {

            modelo.setRowCount(0);

            for (String[] leitor : dao.listarTodos()) {

                modelo.addRow(leitor);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void salvarLeitor() {

        try {

            if (txtNome.getText().trim().isEmpty()
                    || txtCpf.getText().trim().isEmpty()
                    || txtTelefone.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos."
                );

                return;
            }

            Leitor leitor = new Leitor(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    "Senha123"
            );

            dao.salvar(leitor);

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Leitor salvo com sucesso!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void atualizarLeitor() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um leitor para atualizar."
                );

                return;
            }

            if (txtNome.getText().trim().isEmpty()
                    || txtCpf.getText().trim().isEmpty()
                    || txtTelefone.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos."
                );

                return;
            }

            dao.atualizar(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText()
            );

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Leitor atualizado com sucesso!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void excluirLeitor() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um leitor para excluir."
                );

                return;
            }

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este leitor?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }

            dao.excluir(
                    Integer.parseInt(txtId.getText())
            );

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Leitor excluído com sucesso!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void limparCampos() {

        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
    }
}