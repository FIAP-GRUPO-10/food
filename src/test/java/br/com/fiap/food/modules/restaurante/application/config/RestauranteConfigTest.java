package br.com.fiap.food.modules.restaurante.application.config;

import br.com.fiap.food.modules.restaurante.application.usecase.AtualizarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.BuscarRestaurantePorIdUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.CriarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.DeletarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestauranteConfig.class)
class RestauranteConfigTest {

    @Autowired
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @Autowired
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @Autowired
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Autowired
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @MockitoBean
    private RestauranteGateway restauranteGateway;

    @MockitoBean
    private UsuarioGateway usuarioGateway;

    @Test
    void shouldLoadAllUseCasesAsBeans() {
        assertNotNull(criarRestauranteUseCase);
        assertNotNull(buscarRestaurantePorIdUseCase);
        assertNotNull(atualizarRestauranteUseCase);
        assertNotNull(deletarRestauranteUseCase);
    }
}