package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;
import org.springframework.stereotype.Service;

@Service
public class RecuperarPorIdItemCardapioUseCase {

    private final ItemCardapioGatewaySpec itemCardapioGatewaySpec;

    public RecuperarPorIdItemCardapioUseCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }

    public ItemCardapioResponse buscarPorId(Long id) {
        return this.itemCardapioGatewaySpec.buscarPorId(id);
    }
}
