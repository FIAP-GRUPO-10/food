package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

public class DeletarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public DeletarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public void execute(Long id) {
        Restaurante restaurante =
                restauranteGateway.buscarPorId(id)
                        .orElseThrow(() -> new RestauranteNaoEncontradoException(id));

        restauranteGateway.deletar(restaurante.getId());

    }
}
