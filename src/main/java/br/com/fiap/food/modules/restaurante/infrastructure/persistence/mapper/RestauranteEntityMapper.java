package br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioEntityMapper.class)
public interface RestauranteEntityMapper {

    RestauranteEntity toEntity(Restaurante restaurante);
    Restaurante toDomain(RestauranteEntity entity);
}