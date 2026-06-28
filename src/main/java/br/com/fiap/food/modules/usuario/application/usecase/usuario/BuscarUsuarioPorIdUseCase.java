package br.com.fiap.food.modules.usuario.application.usecase.usuario;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class BuscarUsuarioPorIdUseCase {

    private final UsuarioGateway gateway;

    public BuscarUsuarioPorIdUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public Usuario execute(Long id) {
        return gateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
}
