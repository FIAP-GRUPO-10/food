package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.request.ItemCardapioRequest;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;
import org.springframework.stereotype.Service;

@Service
public class CriarItemCardapioUseCase {

    private final ItemCardapioGatewaySpec itemCardapioGatewaySpec;

    public CriarItemCardapioUseCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }

    public ItemCardapioResponse criar(ItemCardapioRequest request) {
        return itemCardapioGatewaySpec.criar(request);
    }
}
