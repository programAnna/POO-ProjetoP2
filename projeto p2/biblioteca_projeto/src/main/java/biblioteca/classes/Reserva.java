package biblioteca.classes;

public class Reserva extends Operacao {

    public Reserva(Usuario usuario, Copia copia) {
        super(usuario, copia);
    }

    @Override
    public void executar() throws BibliotecaException {

        copia.reservar(usuario);

        System.out.println(
            "Reserva: " +
            usuario.getNome() +
            " reservou " +
            copia.getObra().getTitulo()
        );
    }
}