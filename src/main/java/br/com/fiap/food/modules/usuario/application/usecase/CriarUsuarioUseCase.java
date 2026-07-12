package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class CriarUsuarioUseCase {

    private final UsuarioGateway gateway;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.gateway = usuarioGateway;
    }

    public Usuario execute(Usuario usuario) {
        return gateway.salvar(usuario);
    }
}
