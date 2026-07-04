package br.com.fiap.food.modules.usuario.infrastructure.controller.mapper;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRequestTest {

    @Test
    void testCriacaoUsuarioRequest() {
        String nome = "João";
        String email = "joao@teste.com";
        Long tipoUsuarioId = 1L;

        UsuarioRequest request = new UsuarioRequest(nome, email, tipoUsuarioId);

        assertNotNull(request);
        assertEquals(nome, request.nome());
        assertEquals(email, request.email());
        assertEquals(tipoUsuarioId, request.tipoUsuario());
    }

    @Test
    void testUsuarioRequestComValoresNulos() {
        UsuarioRequest request = new UsuarioRequest(null, null, null);

        assertNull(request.nome());
        assertNull(request.email());
        assertNull(request.tipoUsuario());
    }
}
