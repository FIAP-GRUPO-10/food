package br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteApiMapper {

    @Mapping(target = "dono.id", source = "donoId")
    Restaurante toDomain(RestauranteRequest request);
    RestauranteResponse toResponse(Restaurante restaurante);

}
