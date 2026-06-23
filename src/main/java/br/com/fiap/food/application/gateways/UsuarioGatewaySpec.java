package br.com.fiap.food.application.gateways;

import br.com.fiap.food.infrastructure.presentation.request.UsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;

import java.util.List;

public interface UsuarioGatewaySpec {

    UsuarioResponse criar(UsuarioRequest request);
    UsuarioResponse buscarPorId(Long id);
    List<UsuarioResponse> listarTodos();
    UsuarioResponse atualizar(Long id, UsuarioRequest request);
    void deletar(Long id);
}
