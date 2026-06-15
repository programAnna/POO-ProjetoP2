package biblioteca.DAO;

import biblioteca.classes.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario)
            throws SQLException {

        String sql =
                "INSERT INTO funcionario(nome, cpf, telefone, email, cargo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getCargo());

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos()
            throws SQLException {

        List<String[]> funcionarios =
                new ArrayList<>();

        String sql =
                "SELECT * FROM funcionario";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                funcionarios.add(
                        new String[]{
                                String.valueOf(
                                        rs.getInt("id")
                                ),
                                rs.getString("nome"),
                                rs.getString("cpf"),
                                rs.getString("telefone"),
                                rs.getString("email"),
                                rs.getString("cargo")
                        }
                );
            }
        }

        return funcionarios;
    }

    public void atualizar(
            int id,
            String nome,
            String cpf,
            String telefone,
            String email,
            String cargo
    ) throws SQLException {

        String sql =
                "UPDATE funcionario SET nome=?, cpf=?, telefone=?, email=?, cargo=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, cargo);
            stmt.setInt(6, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id)
            throws SQLException {

        String sql =
                "DELETE FROM funcionario WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public List<String> listarNomes()
            throws SQLException {

        List<String> nomes =
                new ArrayList<>();

        String sql =
                "SELECT nome FROM funcionario";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                nomes.add(
                        rs.getString("nome")
                );
            }
        }

        return nomes;
    }
}