package br.com.fiap.food.modules.usuario.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioJaCadastradoExceptionTest {

    @Test
    void testCriarExcecaoComNome() {
        String nome = "ADMIN";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertNotNull(exception);
        assertEquals(nome, exception.getNome());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }

    @Test
    void testMensagemExcecao() {
        String nome = "USER";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        String mensagemEsperada = "Tipo de usuário já cadastrado: " + nome;
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void testStatusHttpConflito() {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException("ADMIN");

        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        assertEquals(409, exception.getStatus().value());
    }

    @Test
    void testExcecaoComNomeDiferente() {
        String nome1 = "ADMIN";
        String nome2 = "USER";

        TipoUsuarioJaCadastradoException exception1 = new TipoUsuarioJaCadastradoException(nome1);
        TipoUsuarioJaCadastradoException exception2 = new TipoUsuarioJaCadastradoException(nome2);

        assertNotEquals(exception1.getNome(), exception2.getNome());
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    void testExcecaoEstendeRuntimeException() {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException("ADMIN");

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testExcecaoComNomeVazio() {
        String nome = "";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertEquals(nome, exception.getNome());
        assertTrue(exception.getMessage().contains("já cadastrado"));
    }

    @Test
    void testExcecaoComNomeComEspacos() {
        String nome = "  GUEST  ";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertEquals(nome, exception.getNome());
        assertTrue(exception.getMessage().contains(nome));
    }

    @Test
    void testExcecaoComNomeLongo() {
        String nome = "SUPER_ADMIN_MODERATOR_USUARIO_ESPECIAL";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertEquals(nome, exception.getNome());
        assertTrue(exception.getMessage().contains(nome));
    }

    @Test
    void testExcecaoComNomeEspecial() {
        String nome = "ADMIN@#$%";
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertEquals(nome, exception.getNome());
        assertTrue(exception.getMessage().contains(nome));
    }
}
