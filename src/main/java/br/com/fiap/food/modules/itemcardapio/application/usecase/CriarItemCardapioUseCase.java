package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;

public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway gateway;

    public CriarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.gateway = itemCardapioGateway;
    }

    public ItemCardapio execute(ItemCardapio restaurante) {
        return gateway.salvar(restaurante);
    }
}
