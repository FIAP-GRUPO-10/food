package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

import java.util.List;

public class ListarRestaurantesUseCase {

    private final RestauranteGateway gateway;

    public ListarRestaurantesUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public List<Restaurante> execute() {
        return gateway.listarTodos();
    }
}
