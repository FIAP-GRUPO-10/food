package br.com.fiap.food.modules.restaurante.domain.gateway;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    List<Restaurante> listarTodos();
    void deletar(Long id);
}