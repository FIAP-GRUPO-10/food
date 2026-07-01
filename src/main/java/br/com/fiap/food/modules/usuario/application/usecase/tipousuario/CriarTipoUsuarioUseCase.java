package br.com.fiap.food.modules.usuario.application.usecase.tipousuario;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;

public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuario execute(TipoUsuario tipoUsuario) {
       return tipoUsuarioGateway.salvar(tipoUsuario);
    }
}
