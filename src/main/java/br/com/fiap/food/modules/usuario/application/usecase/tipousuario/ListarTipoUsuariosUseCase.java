package br.com.fiap.food.modules.usuario.application.usecase.tipousuario;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;

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
