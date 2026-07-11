package br.com.fiap.food.modules.itemcardapio.infrastructure.gateway;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.mapper.ItemCardapioEntityMapper;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.repository.ItemCardapioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemCardapioGatewayImpl implements ItemCardapioGateway {

    private final ItemCardapioRepository repository;
    private final ItemCardapioEntityMapper mapper;

    public ItemCardapioGatewayImpl(ItemCardapioRepository repository, ItemCardapioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio itemCardapio) {
        ItemCardapioEntity entity = mapper.toEntity(itemCardapio);
        ItemCardapioEntity save = repository.save(entity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    public List<ItemCardapio> buscarPorRestauranteId(Long restauranteId) {
        return repository.findByRestauranteId(restauranteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ItemCardapio> listarTodos() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
