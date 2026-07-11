package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuariosUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private ListarUsuariosUseCase listarUsuariosUseCase;

    @BeforeEach
    void setUp() {
        listarUsuariosUseCase = new ListarUsuariosUseCase(usuarioGateway);
    }

    @Test
    void testListarUsuariosComSucesso() {
        TipoUsuario tipoAdmin = new TipoUsuario(1L, "ADMIN", "Administrador");
        TipoUsuario tipoUser = new TipoUsuario(2L, "USER", "Usuário Normal");

        Usuario usuario1 = new Usuario(1L, "João Silva", "joao@email.com", tipoAdmin);
        Usuario usuario2 = new Usuario(2L, "Maria Santos", "maria@email.com", tipoUser);

        List<Usuario> usuariosEsperados = Arrays.asList(usuario1, usuario2);

        when(usuarioGateway.listarTodos()).thenReturn(usuariosEsperados);

        List<Usuario> resultado = listarUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
        assertEquals("Maria Santos", resultado.get(1).getNome());
        verify(usuarioGateway).listarTodos();
    }

    @Test
    void testListarUsuariosVazio() {
        when(usuarioGateway.listarTodos()).thenReturn(Collections.emptyList());

        List<Usuario> resultado = listarUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(usuarioGateway).listarTodos();
    }

    @Test
    void testListarUsuariosComUmUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(1L, "João Silva", "joao@email.com", tipoUsuario);

        List<Usuario> usuariosEsperados = Collections.singletonList(usuario);

        when(usuarioGateway.listarTodos()).thenReturn(usuariosEsperados);

        List<Usuario> resultado = listarUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
        verify(usuarioGateway).listarTodos();
    }
}
