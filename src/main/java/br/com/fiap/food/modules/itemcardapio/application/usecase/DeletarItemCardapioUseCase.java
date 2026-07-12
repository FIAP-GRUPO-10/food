package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;

public class DeletarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public DeletarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway
    ) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public void execute(Long id) {
        itemCardapioGateway.buscarPorId(id)
                .orElseThrow(() ->
                        new ItemCardapioNaoEncontradoException(
                                "Item do cardápio não encontrado"
                        )
                );

        itemCardapioGateway.deletar(id);
    }
}
