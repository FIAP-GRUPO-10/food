package br.com.fiap.food.application.usercases.usuario;

import br.com.fiap.food.application.gateways.UsuarioGatewaySpec;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUserCase {

    private final UsuarioGatewaySpec usuarioGatewaySpec;

    public DeletarUsuarioUserCase(UsuarioGatewaySpec usuarioGatewaySpec) {
        this.usuarioGatewaySpec = usuarioGatewaySpec;
    }

    public void deletar(Long id) {
        this.usuarioGatewaySpec.deletar(id);
    }
}
