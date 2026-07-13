package br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteApiMapperTest {

    private final RestauranteApiMapper mapper = Mappers.getMapper(RestauranteApiMapper.class);

    @Test
    void deveMapearRequestParaDomain() {
        RestauranteRequest request = new RestauranteRequest(
                "Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                1L
        );

        Restaurante restaurante = mapper.toDomain(request);

        assertEquals("Fazenda", restaurante.getNome());
        assertEquals("Rua A", restaurante.getEndereco());
        assertEquals("Mexicana", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), restaurante.getHorarioAbertura());
        assertEquals(1L, restaurante.getDono().getId());
    }

    @Test
    void deveMapearDomainParaResponse() {

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                1L,
                "Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                dono
        );

        RestauranteResponse response = mapper.toResponse(restaurante);

        // Restaurante
        assertEquals(1L, response.id());
        assertEquals("Fazenda", response.nome());
        assertEquals("Rua A", response.endereco());
        assertEquals("Mexicana", response.tipoCozinha());
        assertEquals(LocalTime.of(10, 0), response.horarioAbertura());
        assertEquals(LocalTime.of(22, 0), response.horarioFechamento());

        // Usuario (dono)
        assertNotNull(response.dono());
        assertEquals(1L, response.dono().id());
        assertEquals("Thiago", response.dono().nome());
        assertEquals("thiago@email.com", response.dono().email());

        // TipoUsuario
        assertNotNull(response.dono().tipoUsuario());
        assertEquals(1L, response.dono().tipoUsuario().getId());
        assertEquals("ADMIN", response.dono().tipoUsuario().getNome());
    }
}
