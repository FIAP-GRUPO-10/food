package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;

public class AtualizarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public AtualizarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Restaurante execute(Long id, Restaurante restauranteAtualizado) {

        Restaurante restaurante = restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));

        Usuario dono = usuarioGateway.buscarPorId(restauranteAtualizado.getDono().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(restauranteAtualizado.getDono().getId()));

        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setEndereco(restauranteAtualizado.getEndereco());
        restaurante.setHorarioAbertura(restauranteAtualizado.getHorarioAbertura());
        restaurante.setHorarioFechamento(restauranteAtualizado.getHorarioFechamento());
        restaurante.setTipoCozinha(restauranteAtualizado.getTipoCozinha());
        restaurante.setDono(dono);

        return restauranteGateway.atualizar(restaurante);
    }
}
