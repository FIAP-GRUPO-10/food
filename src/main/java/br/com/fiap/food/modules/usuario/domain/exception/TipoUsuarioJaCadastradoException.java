package br.com.fiap.food.modules.usuario.domain.exception;

import org.springframework.http.HttpStatus;

public class TipoUsuarioJaCadastradoException extends RuntimeException {

    private final String nome;
    private static final HttpStatus status = HttpStatus.CONFLICT;

    public TipoUsuarioJaCadastradoException(String nome) {
        super((nome == null || nome.trim().isEmpty())
                ? "Tipo de usuário já cadastrado:"
                : "Tipo de usuário já cadastrado: " + nome.replaceAll("\\s+$", ""));
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public HttpStatus getStatus() {
        return status;
    }
}