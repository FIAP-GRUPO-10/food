package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.gateways.ItemCardapioGateway;
import org.springframework.stereotype.Service;

@Service
public class ListarItemCardapioUserCase {

    private final ItemCardapioGatewaySpec itemCardapioGatewaySpec;

    public ListarItemCardapioUserCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }
}
