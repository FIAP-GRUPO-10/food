package br.com.fiap.food.modules.tipousuario.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioNaoEncontradoExceptionTest {

    @Test
    void testCriarExcecaoComId() {
        Long id = 1L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        assertNotNull(exception);
        assertEquals(id, exception.getId());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testMensagemExcecao() {
        Long id = 5L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        String mensagemEsperada = "Tipo de usuário não encontrado com id: " + id;
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void testStatusHttpNaoEncontrado() {
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(1L);

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(404, exception.getStatus().value());
    }

    @Test
    void testExcecaoComIdDiferente() {
        Long id1 = 10L;
        Long id2 = 20L;

        TipoUsuarioNaoEncontradoException exception1 = new TipoUsuarioNaoEncontradoException(id1);
        TipoUsuarioNaoEncontradoException exception2 = new TipoUsuarioNaoEncontradoException(id2);

        assertNotEquals(exception1.getId(), exception2.getId());
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    void testExcecaoEstendeRuntimeException() {
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(1L);

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testExcecaoComIdNegativo() {
        Long id = -1L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains("-1"));
    }

    @Test
    void testExcecaoComIdZero() {
        Long id = 0L;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains("0"));
    }

    @Test
    void testExcecaoComIdGrande() {
        Long id = Long.MAX_VALUE;
        TipoUsuarioNaoEncontradoException exception = new TipoUsuarioNaoEncontradoException(id);

        assertEquals(id, exception.getId());
        assertTrue(exception.getMessage().contains(Long.MAX_VALUE + ""));
    }
}
