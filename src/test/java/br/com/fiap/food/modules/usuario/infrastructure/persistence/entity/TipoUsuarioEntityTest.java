package br.com.fiap.food.modules.usuario.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioEntityTest {

    @Test
    void testCriarTipoUsuarioEntityComBuilder() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(1L)
                .nome("ADMIN")
                .observacao("Administrador do Sistema")
                .build();

        assertNotNull(tipoUsuarioEntity);
        assertEquals(1L, tipoUsuarioEntity.getId());
        assertEquals("ADMIN", tipoUsuarioEntity.getNome());
        assertEquals("Administrador do Sistema", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testCriarTipoUsuarioEntityComConstrutorVazio() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();

        assertNull(tipoUsuarioEntity.getId());
        assertNull(tipoUsuarioEntity.getNome());
        assertNull(tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testCriarTipoUsuarioEntityComConstrutorCompleto() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(2L, "USER", "Usuário Normal");

        assertEquals(2L, tipoUsuarioEntity.getId());
        assertEquals("USER", tipoUsuarioEntity.getNome());
        assertEquals("Usuário Normal", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testSetarPropriedadesTipoUsuarioEntity() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();

        tipoUsuarioEntity.setId(3L);
        tipoUsuarioEntity.setNome("GUEST");
        tipoUsuarioEntity.setObservacao("Convidado");

        assertEquals(3L, tipoUsuarioEntity.getId());
        assertEquals("GUEST", tipoUsuarioEntity.getNome());
        assertEquals("Convidado", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testTipoUsuarioEntityComObservacaoVazia() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(1L)
                .nome("TEST")
                .observacao("")
                .build();

        assertEquals("", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testTipoUsuarioEntityComObservacaoNula() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(1L)
                .nome("TEST")
                .observacao(null)
                .build();

        assertNull(tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testTipoUsuarioEntityComNomeVazio() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(1L)
                .nome("")
                .observacao("Tipo com nome vazio")
                .build();

        assertEquals("", tipoUsuarioEntity.getNome());
    }

    @Test
    void testModificarIdDoTipoUsuarioEntity() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Admin");

        tipoUsuarioEntity.setId(5L);

        assertEquals(5L, tipoUsuarioEntity.getId());
    }
}
