package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;

public class AtualizarTipoUsuarioUseCase {

    private final TipoUsuarioGateway gateway;
    
    public AtualizarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public TipoUsuario execute(Long id, TipoUsuario tipoUsuario) {
       return gateway.atualizar(id,tipoUsuario);
    }


}
