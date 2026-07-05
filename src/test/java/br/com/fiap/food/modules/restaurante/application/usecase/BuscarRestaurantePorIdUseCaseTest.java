package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarRestaurantePorIdUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private BuscarRestaurantePorIdUseCase useCase;

    @Test
    void deveBuscarRestaurantePorId() {

        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", null);

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

        Restaurante resultado = useCase.execute(1L);

        assertEquals(restaurante, resultado);

        verify(restauranteGateway).buscarPorId(1L);
    }
}
