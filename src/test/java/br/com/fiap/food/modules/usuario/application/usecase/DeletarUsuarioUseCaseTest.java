package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        deletarUsuarioUseCase = new DeletarUsuarioUseCase(usuarioGateway);
    }

    @Test
    void testDeletarUsuarioComSucesso() {
        Long id = 1L;

        deletarUsuarioUseCase.execute(id);

        verify(usuarioGateway).deletar(id);
    }

    @Test
    void testDeletarUsuarioComIdDiferente() {
        Long id = 999L;

        deletarUsuarioUseCase.execute(id);

        verify(usuarioGateway).deletar(id);
    }

    @Test
    void testDeletarMultiplosUsuarios() {
        Long id1 = 1L;
        Long id2 = 2L;

        deletarUsuarioUseCase.execute(id1);
        deletarUsuarioUseCase.execute(id2);

        verify(usuarioGateway).deletar(id1);
        verify(usuarioGateway).deletar(id2);
    }
}
