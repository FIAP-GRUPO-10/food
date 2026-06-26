package br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper.RestauranteEntityMapper;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        RestauranteEntityMapper.class,
        UsuarioEntityMapper.class
})
public interface ItemCardapioEntityMapper {

    ItemCardapioEntity toEntity(ItemCardapio itemCardapio);
    ItemCardapio toDomain(ItemCardapioEntity entity);
}