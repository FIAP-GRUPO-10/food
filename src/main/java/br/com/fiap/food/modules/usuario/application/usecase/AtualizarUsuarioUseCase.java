package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway gateway, TipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = gateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public Usuario execute(Long id, Usuario usuario) {
        Long tipoUsuarioId = usuario.getTipoUsuario().getId();
        if (!tipoUsuarioGateway.existsById(tipoUsuarioId)) {
            throw new TipoUsuarioNaoEncontradoException(tipoUsuarioId);
        }
        return usuarioGateway.atualizar(id,usuario);
    }
}
