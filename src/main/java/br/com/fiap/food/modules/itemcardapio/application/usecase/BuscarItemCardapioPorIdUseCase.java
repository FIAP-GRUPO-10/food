package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;

public class BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway gateway;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway gateway) {
        this.gateway = gateway;
    }

    public ItemCardapio execute(Long id) {
        return gateway.buscarPorId(id)
                .orElseThrow(() -> new ItemCardapioNaoEncontradoException("Item do cardapio não encontrado"));
    }
}
