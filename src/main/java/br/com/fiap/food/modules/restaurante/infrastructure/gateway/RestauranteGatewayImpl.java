package br.com.fiap.food.modules.restaurante.infrastructure.gateway;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper.RestauranteEntityMapper;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final RestauranteRepository repository;
    private final RestauranteEntityMapper mapper;

    public RestauranteGatewayImpl(RestauranteRepository repository, RestauranteEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity entity = mapper.toEntity(restaurante);
        RestauranteEntity save = repository.save(entity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return List.of();
    }

    @Override
    public void deletar(Long id) {

    }
}
