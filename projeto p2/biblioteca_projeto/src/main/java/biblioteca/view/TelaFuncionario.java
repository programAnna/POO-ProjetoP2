package biblioteca.view;

import biblioteca.DAO.FuncionarioDAO;
import biblioteca.classes.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaFuncionario extends JFrame {

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtCargo;

    private JTable tabela;
    private DefaultTableModel modelo;

    private FuncionarioDAO dao = new FuncionarioDAO();

    public TelaFuncionario() {

        setTitle("Gerenciamento de Funcionários");
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JPanel painelCampos =
                new JPanel(new GridLayout(6, 2, 5, 5));

        txtId = new JTextField();
        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();
        txtEmail = new JTextField();
        txtCargo = new JTextField();

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

        painelCampos.add(new JLabel("Cargo"));
        painelCampos.add(txtCargo);

        JPanel painelBotoes = new JPanel();

        JButton btnSalvar =
                new JButton("Salvar");

        JButton btnAtualizar =
                new JButton("Atualizar");

        JButton btnExcluir =
                new JButton("Excluir");

        JButton btnLimpar =
                new JButton("Limpar");

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
        modelo.addColumn("Cargo");

        tabela = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabela);

        setLayout(new BorderLayout(10, 10));

        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        btnSalvar.addActionListener(
                e -> salvarFuncionario()
        );

        btnAtualizar.addActionListener(
                e -> atualizarFuncionario()
        );

        btnExcluir.addActionListener(
                e -> excluirFuncionario()
        );

        btnLimpar.addActionListener(
                e -> limparCampos()
        );

        tabela.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tabela.getSelectedRow() != -1) {

                int linha =
                        tabela.getSelectedRow();

                txtId.setText(
                        modelo.getValueAt(linha, 0)
                                .toString()
                );

                txtNome.setText(
                        modelo.getValueAt(linha, 1)
                                .toString()
                );

                txtCpf.setText(
                        modelo.getValueAt(linha, 2)
                                .toString()
                );

                txtTelefone.setText(
                        modelo.getValueAt(linha, 3)
                                .toString()
                );

                txtEmail.setText(
                        modelo.getValueAt(linha, 4)
                                .toString()
                );

                txtCargo.setText(
                        modelo.getValueAt(linha, 5)
                                .toString()
                );
            }
        });
    }

    private void carregarTabela() {

        try {

            modelo.setRowCount(0);

            for (String[] funcionario :
                    dao.listarTodos()) {

                modelo.addRow(funcionario);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void salvarFuncionario() {

        try {

            if (txtNome.getText().trim().isEmpty()
                    || txtCpf.getText().trim().isEmpty()
                    || txtTelefone.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()
                    || txtCargo.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos."
                );

                return;
            }

            Funcionario funcionario =
                    new Funcionario(
                            txtNome.getText(),
                            txtCpf.getText(),
                            txtTelefone.getText(),
                            txtEmail.getText(),
                            txtCargo.getText()
                    );

            dao.salvar(funcionario);

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Funcionário salvo com sucesso!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void atualizarFuncionario() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um funcionário."
                );

                return;
            }

            dao.atualizar(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtCargo.getText()
            );

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Funcionário atualizado!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void excluirFuncionario() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um funcionário."
                );

                return;
            }

            int resposta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Deseja excluir este funcionário?",
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
                    "Funcionário excluído!"
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
        txtCargo.setText("");
    }
}