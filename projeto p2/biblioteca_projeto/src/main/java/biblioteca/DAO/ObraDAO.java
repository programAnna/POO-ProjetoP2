package biblioteca.DAO;

import biblioteca.classes.Obra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObraDAO {

    public void salvar(Obra obra) throws SQLException {

        String sql =
                "INSERT INTO obra(titulo, autor, ano, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, obra.getTitulo());
            stmt.setString(2, obra.getAutor());
            stmt.setInt(3, obra.getAno());
            stmt.setString(4, obra.getTipoObra());

            stmt.executeUpdate();
        }
    }
}
