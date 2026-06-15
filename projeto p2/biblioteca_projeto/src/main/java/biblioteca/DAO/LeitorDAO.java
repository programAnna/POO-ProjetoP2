package biblioteca.DAO;

import biblioteca.classes.Leitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class LeitorDAO {

    public void salvar(Leitor leitor) throws SQLException {

        String sql =
                "INSERT INTO leitor(nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, leitor.getNome());
            stmt.setString(2, leitor.getCpf());
            stmt.setString(3, leitor.getTelefone());
            stmt.setString(4, leitor.getEmail());

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos() throws SQLException {

        List<String[]> leitores = new ArrayList<>();

        String sql = "SELECT * FROM leitor";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                leitores.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email")
                });
            }
        }

        return leitores;
    }

    public void atualizar(int id,
                           String nome,
                           String cpf,
                           String telefone,
                           String email) throws SQLException {

        String sql =
                "UPDATE leitor SET nome=?, cpf=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setInt(5, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM leitor WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public java.util.List<String> listarNomes() throws SQLException {

        java.util.List<String> leitores = new java.util.ArrayList<>();

        String sql = "SELECT nome FROM leitor";

        try (Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                leitores.add(
                        rs.getString("nome")
                );
            }
        }

        return leitores;
    }
}