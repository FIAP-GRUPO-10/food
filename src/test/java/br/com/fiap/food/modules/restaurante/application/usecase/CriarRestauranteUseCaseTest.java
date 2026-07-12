package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteDuplicadoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CriarRestauranteUseCase useCase;

    @Test
    void deveCriarRestauranteQuandoDadosForemValidos() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", null);
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                null,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                new Usuario(1L, null, null, null)
        );

        when(restauranteGateway.existePorNomeEEndereco("Restaurante Fazenda", "Rua A"))
                .thenReturn(false);

        when(usuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(dono));

        when(restauranteGateway.salvar(any(Restaurante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Restaurante resultado = useCase.execute(restaurante);

        assertNotNull(resultado);
        assertEquals("Restaurante Fazenda", resultado.getNome());
        assertEquals("Rua A", resultado.getEndereco());
        assertEquals(dono.getId(), resultado.getDono().getId());
        assertEquals(dono.getNome(), resultado.getDono().getNome());

        verify(restauranteGateway).existePorNomeEEndereco("Restaurante Fazenda", "Rua A");
        verify(usuarioGateway).buscarPorId(1L);
        verify(restauranteGateway).salvar(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteJaExistirNoMesmoEndereco() {
        Restaurante restaurante = new Restaurante(
                null,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                new Usuario(1L, null, null, null)
        );

        when(restauranteGateway.existePorNomeEEndereco("Restaurante Fazenda", "Rua A"))
                .thenReturn(true);

        RestauranteDuplicadoException exception = assertThrows(
                RestauranteDuplicadoException.class,
                () -> useCase.execute(restaurante)
        );

        assertEquals(
                "Restaurante: Restaurante Fazenda já cadastrado no endereço: Rua A",
                exception.getMessage()
        );

        verify(restauranteGateway).existePorNomeEEndereco("Restaurante Fazenda", "Rua A");
        verifyNoInteractions(usuarioGateway);
        verify(restauranteGateway, never()).salvar(any(Restaurante.class));
    }
}