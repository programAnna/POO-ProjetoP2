package biblioteca.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Bibliotecapoo {

    private String nome;

    // Composição
    private final List<Obra> obras;
    private final List<Copia> copias;
    private final List<Operacao> historico;

    public Bibliotecapoo(String nome) throws BibliotecaException {

        Validador.validarTextoObrigatorio(nome, "nome da biblioteca");

        this.nome = nome;
        this.obras = new ArrayList<>();
        this.copias = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public void cadastrarObra(Obra obra, int quantidade)
            throws BibliotecaException {

        if (obra == null) {
            throw new BibliotecaException(
                    BibliotecaException.TipoErro.DADOS_INVALIDOS,
                    "Obra não pode ser nula."
            );
        }

        if (quantidade <= 0) {
            throw new BibliotecaException(
                    BibliotecaException.TipoErro.DADOS_INVALIDOS,
                    "Quantidade inválida."
            );
        }

        obras.add(obra);

        for (int i = 0; i < quantidade; i++) {
            copias.add(new Copia(obra));
        }
    }

    public Optional<Copia> buscar(String titulo) {

        return copias.stream()
                .filter(c -> c.disponivel()
                        && c.getObra()
                        .getTitulo()
                        .equalsIgnoreCase(titulo))
                .findFirst();
    }

    public void processar(Operacao operacao)
            throws BibliotecaException {

        operacao.executar();
        historico.add(operacao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)
            throws BibliotecaException {

        Validador.validarTextoObrigatorio(nome, "nome da biblioteca");
        this.nome = nome;
    }

    public List<Obra> getObras() {
        return Collections.unmodifiableList(obras);
    }

    public List<Copia> getCopias() {
        return Collections.unmodifiableList(copias);
    }

    public List<Operacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }
}