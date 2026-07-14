package br.com.fiap.food.modules.itemCardapio.infrastructure.controller.mapper;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper.ItemCardapioApiMapper;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioApiMapperTest {

    private final ItemCardapioApiMapper mapper =
            Mappers.getMapper(ItemCardapioApiMapper.class);

    @Test
    void deveMapearRequestParaDomain() {
        ItemCardapioRequest request = new ItemCardapioRequest(
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                1L
        );

        ItemCardapio itemCardapio = mapper.toDomain(request);

        assertNotNull(itemCardapio);
        assertNull(itemCardapio.getId());
        assertEquals("Pizza Portuguesa", itemCardapio.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                itemCardapio.getDescricao()
        );
        assertEquals(49.90, itemCardapio.getPreco(), 0.001);
        assertFalse(itemCardapio.isSomenteNoLocal());
        assertEquals(
                "/img/pizza.png",
                itemCardapio.getCaminhoFoto()
        );

        assertNotNull(itemCardapio.getRestaurante());
        assertEquals(
                1L,
                itemCardapio.getRestaurante().getId()
        );
    }

    @Test
    void deveMapearDomainParaResponse() {
        TipoUsuario tipoUsuario = new TipoUsuario(
                1L,
                "ADMIN",
                "Administrador"
        );

        Usuario dono = new Usuario(
                1L,
                "Luiz",
                "luiz@email.com",
                tipoUsuario
        );

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        ItemCardapio itemCardapio = new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                restaurante
        );

        ItemCardapioResponse response =
                mapper.toResponse(itemCardapio);

        // Item do cardápio
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Pizza Portuguesa", response.nome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                response.descricao()
        );
        assertEquals(49.90, response.preco(), 0.001);
        assertFalse(response.somenteNoLocal());
        assertEquals(
                "/img/pizza.png",
                response.caminhoFoto()
        );

        // Restaurante
        assertNotNull(response.restaurante());
        assertEquals(1L, response.restaurante().id());
        assertEquals(
                "Restaurante Fazenda",
                response.restaurante().nome()
        );
        assertEquals(
                "Rua A",
                response.restaurante().endereco()
        );
        assertEquals(
                "Italiana",
                response.restaurante().tipoCozinha()
        );
        assertEquals(
                LocalTime.of(8, 0),
                response.restaurante().horarioAbertura()
        );
        assertEquals(
                LocalTime.of(22, 0),
                response.restaurante().horarioFechamento()
        );

        // Dono
        assertNotNull(response.restaurante().dono());
        assertEquals(
                1L,
                response.restaurante().dono().id()
        );
        assertEquals(
                "Luiz",
                response.restaurante().dono().nome()
        );
        assertEquals(
                "luiz@email.com",
                response.restaurante().dono().email()
        );

        // Tipo de usuário
        assertNotNull(
                response.restaurante()
                        .dono()
                        .tipoUsuario()
        );

        assertEquals(
                1L,
                response.restaurante()
                        .dono()
                        .tipoUsuario()
                        .getId()
        );

        assertEquals(
                "ADMIN",
                response.restaurante()
                        .dono()
                        .tipoUsuario()
                        .getNome()
        );
    }
}