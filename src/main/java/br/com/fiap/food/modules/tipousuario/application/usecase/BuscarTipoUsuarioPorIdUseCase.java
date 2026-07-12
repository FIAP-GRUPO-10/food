package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;

public class BuscarTipoUsuarioPorIdUseCase {

    private final TipoUsuarioGateway gateway;

    public BuscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public TipoUsuario execute(Long id) {
        return gateway.buscarPorId(id)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(id));
    }
}
