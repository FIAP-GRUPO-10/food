package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
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
class BuscarTipoUsuarioPorIdUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;

    @BeforeEach
    void setUp() {
        buscarTipoUsuarioPorIdUseCase = new BuscarTipoUsuarioPorIdUseCase(tipoUsuarioGateway);
    }

    @Test
    void testBuscarTipoUsuarioPorIdComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuarioEsperado = new TipoUsuario(id, "ADMIN", "Administrador do Sistema");

        when(tipoUsuarioGateway.buscarPorId(id)).thenReturn(Optional.of(tipoUsuarioEsperado));

        TipoUsuario resultado = buscarTipoUsuarioPorIdUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("ADMIN", resultado.getNome());
        assertEquals("Administrador do Sistema", resultado.getObservacao());
        verify(tipoUsuarioGateway).buscarPorId(id);
    }

    @Test
    void testBuscarTipoUsuarioPorIdNaoEncontrado() {
        Long id = 999L;

        when(tipoUsuarioGateway.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            buscarTipoUsuarioPorIdUseCase.execute(id);
        });

        verify(tipoUsuarioGateway).buscarPorId(id);
    }

    @Test
    void testBuscarTipoUsuarioPorIdComTipoUSER() {
        Long id = 2L;
        TipoUsuario tipoUsuarioEsperado = new TipoUsuario(id, "USER", "Usuário Normal");

        when(tipoUsuarioGateway.buscarPorId(id)).thenReturn(Optional.of(tipoUsuarioEsperado));

        TipoUsuario resultado = buscarTipoUsuarioPorIdUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals("USER", resultado.getNome());
    }
}
