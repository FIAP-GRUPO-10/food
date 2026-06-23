package br.com.fiap.food.application.gateways;

import br.com.fiap.food.infrastructure.presentation.request.RestauranteRequest;
import br.com.fiap.food.infrastructure.presentation.response.RestauranteResponse;

import java.util.List;

public interface RestauranteGatewaySpec {

    RestauranteResponse criar(RestauranteRequest request);
    RestauranteResponse buscarPorId(Long id);
    List<RestauranteResponse> listarTodos();
    RestauranteResponse atualizar(Long id, RestauranteRequest request);
    void deletar(Long id);
}
