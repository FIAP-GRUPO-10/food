package br.com.fiap.food.modules.restaurante.application.config;

import br.com.fiap.food.modules.restaurante.application.usecase.*;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
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

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        return new AtualizarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Bean
    public DeletarRestauranteUseCase deletarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCase(restauranteGateway);
    }

    @Bean
    public ListarRestaurantesUseCase listarRestaurantesUseCase(RestauranteGateway restauranteGateway) {
        return new ListarRestaurantesUseCase(restauranteGateway);
    }
}
