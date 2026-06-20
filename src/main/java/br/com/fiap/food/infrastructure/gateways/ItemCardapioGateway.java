package br.com.fiap.food.infrastructure.gateways;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.request.ItemCardapioRequest;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;

import java.util.List;

public class ItemCardapioGateway  implements ItemCardapioGatewaySpec {

    @Override
    public ItemCardapioResponse criar(ItemCardapioRequest request) {
        return null;
    }

    @Override
    public ItemCardapioResponse buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<ItemCardapioResponse> listarTodos() {
        return List.of();
    }

    @Override
    public ItemCardapioResponse atualizar(Long id, ItemCardapioRequest request) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
