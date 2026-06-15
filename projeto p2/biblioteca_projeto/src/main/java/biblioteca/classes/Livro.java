package biblioteca.classes;

import java.util.List;

public class Livro extends Obra {

    private String isbn;

    public Livro(String titulo,
                 String autor,
                 int ano,
                 List<String> categorias,
                 String isbn) throws BibliotecaException {

        super(titulo, autor, ano, categorias);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws BibliotecaException {

        Validador.validarTextoObrigatorio(isbn, "ISBN");
        this.isbn = isbn;
    }

    @Override
    public String getTipoObra() {
        return "Livro";
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", ano=" + getAno() +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}