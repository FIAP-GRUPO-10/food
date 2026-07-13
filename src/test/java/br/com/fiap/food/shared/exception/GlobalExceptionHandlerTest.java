package br.com.fiap.food.shared.exception;

import br.com.fiap.food.modules.restaurante.domain.exception.HorarioInvalidoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteDuplicadoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteSemDonoException;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleJaCadastrado() {

        TipoUsuarioJaCadastradoException exception =
                new TipoUsuarioJaCadastradoException("ADMIN");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/tipo-usuario");

        ProblemDetail problemDetail =
                exceptionHandler.handleJaCadastrado(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.CONFLICT.value(), problemDetail.getStatus()),
                () -> assertEquals("Tipo de Usuário já cadastrado", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("ADMIN"))
        );
    }

    @Test
    void testHandleTipoNaoEncontrado() {

        TipoUsuarioNaoEncontradoException exception =
                new TipoUsuarioNaoEncontradoException(1L);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/tipo-usuario");

        ProblemDetail problemDetail =
                exceptionHandler.handleTipoNaoEncontrado(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus()),
                () -> assertEquals("Tipo de Usuário não encontrado", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("1"))
        );
    }

    @Test
    void testHandleUsuarioNaoEncontrado() {

        UsuarioNaoEncontradoException exception =
                new UsuarioNaoEncontradoException(5L);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/usuario");

        ProblemDetail problemDetail =
                exceptionHandler.handleUsuarioNaoEncontrado(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus()),
                () -> assertEquals("Usuário não encontrado", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("5"))
        );
    }

    @Test
    void testHandleRestauranteNaoEncontrado() {

        RestauranteNaoEncontradoException exception =
                new RestauranteNaoEncontradoException(10L);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/restaurante");

        ProblemDetail problemDetail =
                exceptionHandler.handleRestauranteNaoEncontrado(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), problemDetail.getStatus()),
                () -> assertEquals("Restaurante não encontrado", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("10"))
        );
    }

    @Test
    void testHandleHorarioInvalido() {

        HorarioInvalidoException exception =
                new HorarioInvalidoException("Horário de abertura deve ser menor que o de fechamento");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/restaurante");

        ProblemDetail problemDetail =
                exceptionHandler.handleHorarioInvalido(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus()),
                () -> assertEquals("Horário inválido", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("Horário"))
        );
    }

    @Test
    void testHandleRestauranteSemDono() {

        RestauranteSemDonoException exception =
                new RestauranteSemDonoException();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/restaurante");

        ProblemDetail problemDetail =
                exceptionHandler.handleRestauranteSemDono(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus()),
                () -> assertEquals("Restaurante sem dono", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("deve possuir um dono"))
        );
    }

    @Test
    void testHandleRestauranteDuplicado() {

        RestauranteDuplicadoException exception =
                new RestauranteDuplicadoException("McDonald's", "Rua A");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/restaurante");

        ProblemDetail problemDetail =
                exceptionHandler.handleRestauranteDuplicado(exception, request);

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus()),
                () -> assertEquals("Restaurante duplicado", problemDetail.getTitle()),
                () -> assertTrue(problemDetail.getDetail().contains("McDonald's")),
                () -> assertTrue(problemDetail.getDetail().contains("Rua A"))
        );
    }

    @Test
    void testHandleValidationErrors() {

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult =
                mock(BindingResult.class);

        FieldError nome =
                mock(FieldError.class);

        FieldError email =
                mock(FieldError.class);

        when(nome.getField()).thenReturn("nome");
        when(nome.getDefaultMessage()).thenReturn("Nome obrigatório");

        when(email.getField()).thenReturn("email");
        when(email.getDefaultMessage()).thenReturn("Email inválido");

        when(bindingResult.getFieldErrors())
                .thenReturn(List.of(nome, email));

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/usuario");

        ProblemDetail problemDetail =
                exceptionHandler.handleValidationErrors(exception, request);

        @SuppressWarnings("unchecked")
        Map<String, String> errors =
                (Map<String, String>) problemDetail.getProperties().get("errors");

        assertAll(
                () -> assertNotNull(problemDetail),
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), problemDetail.getStatus()),
                () -> assertEquals("Erro de validação", problemDetail.getTitle()),
                () -> assertEquals("Um ou mais campos são inválidos", problemDetail.getDetail()),
                () -> assertNotNull(errors),
                () -> assertEquals(2, errors.size()),
                () -> assertEquals("Nome obrigatório", errors.get("nome")),
                () -> assertEquals("Email inválido", errors.get("email"))
        );
    }
}