package br.com.fiap.food.modules.usuario.domain.exception.handler;

import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoUsuarioJaCadastradoException.class)
    public ProblemDetail handleJaCadastrado(TipoUsuarioJaCadastradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Tipo de Usuário já cadastrado");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(TipoUsuarioNaoEncontradoException.class)
    public ProblemDetail handleTipoNaoEncontrado(TipoUsuarioNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Tipo de Usuário não encontrado");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Usuário não encontrado");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
}
