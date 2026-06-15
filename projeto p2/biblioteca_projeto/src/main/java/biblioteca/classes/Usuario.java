package biblioteca.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Usuario extends Pessoa {

    // Agregação: o usuário possui cópias emprestadas
    private final List<Copia> copiasEmprestadas = new ArrayList<>();

    private String senha;

    public Usuario(String nome,
                   String cpf,
                   String telefone,
                   String email,
                   String senha)
            throws BibliotecaException {

        super(nome, cpf, telefone, email);
        setSenha(senha);
    }

    public void setSenha(String senha)
            throws BibliotecaException {

        Validador.validarSenha(senha);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public abstract int getLimite();

    public abstract int getPrazo();

    public boolean podeEmprestar() {
        return copiasEmprestadas.size() < getLimite();
    }

    public void adicionarCopia(Copia copia) {
        copiasEmprestadas.add(copia);
    }

    public void removerCopia(Copia copia) {
        copiasEmprestadas.remove(copia);
    }

    public List<Copia> getEmprestimos() {
        return Collections.unmodifiableList(copiasEmprestadas);
    }

    public List<Copia> getCopiasEmprestadas() {
        return Collections.unmodifiableList(copiasEmprestadas);
    }
}