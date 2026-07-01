package br.com.fiap.food.modules.usuario.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioNaoEncontradoExceptionTest {

    @Test
    void testCriarExcecaoComId() {
        Long id = 1L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        assertNotNull(exception);
        assertEquals(id, exception.getId());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testMensagemExcecao() {
        Long id = 5L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        String mensagemEsperada = "Usuário não encontrado com id: " + id;
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void testStatusHttpNaoEncontrado() {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(1L);

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(404, exception.getStatus().value());
    }

    @Test
    void testExcecaoComIdDiferente() {
        Long id1 = 10L;
        Long id2 = 20L;

        UsuarioNaoEncontradoException exception1 = new UsuarioNaoEncontradoException(id1);
        UsuarioNaoEncontradoException exception2 = new UsuarioNaoEncontradoException(id2);

        assertNotEquals(exception1.getId(), exception2.getId());
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    void testExcecaoEstendeRuntimeException() {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(1L);

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testExcecaoComIdNegativo() {
        Long id = -1L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains("-1"));
    }

    @Test
    void testExcecaoComIdZero() {
        Long id = 0L;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains("0"));
    }

    @Test
    void testExcecaoComIdGrande() {
        Long id = Long.MAX_VALUE;
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains(Long.MAX_VALUE + ""));
    }
}
