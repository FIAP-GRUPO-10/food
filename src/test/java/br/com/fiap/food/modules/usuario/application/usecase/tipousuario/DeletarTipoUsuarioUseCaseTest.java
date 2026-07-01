package br.com.fiap.food.modules.usuario.application.usecase.tipousuario;

import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    @BeforeEach
    void setUp() {
        deletarTipoUsuarioUseCase = new DeletarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Test
    void testDeletarTipoUsuarioComSucesso() {
        Long id = 1L;

        when(tipoUsuarioGateway.existsById(id)).thenReturn(true);

        deletarTipoUsuarioUseCase.execute(id);

        verify(tipoUsuarioGateway).existsById(id);
        verify(tipoUsuarioGateway).deletar(id);
    }

    @Test
    void testDeletarTipoUsuarioNaoEncontrado() {
        Long id = 999L;

        when(tipoUsuarioGateway.existsById(id)).thenReturn(false);

        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            deletarTipoUsuarioUseCase.execute(id);
        });

        verify(tipoUsuarioGateway).existsById(id);
    }

    @Test
    void testDeletarTipoUsuarioComIdValido() {
        Long id = 5L;

        when(tipoUsuarioGateway.existsById(id)).thenReturn(true);

        deletarTipoUsuarioUseCase.execute(id);

        verify(tipoUsuarioGateway).deletar(id);
    }
}
