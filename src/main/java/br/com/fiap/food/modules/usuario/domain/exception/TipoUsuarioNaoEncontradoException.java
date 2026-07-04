package br.com.fiap.food.modules.usuario.domain.exception;

import org.springframework.http.HttpStatus;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {

    private final Long id;
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public TipoUsuarioNaoEncontradoException(Long id) {
        super("Tipo de usuário não encontrado com id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
