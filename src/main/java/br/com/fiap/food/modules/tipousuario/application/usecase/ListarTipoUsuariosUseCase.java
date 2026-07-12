package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;

import java.util.List;

public class ListarTipoUsuariosUseCase {

    private final TipoUsuarioGateway gateway;

    public ListarTipoUsuariosUseCase(TipoUsuarioGateway gateway) {
        this.gateway = gateway;
    }
    public List<TipoUsuario> execute() {
        return gateway.listarTodos();
    }
}
