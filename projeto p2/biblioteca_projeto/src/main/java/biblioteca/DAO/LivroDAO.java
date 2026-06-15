package biblioteca.DAO;

import biblioteca.classes.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void salvar(Livro livro) throws SQLException {

        String sql =
                "INSERT INTO livro(titulo, autor, ano, isbn) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getIsbn());

            stmt.executeUpdate();
        }
    }

    public List<String[]> listarTodos() throws SQLException {

        List<String[]> livros = new ArrayList<>();

        String sql = "SELECT * FROM livro";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                livros.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        String.valueOf(rs.getInt("ano")),
                        rs.getString("isbn")
                });
            }
        }

        return livros;
    }

    public void atualizar(int id,
                          String titulo,
                          String autor,
                          int ano,
                          String isbn) throws SQLException {

        String sql =
                "UPDATE livro SET titulo=?, autor=?, ano=?, isbn=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, ano);
            stmt.setString(4, isbn);
            stmt.setInt(5, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM livro WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public List<String> listarTitulos() throws SQLException {

        List<String> livros = new ArrayList<>();

        String sql = "SELECT titulo FROM livro";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                livros.add(
                        rs.getString("titulo")
                );
            }
        }

        return livros;
    }

    public Livro buscarPorTitulo(String titulo) throws Exception {

        String sql =
                "SELECT * FROM livro WHERE titulo=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        java.util.Arrays.asList(
                                "Programação",
                                "Tecnologia",
                                "Educação"
                        ),
                        rs.getString("isbn")
                );
            }
        }

        return null;
    }
}