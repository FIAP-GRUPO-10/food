package br.com.fiap.food.application.usercases.usuario;

import br.com.fiap.food.application.gateways.UsuarioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.springframework.stereotype.Service;

@Service
public class RecuperarPorIdUsuarioUserCase {

    private final UsuarioGatewaySpec usuarioGateway;

    public RecuperarPorIdUsuarioUserCase(UsuarioGatewaySpec usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public UsuarioResponse buscarPorId(Long id) {
        return this.usuarioGateway.buscarPorId(id);
    }
}
