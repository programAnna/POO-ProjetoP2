package biblioteca.classes;

public class Devolucao extends Operacao {

    public Devolucao(Usuario u, Copia c) {
        super(u, c);
    }

    @Override
    public void executar() throws BibliotecaException {

        if (!usuario.getEmprestimos().contains(copia)) {
            throw new BibliotecaException(
                BibliotecaException.TipoErro.OPERACAO_INVALIDA,
                "Não pertence ao usuário."
            );
        }

        copia.devolver();
        usuario.removerCopia(copia);

        System.out.println(
            "Devolução: " +
            usuario.getNome() +
            " devolveu " +
            copia.getObra().getTitulo()
        );
    }
}
