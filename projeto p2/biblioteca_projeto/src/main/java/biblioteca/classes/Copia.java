package biblioteca.classes;

public class Copia {
    private static long proximoTombo = 1000;
    private final long tombo;
    
    // AGREGAÇÃO: A Cópia "tem" uma Obra.
    private final Obra obra;
    
    private StatusCopia status = StatusCopia.DISPONIVEL;
    private Usuario reserva;

    public Copia(Obra obra) throws BibliotecaException {
        if (obra == null) throw new BibliotecaException(BibliotecaException.TipoErro.DADOS_INVALIDOS, "Obra nula.");
        this.tombo = proximoTombo++;
        this.obra = obra;
    }

    public Obra getObra() { return obra; }
    public long getTombo() { return tombo; }
    public boolean disponivel() { return status == StatusCopia.DISPONIVEL; }
    public void emprestar(Usuario u) throws BibliotecaException {
        if (status == StatusCopia.EMPRESTADA) throw new BibliotecaException(BibliotecaException.TipoErro.ITEM_INDISPONIVEL, "Já emprestada.");
        if (status == StatusCopia.RESERVADA && reserva != u) throw new BibliotecaException(BibliotecaException.TipoErro.ITEM_INDISPONIVEL, "Reservada para outro.");
        this.status = StatusCopia.EMPRESTADA; this.reserva = null;
    }
    public void reservar(Usuario u) throws BibliotecaException {
        if (!disponivel()) throw new BibliotecaException(BibliotecaException.TipoErro.ITEM_INDISPONIVEL, "Indisponível.");
        this.status = StatusCopia.RESERVADA; this.reserva = u;
    }
    public void devolver() { this.status = StatusCopia.DISPONIVEL; this.reserva = null; }
    @Override public String toString() { return "Tombo " + tombo + " | " + obra.getTitulo() + " | " + status; }

    public StatusCopia getStatus() {
    return status;
    }
}
