package br.com.fiap.food.modules.usuario.application.usecase.usuario;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway gateway;

    public AtualizarUsuarioUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public Usuario execute(Long id, Usuario usuario) {
        return  gateway.atualizar(id,usuario);
    }
}
