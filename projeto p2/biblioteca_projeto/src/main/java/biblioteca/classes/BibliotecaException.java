package biblioteca.classes;

public class BibliotecaException extends Exception {
    public enum TipoErro {
        DADOS_INVALIDOS, CPF_INVALIDO, TELEFONE_INVALIDO, 
        EMAIL_INVALIDO, SENHA_INVALIDA, CATEGORIA_INVALIDA, 
        ITEM_INDISPONIVEL, OPERACAO_INVALIDA, LIMITE_EXCEDIDO
    }

    private final TipoErro tipo;

    public BibliotecaException(TipoErro tipo, String mensagem) {
        super(mensagem);
        this.tipo = tipo;
    }

    public TipoErro getTipo() {
        return tipo;
    }
}