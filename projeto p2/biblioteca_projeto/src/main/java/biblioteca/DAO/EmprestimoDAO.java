package biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public void salvar(String usuario,
                       String obra,
                       String dataEmprestimo)
            throws SQLException {

        String sql =
                "INSERT INTO emprestimo(usuario, obra, data_emprestimo) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, obra);
            stmt.setString(3, dataEmprestimo);

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos() throws SQLException {

        List<String[]> emprestimos = new ArrayList<>();

        String sql = "SELECT * FROM emprestimo";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                emprestimos.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("usuario"),
                        rs.getString("obra"),
                        rs.getString("data_emprestimo")
                });
            }
        }

        return emprestimos;
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM emprestimo WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}
