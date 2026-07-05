package br.com.fiap.food.modules.usuario.infrastructure.controller.mapper;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRequestTest {


    private final UsuarioApiMapper mapper = Mappers.getMapper(UsuarioApiMapper.class);

    @Test
    void testToTipoUsuarioComIdNulo() {
        TipoUsuario tipoUsuario = mapper.toTipoUsuario(null);

        assertNull(tipoUsuario, "Quando o id é nulo, deve retornar null");
    }

    @Test
    void testToTipoUsuarioComIdValido() {
        Long id = 10L;
        TipoUsuario tipoUsuario = mapper.toTipoUsuario(id);

        assertNotNull(tipoUsuario, "Deve retornar um objeto TipoUsuario");
        assertEquals(id, tipoUsuario.getId(), "O id deve ser atribuído corretamente");
        assertNull(tipoUsuario.getNome(), "Nome deve ser null");
    }
}
