package br.com.fiap.food.application.usercases.usuario;

import br.com.fiap.food.application.gateways.UsuarioGatewaySpec;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuarioUserCase {
    private final UsuarioGatewaySpec usuarioGatewaySpec;

    public ListarUsuarioUserCase(UsuarioGatewaySpec usuarioGatewaySpec) {
        this.usuarioGatewaySpec = usuarioGatewaySpec;
    }

    public List<UsuarioResponse> listarTodos() {
        return  this.usuarioGatewaySpec.listarTodos();
    }
}
