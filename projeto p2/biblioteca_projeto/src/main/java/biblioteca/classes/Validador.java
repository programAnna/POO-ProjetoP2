package biblioteca.classes;

import java.util.List;
import java.util.regex.Pattern;

public class Validador {
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\d{2}-\\d{4,5}-\\d{4}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern SENHA_MAIUSCULA_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern SENHA_NUMERO_PATTERN = Pattern.compile(".*[0-9].*");

    private Validador() {}

    public static void validarTextoObrigatorio(String valor, String campo) throws BibliotecaException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new BibliotecaException(BibliotecaException.TipoErro.DADOS_INVALIDOS, "O campo '" + campo + "' é obrigatório.");
        }
    }

    public static void validarCpf(String cpf) throws BibliotecaException {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            throw new BibliotecaException(BibliotecaException.TipoErro.CPF_INVALIDO, "CPF inválido.");
        }
    }

    public static void validarTelefone(String telefone) throws BibliotecaException {
        if (telefone == null || !TELEFONE_PATTERN.matcher(telefone).matches()) {
            throw new BibliotecaException(BibliotecaException.TipoErro.TELEFONE_INVALIDO, "Telefone inválido.");
        }
    }

    public static void validarEmail(String email) throws BibliotecaException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new BibliotecaException(BibliotecaException.TipoErro.EMAIL_INVALIDO, "Email inválido.");
        }
    }

    public static void validarSenha(String senha) throws BibliotecaException {
        if (senha == null || senha.length() < 6 || !SENHA_MAIUSCULA_PATTERN.matcher(senha).matches() || !SENHA_NUMERO_PATTERN.matcher(senha).matches()) {
            throw new BibliotecaException(BibliotecaException.TipoErro.SENHA_INVALIDA, "Senha deve ter 6+ caracteres, 1 maiúscula e 1 número.");
        }
    }

    public static void validarCategorias(List<String> categorias) throws BibliotecaException {
        if (categorias == null || categorias.size() < 3 || categorias.size() > 7) {
            throw new BibliotecaException(BibliotecaException.TipoErro.CATEGORIA_INVALIDA, "A obra deve ter entre 3 e 7 categorias.");
        }
    }
}
