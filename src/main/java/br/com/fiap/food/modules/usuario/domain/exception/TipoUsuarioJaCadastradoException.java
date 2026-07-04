package br.com.fiap.food.modules.usuario.domain.exception;

import org.springframework.http.HttpStatus;

public class TipoUsuarioJaCadastradoException extends RuntimeException {

    private final String nome;
    private static final HttpStatus status = HttpStatus.CONFLICT;

    public TipoUsuarioJaCadastradoException(String nome) {
        super(buildMessage(nome));
        this.nome = nome;
    }

    private static String buildMessage(String nome) {
        String trimmed = nome == null ? "" : nome.trim();
        if (trimmed.isEmpty()) {
            return "Tipo de usuário já cadastrado:";
        }
        return "Tipo de usuário já cadastrado: " + trimmed;
    }

    public String getNome() {
        return nome;
    }

    public HttpStatus getStatus() {
        return status;
    }
}