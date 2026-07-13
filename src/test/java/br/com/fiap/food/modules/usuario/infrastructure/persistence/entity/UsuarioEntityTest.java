package br.com.fiap.food.modules.usuario.infrastructure.persistence.entity;

import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    void testCriarUsuarioEntityComBuilder() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Administrador");
        
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .nome("João Silva")
                .email("joao@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        assertNotNull(usuarioEntity);
        assertEquals(1L, usuarioEntity.getId());
        assertEquals("João Silva", usuarioEntity.getNome());
        assertEquals("joao@email.com", usuarioEntity.getEmail());
        assertEquals(tipoUsuarioEntity, usuarioEntity.getTipoUsuario());
    }

    @Test
    void testCriarUsuarioEntityComConstrutorVazio() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        assertNull(usuarioEntity.getId());
        assertNull(usuarioEntity.getNome());
        assertNull(usuarioEntity.getEmail());
        assertNull(usuarioEntity.getTipoUsuario());
    }

    @Test
    void testCriarUsuarioEntityComConstrutorCompleto() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "USER", "Usuário Normal");
        UsuarioEntity usuarioEntity = new UsuarioEntity(2L, "Maria", "maria@email.com", tipoUsuarioEntity);

        assertEquals(2L, usuarioEntity.getId());
        assertEquals("Maria", usuarioEntity.getNome());
        assertEquals("maria@email.com", usuarioEntity.getEmail());
        assertEquals(tipoUsuarioEntity, usuarioEntity.getTipoUsuario());
    }

    @Test
    void testSetarPropriedadesUsuarioEntity() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Administrador");
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setId(3L);
        usuarioEntity.setNome("Pedro Silva");
        usuarioEntity.setEmail("pedro@email.com");
        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);

        assertEquals(3L, usuarioEntity.getId());
        assertEquals("Pedro Silva", usuarioEntity.getNome());
        assertEquals("pedro@email.com", usuarioEntity.getEmail());
        assertEquals(tipoUsuarioEntity, usuarioEntity.getTipoUsuario());
    }

    @Test
    void testUsuarioEntityComEmailVazio() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Administrador");
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .nome("João")
                .email("")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        assertEquals("", usuarioEntity.getEmail());
    }

    @Test
    void testUsuarioEntityComNomeVazio() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Administrador");
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .nome("")
                .email("email@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        assertEquals("", usuarioEntity.getNome());
    }
}
