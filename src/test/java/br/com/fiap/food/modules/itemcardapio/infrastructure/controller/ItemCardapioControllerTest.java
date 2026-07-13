package br.com.fiap.food.modules.itemcardapio.infrastructure.controller;

import br.com.fiap.food.modules.itemcardapio.application.usecase.AtualizarItemUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.DeletarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper.ItemCardapioApiMapper;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemCardapioController.class)
class ItemCardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ItemCardapioApiMapper mapper;

    @MockitoBean
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @MockitoBean
    private BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    @MockitoBean
    private AtualizarItemUseCase atualizarItemCardapioUseCase;

    @MockitoBean
    private DeletarItemCardapioUseCase deletarItemCardapioUseCase;

    private ItemCardapio buildItemCardapio(Long id) {
        return new ItemCardapio(
                id,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                Restaurante.referenciaPorId(1L)
        );
    }

    private ItemCardapioResponse buildItemCardapioResponse(Long id) {
        RestauranteResponse restauranteResponse = new RestauranteResponse(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                null,
                null,
                null
        );

        return new ItemCardapioResponse(
                id,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                restauranteResponse
        );
    }

    @Test
    void deveCriarItemCardapio() throws Exception {
        ItemCardapioRequest request = new ItemCardapioRequest(
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                1L
        );

        ItemCardapio itemCardapio = buildItemCardapio(1L);
        ItemCardapioResponse response = buildItemCardapioResponse(1L);

        Mockito.when(mapper.toDomain(Mockito.any(ItemCardapioRequest.class)))
                .thenReturn(itemCardapio);

        Mockito.when(criarItemCardapioUseCase.execute(Mockito.any(ItemCardapio.class)))
                .thenReturn(itemCardapio);

        Mockito.when(mapper.toResponse(Mockito.any(ItemCardapio.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/item-cardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Pizza Portuguesa"))
                .andExpect(jsonPath("$.descricao").value("Pizza com presunto, ovos e cebola"))
                .andExpect(jsonPath("$.preco").value(49.90))
                .andExpect(jsonPath("$.somenteNoLocal").value(false))
                .andExpect(jsonPath("$.caminhoFoto").value("/img/pizza.png"))
                .andExpect(jsonPath("$.restaurante.id").value(1L))
                .andExpect(jsonPath("$.restaurante.nome").value("Restaurante Fazenda"));
    }

    @Test
    void deveBuscarItemCardapioPorId() throws Exception {
        ItemCardapio itemCardapio = buildItemCardapio(1L);
        ItemCardapioResponse response = buildItemCardapioResponse(1L);

        Mockito.when(buscarItemCardapioPorIdUseCase.execute(1L))
                .thenReturn(itemCardapio);

        Mockito.when(mapper.toResponse(Mockito.any(ItemCardapio.class)))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/item-cardapio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Pizza Portuguesa"))
                .andExpect(jsonPath("$.restaurante.id").value(1L))
                .andExpect(jsonPath("$.restaurante.nome").value("Restaurante Fazenda"));
    }

    @Test
    void deveAtualizarItemCardapio() throws Exception {
        ItemCardapioRequest request = new ItemCardapioRequest(
                "Pizza Portuguesa",
                "Pizza com mais queijo",
                59.90,
                true,
                "/img/pizza-atualizada.png",
                1L
        );

        ItemCardapio itemCardapioAtualizado = new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com mais queijo",
                59.90,
                true,
                "/img/pizza-atualizada.png",
                Restaurante.referenciaPorId(1L)
        );

        RestauranteResponse restauranteResponse = new RestauranteResponse(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                null,
                null,
                null
        );

        ItemCardapioResponse response = new ItemCardapioResponse(
                1L,
                "Pizza Portuguesa",
                "Pizza com mais queijo",
                59.90,
                true,
                "/img/pizza-atualizada.png",
                restauranteResponse
        );

        Mockito.when(mapper.toDomain(Mockito.any(ItemCardapioRequest.class)))
                .thenReturn(itemCardapioAtualizado);

        Mockito.when(
                atualizarItemCardapioUseCase.execute(
                        Mockito.eq(1L),
                        Mockito.any(ItemCardapio.class)
                )
        ).thenReturn(itemCardapioAtualizado);

        Mockito.when(mapper.toResponse(Mockito.any(ItemCardapio.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/item-cardapio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descricao").value("Pizza com mais queijo"))
                .andExpect(jsonPath("$.preco").value(59.90))
                .andExpect(jsonPath("$.somenteNoLocal").value(true))
                .andExpect(jsonPath("$.caminhoFoto").value("/img/pizza-atualizada.png"))
                .andExpect(jsonPath("$.restaurante.id").value(1L));
    }

    @Test
    void deveDeletarItemCardapio() throws Exception {
        mockMvc.perform(delete("/api/v1/item-cardapio/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(deletarItemCardapioUseCase)
                .execute(1L);
    }
}