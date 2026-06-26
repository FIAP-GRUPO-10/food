package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

public class CriarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public CriarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.gateway = restauranteGateway;
    }

    public Restaurante execute(Restaurante restaurante) {
        return gateway.salvar(restaurante);
    }
}
