package biblioteca.classes;

public class Leitor extends Usuario {
    public Leitor(String n, String c, String t, String e, String s) throws BibliotecaException { super(n, c, t, e, s); }
    @Override public String getTipo() { return "Leitor"; }
    @Override public int getLimite() { return 3; }
    @Override public int getPrazo() { return 7; }
}
