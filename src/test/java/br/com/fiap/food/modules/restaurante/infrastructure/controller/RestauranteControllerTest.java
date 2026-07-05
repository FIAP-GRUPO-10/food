package br.com.fiap.food.modules.restaurante.infrastructure.controller;

import br.com.fiap.food.modules.restaurante.application.usecase.*;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper.RestauranteApiMapper;
import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RestauranteApiMapper mapper;

    @MockitoBean
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @MockitoBean
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @MockitoBean
    private ListarRestaurantesUseCase listarRestaurantesUseCase;

    @MockitoBean
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @MockitoBean
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    private TipoUsuario buildTipoUsuario() {
        return new TipoUsuario(1L, "ADMIN", "Administrador");
    }

    private Usuario buildUsuario() {
        return new Usuario(1L, "Thiago", "thiago@email.com", buildTipoUsuario());
    }

    private UsuarioResponse buildUsuarioResponse() {
        return new UsuarioResponse(
                1L,
                "Thiago",
                "thiago@email.com",
                buildTipoUsuario()
        );
    }

    private Restaurante buildRestaurante() {
        return new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(23, 0),
                buildUsuario()
        );
    }

    private RestauranteRequest buildRequest() {
        return new RestauranteRequest(
                "Restaurante Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(23, 0),
                1L
        );
    }

    private RestauranteResponse buildResponse() {
        return new RestauranteResponse(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(23, 0),
                buildUsuarioResponse()
        );
    }

    @Test
    void deveCriarRestaurante() throws Exception {

        Restaurante restaurante = buildRestaurante();
        RestauranteRequest request = buildRequest();
        RestauranteResponse response = buildResponse();

        Mockito.when(mapper.toDomain(Mockito.any(RestauranteRequest.class)))
                .thenReturn(restaurante);

        Mockito.when(criarRestauranteUseCase.execute(Mockito.any(Restaurante.class)))
                .thenReturn(restaurante);

        Mockito.when(mapper.toResponse(Mockito.any(Restaurante.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/restaurante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Restaurante Fazenda"))
                .andExpect(jsonPath("$.tipoCozinha").value("Mexicana"));
    }

    @Test
    void deveBuscarRestaurantePorId() throws Exception {

        Restaurante restaurante = buildRestaurante();
        RestauranteResponse response = buildResponse();

        Mockito.when(buscarRestaurantePorIdUseCase.execute(1L))
                .thenReturn(restaurante);

        Mockito.when(mapper.toResponse(restaurante))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/restaurante/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Restaurante Fazenda"));
    }

    @Test
    void deveListarRestaurantes() throws Exception {

        Restaurante restaurante = buildRestaurante();
        RestauranteResponse response = buildResponse();

        Mockito.when(listarRestaurantesUseCase.execute())
                .thenReturn(List.of(restaurante));

        Mockito.when(mapper.toResponse(restaurante))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/restaurante"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Restaurante Fazenda"));
    }

    @Test
    void deveAtualizarRestaurante() throws Exception {

        Restaurante restaurante = buildRestaurante();
        RestauranteRequest request = buildRequest();
        RestauranteResponse response = buildResponse();

        Mockito.when(mapper.toDomain(Mockito.any(RestauranteRequest.class)))
                .thenReturn(restaurante);

        Mockito.when(atualizarRestauranteUseCase.execute(Mockito.eq(1L), Mockito.any(Restaurante.class)))
                .thenReturn(restaurante);

        Mockito.when(mapper.toResponse(Mockito.any(Restaurante.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/restaurante/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deveDeletarRestaurante() throws Exception {

        mockMvc.perform(delete("/api/v1/restaurante/1"))
                .andExpect(status().isNoContent());
    }
}