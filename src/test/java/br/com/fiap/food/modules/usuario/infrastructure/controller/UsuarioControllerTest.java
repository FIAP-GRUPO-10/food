package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.application.usecase.*;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.UsuarioApiMapper;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean private UsuarioApiMapper mapper;
    @MockitoBean private CriarUsuarioUseCase criarUsuarioUseCase;
    @MockitoBean private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    @MockitoBean private ListarUsuariosUseCase listarUsuariosUseCase;
    @MockitoBean private AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    @MockitoBean private DeletarUsuarioUseCase deletarUsuarioUseCase;

    private TipoUsuario buildTipoUsuario() {
        return new TipoUsuario(10L, "ADMIN", "Administrador do sistema");
    }

    private Usuario buildUsuario(Long id, String nome, String email) {
        return new Usuario(id, nome, email, buildTipoUsuario());
    }

    private UsuarioResponse buildUsuarioResponse(Long id, String nome, String email) {
        return new UsuarioResponse(id, nome, email, buildTipoUsuario());
    }

    @Test
    void deveCriarUsuario() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Sandoval", "sandoval@email.com", buildTipoUsuario().getId());
        Usuario usuario = buildUsuario(1L, "Sandoval", "sandoval@email.com");
        UsuarioResponse response = buildUsuarioResponse(1L, "Sandoval", "sandoval@email.com");

        Mockito.when(mapper.toDomain(Mockito.any(UsuarioRequest.class))).thenReturn(usuario);
        Mockito.when(criarUsuarioUseCase.execute(Mockito.any(Usuario.class))).thenReturn(usuario);
        Mockito.when(mapper.toResponse(Mockito.any(Usuario.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Sandoval"))
                .andExpect(jsonPath("$.email").value("sandoval@email.com"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        Usuario usuario = buildUsuario(1L, "Sandoval", "sandoval@email.com");
        UsuarioResponse response = buildUsuarioResponse(1L, "Sandoval", "sandoval@email.com");

        Mockito.when(buscarUsuarioPorIdUseCase.execute(Mockito.anyLong())).thenReturn(usuario);
        Mockito.when(mapper.toResponse(Mockito.any(Usuario.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Sandoval"))
                .andExpect(jsonPath("$.email").value("sandoval@email.com"));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        Usuario usuario = buildUsuario(1L, "Sandoval", "sandoval@email.com");
        UsuarioResponse response = buildUsuarioResponse(1L, "Sandoval", "sandoval@email.com");

        Mockito.when(listarUsuariosUseCase.execute()).thenReturn(List.of(usuario));
        Mockito.when(mapper.toResponse(Mockito.any(Usuario.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Sandoval"))
                .andExpect(jsonPath("$[0].email").value("sandoval@email.com"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Sandoval Atualizado", "novo@email.com", buildTipoUsuario().getId());
        Usuario usuarioAtualizado = buildUsuario(1L, "Sandoval Atualizado", "novo@email.com");
        UsuarioResponse response = buildUsuarioResponse(1L, "Sandoval Atualizado", "novo@email.com");

        Mockito.when(mapper.toDomain(Mockito.any(UsuarioRequest.class))).thenReturn(usuarioAtualizado);
        Mockito.when(atualizarUsuarioUseCase.execute(Mockito.anyLong(), Mockito.any(Usuario.class))).thenReturn(usuarioAtualizado);
        Mockito.when(mapper.toResponse(Mockito.any(Usuario.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Sandoval Atualizado"))
                .andExpect(jsonPath("$.email").value("novo@email.com"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        mockMvc.perform(delete("/api/v1/usuario/1"))
                .andExpect(status().isNoContent());
    }
}
