package br.com.fiap.food.application.gateways;

import br.com.fiap.food.infrastructure.presentation.request.ItemCardapioRequest;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;

import java.util.List;

public interface ItemCardapioGatewaySpec {

   ItemCardapioResponse criar(ItemCardapioRequest request);
   ItemCardapioResponse buscarPorId(Long id);
   List<ItemCardapioResponse> listarTodos();
   ItemCardapioResponse atualizar(Long id, ItemCardapioRequest request);
   void deletar(Long id);
}

