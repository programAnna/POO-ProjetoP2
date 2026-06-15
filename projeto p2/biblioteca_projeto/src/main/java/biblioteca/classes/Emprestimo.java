package biblioteca.classes;

public class Emprestimo extends Operacao {

    public Emprestimo(Usuario u, Copia c) {
        super(u, c);
    }

    @Override
    public void executar() throws BibliotecaException {
        if (!usuario.podeEmprestar()) {
            throw new BibliotecaException(
                BibliotecaException.TipoErro.LIMITE_EXCEDIDO,
                "Limite atingido."
            );
        }

        copia.emprestar(usuario);
        usuario.adicionarCopia(copia);

        System.out.println(
            "Empréstimo: " +
            usuario.getNome() +
            " retirou " +
            copia.getObra().getTitulo()
        );
    }
}

