package biblioteca.classes;

public class Funcionario extends Pessoa {

    private String cargo;

    public Funcionario(
            String nome,
            String cpf,
            String telefone,
            String email,
            String cargo
    ) throws BibliotecaException {

        super(nome, cpf, telefone, email);
        this.cargo = cargo;
    }

    @Override
    public String getTipo() {
        return "Funcionário";
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
