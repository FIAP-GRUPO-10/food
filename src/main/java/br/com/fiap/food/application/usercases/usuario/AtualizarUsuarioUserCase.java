package br.com.fiap.food.application.usercases.usuario;

import br.com.fiap.food.application.gateways.UsuarioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.request.UsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUserCase {

    private final UsuarioGatewaySpec usuarioGatewaySpec;

    public AtualizarUsuarioUserCase(UsuarioGatewaySpec usuarioGatewaySpec) {
        this.usuarioGatewaySpec = usuarioGatewaySpec;
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        return this.usuarioGatewaySpec.atualizar(id, request);
    }
}
