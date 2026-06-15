package biblioteca.classes;

import java.time.LocalDateTime;

public abstract class Operacao {

    protected Usuario usuario;
    protected Copia copia;
    protected LocalDateTime dataHora;

    public Operacao(Usuario usuario, Copia copia) {
        this.usuario = usuario;
        this.copia = copia;
        this.dataHora = LocalDateTime.now();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public abstract void executar() throws BibliotecaException;
}