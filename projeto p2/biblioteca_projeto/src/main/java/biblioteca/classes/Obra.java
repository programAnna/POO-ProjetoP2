package biblioteca.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Obra {

    private static long proximoId = 1;

    private final long id;
    private String titulo;
    private String autor;
    private int ano;

    private final List<String> categorias = new ArrayList<>();

    public Obra(String titulo, String autor, int ano, List<String> categorias)
            throws BibliotecaException {

        synchronized (Obra.class) {
            this.id = proximoId++;
        }

        setTitulo(titulo);
        setAutor(autor);
        setAno(ano);
        setCategorias(categorias);
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public List<String> getCategorias() {
        return Collections.unmodifiableList(categorias);
    }

    public void setTitulo(String titulo) throws BibliotecaException {
        Validador.validarTextoObrigatorio(titulo, "título");
        this.titulo = titulo;
    }

    public void setAutor(String autor) throws BibliotecaException {
        Validador.validarTextoObrigatorio(autor, "autor");
        this.autor = autor;
    }

    public void setAno(int ano) throws BibliotecaException {

        if (ano <= 0) {
            throw new BibliotecaException(
                    BibliotecaException.TipoErro.DADOS_INVALIDOS,
                    "Ano inválido."
            );
        }

        this.ano = ano;
    }

    public void setCategorias(List<String> categorias)
            throws BibliotecaException {

        Validador.validarCategorias(categorias);

        this.categorias.clear();
        this.categorias.addAll(categorias);
    }

    public abstract String getTipoObra();
}