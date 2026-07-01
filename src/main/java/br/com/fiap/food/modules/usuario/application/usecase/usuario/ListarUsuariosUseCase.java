package br.com.fiap.food.modules.usuario.application.usecase.usuario;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway gateway;

    public ListarUsuariosUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Usuario> execute() {
        return gateway.listarTodos();
    }
}
