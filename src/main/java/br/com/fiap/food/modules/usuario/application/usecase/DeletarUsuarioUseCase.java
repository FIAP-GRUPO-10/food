package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class DeletarUsuarioUseCase {

    private final UsuarioGateway gateway;

    public DeletarUsuarioUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Long id) {
        gateway.deletar(id);
    }
}
