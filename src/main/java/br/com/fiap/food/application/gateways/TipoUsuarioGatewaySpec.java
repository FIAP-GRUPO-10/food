package br.com.fiap.food.application.gateways;

import br.com.fiap.food.infrastructure.presentation.request.TipoUsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.TipoUsuarioResponse;

import java.util.List;

public interface TipoUsuarioGatewaySpec {

    TipoUsuarioResponse criar(TipoUsuarioRequest request);
    TipoUsuarioResponse buscarPorId(Long id);
    List<TipoUsuarioResponse> listarTodos();
    TipoUsuarioResponse atualizar(Long id, TipoUsuarioRequest request);
    void deletar(Long id);
}
