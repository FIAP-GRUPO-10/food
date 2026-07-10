package br.com.fiap.food.modules.itemCardapio.application.config;

import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import br.com.fiap.food.modules.restaurante.application.config.RestauranteConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestauranteConfig.class)
public class ItemCardapioConfigTest {

    @Autowired
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @Test
    void shouldLoadAllUseCasesAsBeans() {
        assertNotNull(criarItemCardapioUseCase);
    }
}
