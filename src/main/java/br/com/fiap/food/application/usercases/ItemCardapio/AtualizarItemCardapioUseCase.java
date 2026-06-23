package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.request.ItemCardapioRequest;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;
import org.springframework.stereotype.Service;

@Service
public class AtualizarItemCardapioUseCase {

    private final ItemCardapioGatewaySpec itemCardapioGatewaySpec;

    public AtualizarItemCardapioUseCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }

    public ItemCardapioResponse atualizar(Long id, ItemCardapioRequest request) {
        return  this.itemCardapioGatewaySpec.atualizar(id, request);
    }
}
