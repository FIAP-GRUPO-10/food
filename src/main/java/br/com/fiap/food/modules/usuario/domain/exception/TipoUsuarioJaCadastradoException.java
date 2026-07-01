package br.com.fiap.food.modules.usuario.domain.exception;

import org.springframework.http.HttpStatus;

public class TipoUsuarioJaCadastradoException extends RuntimeException {

    private final String nome;
    private final HttpStatus status = HttpStatus.CONFLICT;

    public TipoUsuarioJaCadastradoException(String nome) {
        super("Tipo de usuário já cadastrado: " + nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public HttpStatus getStatus() {
        return status;
    }
}