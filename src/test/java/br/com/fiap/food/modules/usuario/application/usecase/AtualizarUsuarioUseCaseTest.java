package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        atualizarUsuarioUseCase = new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Test
    void testAtualizarUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioAtualizado = new Usuario(id, "João Silva Atualizado", "joao.novo@email.com", tipoUsuario);
        Usuario usuarioRetorno = new Usuario(id, "João Silva Atualizado", "joao.novo@email.com", tipoUsuario);

        when(usuarioGateway.atualizar(id, usuarioAtualizado)).thenReturn(usuarioRetorno);
        when(tipoUsuarioGateway.existsById(1L)).thenReturn(true);

        Usuario resultado = atualizarUsuarioUseCase.execute(id, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João Silva Atualizado", resultado.getNome());
        assertEquals("joao.novo@email.com", resultado.getEmail());
        verify(usuarioGateway).atualizar(id, usuarioAtualizado);
    }

    @Test
    void testAtualizarUsuarioComNovoEmail() {
        Long id = 1L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "USER", "Usuário Normal");
        Usuario usuarioAtualizado = new Usuario(id, "Maria", "maria.novo@email.com", tipoUsuario);
        Usuario usuarioRetorno = new Usuario(id, "Maria", "maria.novo@email.com", tipoUsuario);

        when(usuarioGateway.atualizar(id, usuarioAtualizado)).thenReturn(usuarioRetorno);
        when(tipoUsuarioGateway.existsById(1L)).thenReturn(true);

        Usuario resultado = atualizarUsuarioUseCase.execute(id, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("maria.novo@email.com", resultado.getEmail());
        verify(usuarioGateway).atualizar(id, usuarioAtualizado);
    }

    @Test
    void testAtualizarUsuarioComNovoNome() {
        Long id = 2L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioAtualizado = new Usuario(id, "Novo Nome", "email@email.com", tipoUsuario);
        Usuario usuarioRetorno = new Usuario(id, "Novo Nome", "email@email.com", tipoUsuario);

        when(usuarioGateway.atualizar(id, usuarioAtualizado)).thenReturn(usuarioRetorno);
        when(tipoUsuarioGateway.existsById(1L)).thenReturn(true);

        Usuario resultado = atualizarUsuarioUseCase.execute(id, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        verify(usuarioGateway).atualizar(id, usuarioAtualizado);
    }

    @Test
    void testDeveLancarExcecaoQuandoTipoUsuarioNaoExistir() {
        Long id = 1L;

        TipoUsuario tipoUsuario = new TipoUsuario(99L, null, null);

        Usuario usuario = new Usuario(id, "João", "joao@email.com", tipoUsuario);

        when(tipoUsuarioGateway.existsById(99L)).thenReturn(false);

        TipoUsuarioNaoEncontradoException exception =
                assertThrows(
                        TipoUsuarioNaoEncontradoException.class,
                        () -> atualizarUsuarioUseCase.execute(id, usuario)
                );

        assertTrue(exception.getMessage().contains("99"));

        verify(tipoUsuarioGateway).existsById(99L);
        verify(usuarioGateway, never()).atualizar(any(), any());
    }
}