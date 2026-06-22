package br.com.fiap.food.application.usercases.ItemCardapio;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarItemCardapioUserCase {

    private final ItemCardapioGatewaySpec itemCardapioGatewaySpec;

    public ListarItemCardapioUserCase(ItemCardapioGatewaySpec itemCardapioGatewaySpec) {
        this.itemCardapioGatewaySpec = itemCardapioGatewaySpec;
    }

    public List<ItemCardapioResponse> listarTodos() {
        return this.itemCardapioGatewaySpec.listarTodos();
    }
}
