package br.com.fiap.food;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@SpringBootTest
class FoodApplicationTests {

    @Test
    void contextLoads() {
        // Testa se o contexto Spring Boot inicializa sem erros
    }

    @Test
    void mainMethodRuns() {
        // Verifica se o método main executa sem lançar exceções
        assertThatCode(() -> FoodApplication.main(new String[]{}))
                .doesNotThrowAnyException();
    }
}
