package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private DeletarRestauranteUseCase useCase;

    @Test
    void deveDeletarRestaurante() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", null);
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(restaurante));

        useCase.execute(1L);

        verify(restauranteGateway).buscarPorId(1L);
        verify(restauranteGateway).deletar(1L);
        verifyNoMoreInteractions(restauranteGateway);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RestauranteNaoEncontradoException.class,
                () -> useCase.execute(1L)
        );

        verify(restauranteGateway).buscarPorId(1L);
        verify(restauranteGateway, never()).deletar(anyLong());
    }
}
