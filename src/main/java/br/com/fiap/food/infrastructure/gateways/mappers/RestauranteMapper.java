package br.com.fiap.food.infrastructure.gateways.mappers;

import br.com.fiap.food.infrastructure.persistence.entities.Restaurante;
import br.com.fiap.food.infrastructure.presentation.request.RestauranteRequest;
import br.com.fiap.food.infrastructure.presentation.response.RestauranteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    Restaurante toEntity(RestauranteRequest request);
    RestauranteResponse toResponse(Restaurante restaurante);
    List<RestauranteResponse> toResponseList(List<Restaurante> restaurantes);
}
