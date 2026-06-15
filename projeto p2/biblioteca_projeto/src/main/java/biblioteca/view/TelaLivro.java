package biblioteca.view;

import biblioteca.DAO.LivroDAO;
import biblioteca.classes.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class TelaLivro extends JFrame {

    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JTextField txtISBN;

    private JTable tabela;
    private DefaultTableModel modelo;

    private LivroDAO dao = new LivroDAO();

    public TelaLivro() {

        setTitle("Gerenciamento de Livros");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 5, 5));

        txtId = new JTextField();
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAno = new JTextField();
        txtISBN = new JTextField();

        txtId.setEditable(false);

        painelCampos.add(new JLabel("ID"));
        painelCampos.add(txtId);

        painelCampos.add(new JLabel("Título"));
        painelCampos.add(txtTitulo);

        painelCampos.add(new JLabel("Autor"));
        painelCampos.add(txtAutor);

        painelCampos.add(new JLabel("Ano"));
        painelCampos.add(txtAno);

        painelCampos.add(new JLabel("ISBN"));
        painelCampos.add(txtISBN);

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
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("Ano");
        modelo.addColumn("ISBN");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        setLayout(new BorderLayout());

        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        btnSalvar.addActionListener(e -> salvarLivro());

        btnAtualizar.addActionListener(e -> atualizarLivro());

        btnExcluir.addActionListener(e -> excluirLivro());

        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tabela.getSelectedRow() != -1) {

                txtId.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 0
                        ).toString()
                );

                txtTitulo.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 1
                        ).toString()
                );

                txtAutor.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 2
                        ).toString()
                );

                txtAno.setText(
                        modelo.getValueAt(
                                tabela.getSelectedRow(), 3
                        ).toString()
                );

                txtISBN.setText(
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

            for (String[] livro : dao.listarTodos()) {

                modelo.addRow(livro);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void salvarLivro() {

        try {

            if (txtTitulo.getText().trim().isEmpty()
                    || txtAutor.getText().trim().isEmpty()
                    || txtAno.getText().trim().isEmpty()
                    || txtISBN.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos antes de salvar."
                );

                return;
            }

            Livro livro = new Livro(
                    txtTitulo.getText(),
                    txtAutor.getText(),
                    Integer.parseInt(txtAno.getText()),
                    Arrays.asList(
                            "Programação",
                            "Tecnologia",
                            "Educação"
                    ),
                    txtISBN.getText()
            );

            dao.salvar(livro);

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Livro salvo com sucesso!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }


    private void atualizarLivro() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um livro."
                );

                return;
            }

            dao.atualizar(
                    Integer.parseInt(txtId.getText()),
                    txtTitulo.getText(),
                    txtAutor.getText(),
                    Integer.parseInt(txtAno.getText()),
                    txtISBN.getText()
            );

            carregarTabela();
            limparCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Livro atualizado!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void excluirLivro() {

        try {

            if (txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um livro para excluir."
                );

                return;
            }

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este livro?",
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
                    "Livro excluído com sucesso!"
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
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtISBN.setText("");
    }
}
