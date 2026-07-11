package br.com.fiap.food.modules.tipousuario.domain.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioJaCadastradoExceptionTest {

    @ParameterizedTest
    @CsvSource({
            "ADMIN, Tipo de usuário já cadastrado: ADMIN",
            "USER, Tipo de usuário já cadastrado: USER",
            "'', Tipo de usuário já cadastrado: ",
            "'  GUEST  ', Tipo de usuário já cadastrado:   GUEST  ",
            "SUPER_ADMIN_MODERATOR_USUARIO_ESPECIAL, Tipo de usuário já cadastrado: SUPER_ADMIN_MODERATOR_USUARIO_ESPECIAL",
            "ADMIN@#$%, Tipo de usuário já cadastrado: ADMIN@#$%"
    })
    void testExcecaoComVariadosNomes(String nome, String mensagemEsperada) {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertNotNull(exception);
        assertEquals(nome, exception.getNome());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "ADMIN, USER",
            "USER, ADMIN@#$%"
    })
    void testExcecaoComNomesDiferentes(String nome1, String nome2) {
        TipoUsuarioJaCadastradoException exception1 = new TipoUsuarioJaCadastradoException(nome1);
        TipoUsuarioJaCadastradoException exception2 = new TipoUsuarioJaCadastradoException(nome2);

        assertNotEquals(exception1.getNome(), exception2.getNome());
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"ADMIN", "USER"})
    void testExcecaoEstendeRuntimeException(String nome) {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @ParameterizedTest
    @CsvSource({"ADMIN", "USER"})
    void testStatusHttpConflito(String nome) {
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        assertEquals(409, exception.getStatus().value());
    }

    @Test
    void testExcecaoComNomeNuloOuVazio() {
        // Caso nome seja null
        TipoUsuarioJaCadastradoException exceptionNull = new TipoUsuarioJaCadastradoException(null);
        assertEquals("Tipo de usuário já cadastrado:", exceptionNull.getMessage());
        assertEquals(HttpStatus.CONFLICT, exceptionNull.getStatus());

        // Caso nome seja vazio ou apenas espaços
        TipoUsuarioJaCadastradoException exceptionVazio = new TipoUsuarioJaCadastradoException("   ");
        assertEquals("   ", exceptionVazio.getNome());
        assertEquals("Tipo de usuário já cadastrado:", exceptionVazio.getMessage());
        assertEquals(HttpStatus.CONFLICT, exceptionVazio.getStatus());
    }

    @Test
    void testExcecaoComNomeValido() {
        String nome = "ADMIN   "; // com espaços à direita
        TipoUsuarioJaCadastradoException exception = new TipoUsuarioJaCadastradoException(nome);

        // O nome armazenado é o original
        assertEquals(nome, exception.getNome());
        // A mensagem deve conter o nome sem espaços à direita
        assertEquals("Tipo de usuário já cadastrado: ADMIN", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }
}
