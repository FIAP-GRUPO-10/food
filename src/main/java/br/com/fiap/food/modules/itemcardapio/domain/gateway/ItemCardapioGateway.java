package br.com.fiap.food.modules.itemcardapio.domain.gateway;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioGateway {

    ItemCardapio salvar(ItemCardapio itemCardapio);
    Optional<ItemCardapio> buscarPorId(Long id);
    List<ItemCardapio> listarTodos();
    void deletar(Long id);
}