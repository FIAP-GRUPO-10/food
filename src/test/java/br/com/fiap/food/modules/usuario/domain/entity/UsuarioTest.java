package br.com.fiap.food.modules.usuario.domain.entity;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testCriarUsuarioComTodosOsDados() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(1L, "João Silva", "joao@email.com", tipoUsuario);

        assertNotNull(usuario);
        assertEquals(1L, usuario.getId());
        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(tipoUsuario, usuario.getTipoUsuario());
    }

    @Test
    void testCriarUsuarioComIdNulo() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "USER", "Usuário Normal");
        Usuario usuario = new Usuario(null, "Maria", "maria@email.com", tipoUsuario);

        assertNull(usuario.getId());
        assertEquals("Maria", usuario.getNome());
    }

    @Test
    void testSetarIdDoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(null, "João Silva", "joao@email.com", tipoUsuario);

        usuario.setId(1L);

        assertEquals(1L, usuario.getId());
    }

    @Test
    void testGettersDoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(2L, "GUEST", "Convidado");
        Usuario usuario = new Usuario(5L, "Pedro", "pedro@email.com", tipoUsuario);

        assertEquals(5L, usuario.getId());
        assertEquals("Pedro", usuario.getNome());
        assertEquals("pedro@email.com", usuario.getEmail());
        assertEquals(tipoUsuario, usuario.getTipoUsuario());
    }

    @Test
    void testCriarUsuarioComEmailVazio() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(1L, "João", "", tipoUsuario);

        assertEquals("", usuario.getEmail());
    }

    @Test
    void testCriarUsuarioComNomeVazio() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(1L, "", "email@email.com", tipoUsuario);

        assertEquals("", usuario.getNome());
    }
}
