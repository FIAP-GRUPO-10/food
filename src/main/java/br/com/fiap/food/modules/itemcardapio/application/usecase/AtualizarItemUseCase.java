package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;

public class AtualizarItemUseCase {
    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public AtualizarItemUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public ItemCardapio execute(Long id, ItemCardapio itemCardapio) {
        itemCardapioGateway.buscarPorId(id)
                .orElseThrow(() ->
                        new ItemCardapioNaoEncontradoException(
                                "Item do cardápio não encontrado"
                        )
                );

        Long restauranteId = itemCardapio.getRestaurante().getId();

        Restaurante restaurante = restauranteGateway
                .buscarPorId(restauranteId)
                .orElseThrow(() ->
                        new RestauranteNaoEncontradoException(restauranteId)
                );

        itemCardapio.setId(id);
        itemCardapio.setRestaurante(restaurante);

        return itemCardapioGateway.salvar(itemCardapio);
    }
}
