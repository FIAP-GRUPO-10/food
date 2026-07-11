package br.com.fiap.food.modules.tipousuario.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void testCriarTipoUsuarioComTodosOsDados() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador do Sistema");

        assertNotNull(tipoUsuario);
        assertEquals(1L, tipoUsuario.getId());
        assertEquals("ADMIN", tipoUsuario.getNome());
        assertEquals("Administrador do Sistema", tipoUsuario.getObservacao());
    }

    @Test
    void testCriarTipoUsuarioComIdNulo() {
        TipoUsuario tipoUsuario = new TipoUsuario(null, "USER", "Usuário Normal");

        assertNull(tipoUsuario.getId());
        assertEquals("USER", tipoUsuario.getNome());
    }

    @Test
    void testSetarIdDoTipoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(null, "GUEST", "Convidado");

        tipoUsuario.setId(5L);

        assertEquals(5L, tipoUsuario.getId());
    }

    @Test
    void testGettersDoTipoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(10L, "MODERATOR", "Moderador");

        assertEquals(10L, tipoUsuario.getId());
        assertEquals("MODERATOR", tipoUsuario.getNome());
        assertEquals("Moderador", tipoUsuario.getObservacao());
    }

    @Test
    void testCriarTipoUsuarioComObservacaoVazia() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "TEST", "");

        assertEquals("", tipoUsuario.getObservacao());
    }

    @Test
    void testCriarTipoUsuarioComNomeVazio() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "", "Tipo com nome vazio");

        assertEquals("", tipoUsuario.getNome());
    }

    @Test
    void testModificarIdDoTipoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");

        tipoUsuario.setId(2L);

        assertEquals(2L, tipoUsuario.getId());
    }
}
