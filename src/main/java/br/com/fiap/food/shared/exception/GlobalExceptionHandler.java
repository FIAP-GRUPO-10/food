package br.com.fiap.food.shared.exception;

import br.com.fiap.food.modules.restaurante.domain.exception.HorarioInvalidoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteDuplicadoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteSemDonoException;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoUsuarioJaCadastradoException.class)
    public ProblemDetail handleJaCadastrado(
            TipoUsuarioJaCadastradoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.CONFLICT,
                "Tipo de Usuário já cadastrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(TipoUsuarioNaoEncontradoException.class)
    public ProblemDetail handleTipoNaoEncontrado(
            TipoUsuarioNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.NOT_FOUND,
                "Tipo de Usuário não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    public ProblemDetail handleRestauranteNaoEncontrado(
            RestauranteNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.NOT_FOUND,
                "Restaurante não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(HorarioInvalidoException.class)
    public ProblemDetail handleHorarioInvalido(
            HorarioInvalidoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.BAD_REQUEST,
                "Horário inválido",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(RestauranteSemDonoException.class)
    public ProblemDetail handleRestauranteSemDono(
            RestauranteSemDonoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.BAD_REQUEST,
                "Restaurante sem dono",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(RestauranteDuplicadoException.class)
    public ProblemDetail handleRestauranteDuplicado(
            RestauranteDuplicadoException ex,
            HttpServletRequest request
    ) {
        return StandardError.create(
                HttpStatus.BAD_REQUEST,
                "Restaurante duplicado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ProblemDetail problemDetail = StandardError.create(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                "Um ou mais campos são inválidos",
                request.getRequestURI()
        );

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }
}