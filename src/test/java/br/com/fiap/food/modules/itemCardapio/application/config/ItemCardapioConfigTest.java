package br.com.fiap.food.modules.itemCardapio.application.config;

import br.com.fiap.food.modules.itemcardapio.application.config.ItemCardapioConfig;
import br.com.fiap.food.modules.itemcardapio.application.usecase.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ItemCardapioConfig.class)
class ItemCardapioConfigTest {

    @Autowired
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @Autowired
    private BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    @MockitoBean
    private ItemCardapioGateway itemCardapioGateway;

    @MockitoBean
    private RestauranteGateway restauranteGateway;

    @Test
    void shouldLoadAllUseCasesAsBeans() {
        assertNotNull(criarItemCardapioUseCase);
        assertNotNull(buscarItemCardapioPorIdUseCase);
    }
}