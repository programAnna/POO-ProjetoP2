package biblioteca.classes;

public abstract class Pessoa {
    private static long proximoId = 1;
    private final long id;
    private String nome, cpf, telefone, email;

    public Pessoa(String nome, String cpf, String telefone, String email) throws BibliotecaException {
        this.id = proximoId++;
        setNome(nome); setCpf(cpf); setTelefone(telefone); setEmail(email);
    }

    public void setNome(String n) throws BibliotecaException { Validador.validarTextoObrigatorio(n, "nome"); this.nome = n; }
    public void setCpf(String c) throws BibliotecaException { Validador.validarCpf(c); this.cpf = c; }
    public void setTelefone(String t) throws BibliotecaException { Validador.validarTelefone(t); this.telefone = t; }
    public void setEmail(String e) throws BibliotecaException { Validador.validarEmail(e); this.email = e; }
    public String getNome() { return nome; }
    public abstract String getTipo();

    public static long getProximoId() {
        return proximoId;
    }

    public static void setProximoId(long proximoId) {
        Pessoa.proximoId = proximoId;
    }

    public long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
