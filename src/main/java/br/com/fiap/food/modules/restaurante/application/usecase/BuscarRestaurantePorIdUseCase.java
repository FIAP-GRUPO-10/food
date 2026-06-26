package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

public class BuscarRestaurantePorIdUseCase {

    private final RestauranteGateway gateway;

    public BuscarRestaurantePorIdUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public Restaurante execute(Long id) {
        return gateway.buscarPorId(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException("Restaurante não encontrado"));
    }
}
