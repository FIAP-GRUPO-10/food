package br.com.fiap.food.modules.restaurante.application.config;

import br.com.fiap.food.modules.restaurante.application.usecase.BuscarRestaurantePorIdUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.CriarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteGateway gateway) {
        return new CriarRestauranteUseCase(gateway);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteGateway gateway) {
        return new BuscarRestaurantePorIdUseCase(gateway);
    }
}
