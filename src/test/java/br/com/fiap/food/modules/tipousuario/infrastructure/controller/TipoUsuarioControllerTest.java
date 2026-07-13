package br.com.fiap.food.modules.tipousuario.infrastructure.controller;

import br.com.fiap.food.modules.tipousuario.application.usecase.*;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.controller.dto.request.TipoUsuarioRequest;
import br.com.fiap.food.modules.tipousuario.infrastructure.controller.dto.response.TipoUsuarioResponse;
import br.com.fiap.food.modules.tipousuario.infrastructure.controller.mapper.TipoUsuarioApiMapper;
import br.com.fiap.food.modules.usuario.application.usecase.AtualizarTipoUsuarioDoUsuarioUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoUsuarioController.class)
class TipoUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean private TipoUsuarioApiMapper mapper;
    @MockitoBean private CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    @MockitoBean private BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;
    @MockitoBean private ListarTipoUsuariosUseCase listarTipoUsuariosUseCase;
    @MockitoBean private AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    @MockitoBean private DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    private TipoUsuario buildTipoUsuario(Long id, String nome, String observacao) {
        return new TipoUsuario(id, nome, observacao);
    }

    private TipoUsuarioResponse buildTipoUsuarioResponse(Long id, String nome, String observacao) {
        return new TipoUsuarioResponse(id, nome, observacao);
    }

    @Test
    void deveCriarTipoUsuario() throws Exception {
        TipoUsuarioRequest request = new TipoUsuarioRequest("ADMIN", "Administrador do sistema");
        TipoUsuario tipoUsuario = buildTipoUsuario(1L, "ADMIN", "Administrador do sistema");
        TipoUsuarioResponse response = buildTipoUsuarioResponse(1L, "ADMIN", "Administrador do sistema");

        Mockito.when(mapper.toDomain(Mockito.any(TipoUsuarioRequest.class))).thenReturn(tipoUsuario);
        Mockito.when(criarTipoUsuarioUseCase.execute(Mockito.any(TipoUsuario.class))).thenReturn(tipoUsuario);
        Mockito.when(mapper.toResponse(Mockito.any(TipoUsuario.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/tipo-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("ADMIN"))
                .andExpect(jsonPath("$.observacao").value("Administrador do sistema"));
    }

    @Test
    void deveBuscarTipoUsuarioPorId() throws Exception {
        TipoUsuario tipoUsuario = buildTipoUsuario(1L, "ADMIN", "Administrador do sistema");
        TipoUsuarioResponse response = buildTipoUsuarioResponse(1L, "ADMIN", "Administrador do sistema");

        Mockito.when(buscarTipoUsuarioPorIdUseCase.execute(Mockito.anyLong())).thenReturn(tipoUsuario);
        Mockito.when(mapper.toResponse(Mockito.any(TipoUsuario.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/tipo-usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("ADMIN"))
                .andExpect(jsonPath("$.observacao").value("Administrador do sistema"));
    }

    @Test
    void deveListarTipoUsuarios() throws Exception {
        TipoUsuario tipoUsuario = buildTipoUsuario(1L, "ADMIN", "Administrador do sistema");
        TipoUsuarioResponse response = buildTipoUsuarioResponse(1L, "ADMIN", "Administrador do sistema");

        Mockito.when(listarTipoUsuariosUseCase.execute()).thenReturn(List.of(tipoUsuario));
        Mockito.when(mapper.toResponse(Mockito.any(TipoUsuario.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/tipo-usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("ADMIN"))
                .andExpect(jsonPath("$[0].observacao").value("Administrador do sistema"));
    }

    @Test
    void deveAtualizarTipoUsuario() throws Exception {
        TipoUsuarioRequest request = new TipoUsuarioRequest("USER", "Usuário comum");
        TipoUsuario tipoUsuarioAtualizado = buildTipoUsuario(1L, "USER", "Usuário comum");
        TipoUsuarioResponse response = buildTipoUsuarioResponse(1L, "USER", "Usuário comum");

        Mockito.when(mapper.toDomain(Mockito.any(TipoUsuarioRequest.class))).thenReturn(tipoUsuarioAtualizado);
        Mockito.when(atualizarTipoUsuarioUseCase.execute(Mockito.anyLong(), Mockito.any(TipoUsuario.class))).thenReturn(tipoUsuarioAtualizado);
        Mockito.when(mapper.toResponse(Mockito.any(TipoUsuario.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/tipo-usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("USER"))
                .andExpect(jsonPath("$.observacao").value("Usuário comum"));
    }

    @Test
    void deveDeletarTipoUsuario() throws Exception {
        mockMvc.perform(delete("/api/v1/tipo-usuario/1"))
                .andExpect(status().isNoContent());
    }
}
