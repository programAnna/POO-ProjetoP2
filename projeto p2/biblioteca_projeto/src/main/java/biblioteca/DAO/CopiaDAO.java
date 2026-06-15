package biblioteca.DAO;

import biblioteca.classes.Copia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CopiaDAO {

    public void salvar(Copia copia) throws SQLException {

        String sql =
                "INSERT INTO copia(tombo, titulo) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, copia.getTombo());
            stmt.setString(2, copia.getObra().getTitulo());

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos() throws SQLException {

        List<String[]> copias = new ArrayList<>();

        String sql = "SELECT * FROM copia";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                copias.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getLong("tombo")),
                        rs.getString("titulo")
                });
            }
        }

        return copias;
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM copia WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}