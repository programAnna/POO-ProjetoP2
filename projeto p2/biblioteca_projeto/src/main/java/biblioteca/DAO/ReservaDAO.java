package biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void salvar(String usuario,
                       String obra,
                       String dataReserva)
            throws SQLException {

        String sql =
                "INSERT INTO reserva(usuario, obra, data_reserva) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, obra);
            stmt.setString(3, dataReserva);

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos() throws SQLException {

        List<String[]> reservas = new ArrayList<>();

        String sql = "SELECT * FROM reserva";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                reservas.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("usuario"),
                        rs.getString("obra"),
                        rs.getString("data_reserva")
                });
            }
        }

        return reservas;
    }

    public void atualizar(int id,
                          String usuario,
                          String obra,
                          String dataReserva)
            throws SQLException {

        String sql =
                "UPDATE reserva SET usuario=?, obra=?, data_reserva=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, obra);
            stmt.setString(3, dataReserva);
            stmt.setInt(4, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM reserva WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}