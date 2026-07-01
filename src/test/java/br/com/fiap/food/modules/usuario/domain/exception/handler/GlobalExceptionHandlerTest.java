package br.com.fiap.food.modules.usuario.domain.exception.handler;

import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleJaCadastrado() {
        String nome = "ADMIN";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        ProblemDetail problemDetail = exceptionHandler.handleJaCadastrado(exception);

        assertNotNull(problemDetail);
        assertEquals(HttpStatus.CONFLICT.value(), problemDetail.getStatus());
        assertEquals("Tipo de Usuário já cadastrado", problemDetail.getTitle());
        assertTrue(problemDetail.getDetail().contains(nome));
    }

    @Test
    void testHandleTipoNaoEncontrado() {
        Long id = 1L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        ProblemDetail problemDetail = exceptionHandler.handleTipoNaoEncontrado(exception);

        assertNotNull(problemDetail);
        assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus());
        assertEquals("Tipo de Usuário não encontrado", problemDetail.getTitle());
        assertTrue(problemDetail.getDetail().contains(id.toString()));
    }

    @Test
    void testHandleUsuarioNaoEncontrado() {
        Long id = 5L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        ProblemDetail problemDetail = exceptionHandler.handleUsuarioNaoEncontrado(exception);

        assertNotNull(problemDetail);
        assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus());
        assertEquals("Usuário não encontrado", problemDetail.getTitle());
        assertTrue(problemDetail.getDetail().contains(id.toString()));
    }

    @Test
    void testHandleValidationErrors() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = mock(FieldError.class);

        when(fieldError.getField()).thenReturn("email");
        when(fieldError.getDefaultMessage()).thenReturn("Email inválido");
        when(bindingResult.getFieldErrors()).thenReturn(java.util.List.of(fieldError));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ProblemDetail problemDetail = exceptionHandler.handleValidationErrors(exception);

        assertNotNull(problemDetail);
        assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus());
        assertEquals("Erro de validação", problemDetail.getTitle());
        assertEquals("Um ou mais campos são inválidos", problemDetail.getDetail());
    }

    @Test
    void testHandleJaCadastradoComNomeDiferente() {
        String nome = "USER";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        ProblemDetail problemDetail = exceptionHandler.handleJaCadastrado(exception);

        assertTrue(problemDetail.getDetail().contains("USER"));
        assertTrue(problemDetail.getDetail().contains("já cadastrado"));
    }

    @Test
    void testHandleTipoNaoEncontradoComIdDiferente() {
        Long id = 999L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        ProblemDetail problemDetail = exceptionHandler.handleTipoNaoEncontrado(exception);

        assertTrue(problemDetail.getDetail().contains("999"));
    }

    @Test
    void testHandleUsuarioNaoEncontradoComIdDiferente() {
        Long id = 123L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        ProblemDetail problemDetail = exceptionHandler.handleUsuarioNaoEncontrado(exception);

        assertTrue(problemDetail.getDetail().contains("123"));
    }

    @Test
    void testHandleValidationErrorsMultiplosCampos() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError1 = mock(FieldError.class);
        FieldError fieldError2 = mock(FieldError.class);

        when(fieldError1.getField()).thenReturn("nome");
        when(fieldError1.getDefaultMessage()).thenReturn("Nome obrigatório");
        when(fieldError2.getField()).thenReturn("email");
        when(fieldError2.getDefaultMessage()).thenReturn("Email obrigatório");

        when(bindingResult.getFieldErrors())
                .thenReturn(java.util.List.of(fieldError1, fieldError2));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ProblemDetail problemDetail = exceptionHandler.handleValidationErrors(exception);

        assertNotNull(problemDetail);
        assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus());
    }

    @Test
    void testHandleJaCadastradoStatusCode() {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException("ADMIN");
        ProblemDetail problemDetail = exceptionHandler.handleJaCadastrado(exception);

        assertEquals(HttpStatus.CONFLICT.value(), problemDetail.getStatus());
    }

    @Test
    void testHandleTipoNaoEncontradoStatusCode() {
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(1L);
        ProblemDetail problemDetail = exceptionHandler.handleTipoNaoEncontrado(exception);

        assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus());
    }

    @Test
    void testHandleUsuarioNaoEncontradoStatusCode() {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(1L);
        ProblemDetail problemDetail = exceptionHandler.handleUsuarioNaoEncontrado(exception);

        assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus());
    }

    @Test
    void testHandleValidationErrorsStatusCode() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(java.util.List.of());
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ProblemDetail problemDetail = exceptionHandler.handleValidationErrors(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus());
    }
}
