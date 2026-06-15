package biblioteca;

import biblioteca.DAO.Banco;
import biblioteca.DAO.LivroDAO;
import biblioteca.DAO.LeitorDAO;
import biblioteca.DAO.EmprestimoDAO;
import biblioteca.DAO.ReservaDAO;

import biblioteca.classes.*;

import java.util.Arrays;
import java.util.List;

public class MainTeste {

    public static void main(String[] args) {

        Banco.criarTabelas();

        try {

            System.out.println("=== SISTEMA DE BIBLIOTECA ===\n");

            Bibliotecapoo biblioteca = new Bibliotecapoo("Biblioteca Central");

            List<String> categorias = Arrays.asList(
                    "Programação",
                    "Tecnologia",
                    "Educação"
            );

            // LIVRO 1
            Livro livro = new Livro(
                    "Java Orientado a Objetos",
                    "Deitel",
                    2024,
                    categorias,
                    "978-123456789"
            );

            LivroDAO livroDAO = new LivroDAO();
            livroDAO.salvar(livro);

            System.out.println("Livro 1 salvo no banco!");

            // LIVRO 2
            Livro livro2 = new Livro(
                    "Banco de Dados",
                    "Silberschatz",
                    2023,
                    categorias,
                    "978-987654321"
            );

            livroDAO.salvar(livro2);

            System.out.println("Livro 2 salvo no banco!");

            biblioteca.cadastrarObra(livro, 2);
            biblioteca.cadastrarObra(livro2, 1);

            // LEITOR 1
            Leitor leitor = new Leitor(
                    "Ana Silva",
                    "123.456.789-00",
                    "11-99999-8888",
                    "ana@email.com",
                    "Senha123"
            );

            LeitorDAO leitorDAO = new LeitorDAO();
            leitorDAO.salvar(leitor);

            System.out.println("Leitor 1 salvo no banco!");

            // LEITOR 2
            Leitor leitor2 = new Leitor(
                    "Carlos Souza",
                    "987.654.321-00",
                    "11-98888-7777",
                    "carlos@email.com",
                    "Senha456"
            );

            leitorDAO.salvar(leitor2);

            System.out.println("Leitor 2 salvo no banco!");

            System.out.println("Leitor cadastrado: " + leitor.getNome());

            Copia copia = biblioteca.buscar("Java Orientado a Objetos")
                    .orElseThrow(() -> new Exception("Livro não encontrado"));

            System.out.println("\nCópia encontrada:");
            System.out.println(copia);

            // EMPRÉSTIMO
            Operacao emprestimo = new Emprestimo(leitor, copia);
            biblioteca.processar(emprestimo);

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.salvar(
                    leitor.getNome(),
                    copia.getObra().getTitulo(),
                    java.time.LocalDate.now().toString()
            );

            System.out.println("Empréstimo salvo no banco!");

            // Teste de erro
            try {

                System.out.println("\nTentando emprestar novamente...");

                biblioteca.processar(
                        new Emprestimo(leitor, copia)
                );

            } catch (BibliotecaException e) {

                System.out.println(
                        "Erro esperado: " + e.getMessage()
                );
            }

            // DEVOLUÇÃO
            biblioteca.processar(
                    new Devolucao(leitor, copia)
            );

            // RESERVA
            biblioteca.processar(
                    new Reserva(leitor, copia)
            );

            ReservaDAO reservaDAO = new ReservaDAO();

            reservaDAO.salvar(
                    leitor.getNome(),
                    copia.getObra().getTitulo(),
                    java.time.LocalDate.now().toString()
            );

            System.out.println("Reserva salva no banco!");

            System.out.println("\n=== HISTÓRICO ===");

            for (Operacao op : biblioteca.getHistorico()) {

                System.out.println(
                        op.getClass().getSimpleName()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "\nErro: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}
