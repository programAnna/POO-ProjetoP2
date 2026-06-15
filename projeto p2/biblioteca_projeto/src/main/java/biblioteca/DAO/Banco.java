package biblioteca.DAO;

import java.sql.Connection;
import java.sql.Statement;

public class Banco {

    public static void criarTabelas() {

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS leitor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cpf TEXT NOT NULL,
                    telefone TEXT,
                    email TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS funcionario (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cpf TEXT NOT NULL,
                    telefone TEXT,
                    email TEXT,
                    cargo TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS livro (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    autor TEXT NOT NULL,
                    ano INTEGER,
                    isbn TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS copia (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tombo INTEGER,
                    titulo TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS emprestimo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario TEXT,
                    obra TEXT,
                    data_emprestimo TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS reserva (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    usuario TEXT,
                    obra TEXT,
                    data_reserva TEXT
                )
            """);

            System.out.println("Tabelas criadas com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}