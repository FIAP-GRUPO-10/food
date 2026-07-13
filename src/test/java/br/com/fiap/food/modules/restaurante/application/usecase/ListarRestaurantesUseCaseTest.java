package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarRestaurantesUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private ListarRestaurantesUseCase useCase;

    @Test
    void deveListarTodosRestaurantes() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", null);
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);
        Usuario dono2 = new Usuario(2L, "Pedro", "pedro@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        Restaurante restaurante2 = new Restaurante(
                1L,
                "Restaurante Caipira",
                "Rua B",
                "Mineira",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono2
        );

        when(restauranteGateway.listarTodos()).thenReturn(List.of(restaurante, restaurante2));

        List<Restaurante> resultado = useCase.execute();
        assertEquals(2, resultado.size());
        assertEquals("Restaurante Fazenda", resultado.getFirst().getNome());
        assertEquals(dono, resultado.getFirst().getDono());

        verify(restauranteGateway).listarTodos();

    }
}
