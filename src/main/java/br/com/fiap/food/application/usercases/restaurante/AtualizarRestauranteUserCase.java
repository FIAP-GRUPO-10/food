package br.com.fiap.food.application.usercases.restaurante;

import br.com.fiap.food.application.gateways.RestauranteGatewaySpec;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUserCase {

    private final RestauranteGatewaySpec restauranteGatewaySpec;

    public AtualizarRestauranteUserCase(RestauranteGatewaySpec restauranteGatewaySpec) {
        this.restauranteGatewaySpec = restauranteGatewaySpec;
    }
}
