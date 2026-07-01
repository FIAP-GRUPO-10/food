package br.com.fiap.food.modules.usuario.application.usecase.usuario;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioPorIdUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @BeforeEach
    void setUp() {
        buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdUseCase(usuarioGateway);
    }

    @Test
    void testBuscarUsuarioPorIdComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioEsperado = new Usuario(id, "João Silva", "joao@email.com", tipoUsuario);

        when(usuarioGateway.buscarPorId(id)).thenReturn(Optional.of(usuarioEsperado));

        Usuario resultado = buscarUsuarioPorIdUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        verify(usuarioGateway).buscarPorId(id);
    }

    @Test
    void testBuscarUsuarioPorIdNaoEncontrado() {
        Long id = 999L;

        when(usuarioGateway.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            buscarUsuarioPorIdUseCase.execute(id);
        });

        verify(usuarioGateway).buscarPorId(id);
    }

    @Test
    void testBuscarUsuarioPorIdComNomeVazio() {
        Long id = 2L;
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "USER", "Usuário Normal");
        Usuario usuarioEsperado = new Usuario(id, "", "email@email.com", tipoUsuario);

        when(usuarioGateway.buscarPorId(id)).thenReturn(Optional.of(usuarioEsperado));

        Usuario resultado = buscarUsuarioPorIdUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals("", resultado.getNome());
    }
}
