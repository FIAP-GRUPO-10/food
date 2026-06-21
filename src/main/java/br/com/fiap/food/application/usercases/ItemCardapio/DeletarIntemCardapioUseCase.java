package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import org.springframework.stereotype.Service;

@Service
public class DeletarIntemCardapioUseCase {

    private final ItemCardapioGatewaySpec  itemCardapioGatewaySpec;

    public DeletarIntemCardapioUseCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }
}
