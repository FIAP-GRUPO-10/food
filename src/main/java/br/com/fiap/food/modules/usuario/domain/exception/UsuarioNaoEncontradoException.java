package br.com.fiap.food.modules.usuario.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends RuntimeException {

    private final Long id;
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
