package br.com.fiap.food.modules.usuario.application.usecase.usuario;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        atualizarUsuarioUseCase = new AtualizarUsuarioUseCase(usuarioGateway);
    }

    @Test
    void testAtualizarUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioAtualizado = new Usuario(id, "João Silva Atualizado", "joao.novo@email.com", tipoUsuario);
        Usuario usuarioRetorno = new Usuario(id, "João Silva Atualizado", "joao.novo@email.com", tipoUsuario);

        when(usuarioGateway.atualizar(id, usuarioAtualizado)).thenReturn(usuarioRetorno);

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

        Usuario resultado = atualizarUsuarioUseCase.execute(id, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        verify(usuarioGateway).atualizar(id, usuarioAtualizado);
    }
}
