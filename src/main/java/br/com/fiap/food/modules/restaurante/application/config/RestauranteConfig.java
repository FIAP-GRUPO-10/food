package br.com.fiap.food.modules.restaurante.application.config;

import br.com.fiap.food.modules.restaurante.application.usecase.BuscarRestaurantePorIdUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.CriarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        return new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteGateway gateway) {
        return new BuscarRestaurantePorIdUseCase(gateway);
    }
}
