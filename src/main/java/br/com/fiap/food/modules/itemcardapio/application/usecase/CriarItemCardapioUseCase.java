package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public CriarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public ItemCardapio execute(ItemCardapio itemCardapio) {
        Long restauranteId = itemCardapio.getRestaurante().getId();

        Restaurante restaurante = restauranteGateway.buscarPorId(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));

        itemCardapio.setRestaurante(restaurante);

        return itemCardapioGateway.salvar(itemCardapio);
    }
}