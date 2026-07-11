package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ItemCardapioApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(
            target = "restaurante",
            source = "restauranteId",
            qualifiedByName = "restaurantePorId"
    )
    ItemCardapio toDomain(ItemCardapioRequest request);

    ItemCardapioResponse toResponse(ItemCardapio itemCardapio);

    @Named("restaurantePorId")
    default Restaurante restaurantePorId(Long restauranteId) {
        if (restauranteId == null) {
            return null;
        }

        return Restaurante.referenciaPorId(restauranteId);
    }
}
