package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteDuplicadoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class CriarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public CriarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Restaurante execute(Restaurante restaurante) {
        boolean existePorNomeEEndereco =
                restauranteGateway.existePorNomeEEndereco(restaurante.getNome(), restaurante.getEndereco());

        if (existePorNomeEEndereco) throw new RestauranteDuplicadoException(restaurante.getNome(), restaurante.getEndereco());

        Usuario usuario = usuarioGateway.buscarPorId(restaurante.getDono().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(restaurante.getDono().getId()));

        restaurante.setDono(usuario);
        return restauranteGateway.salvar(restaurante);
    }
}
