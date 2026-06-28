package br.com.fiap.food.modules.usuario.application.usecase.tipousuario;

import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;

public class DeletarTipoUsuarioUseCase {

    private final TipoUsuarioGateway gateway;

    public DeletarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Long id) {
        if (!gateway.existsById(id)) {
            throw new TipoUsuarioNaoEncontradoException(id);
        }
            gateway.deletar(id);
    }
}
