package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemCardapioApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurante", ignore = true)
    ItemCardapio toDomain(ItemCardapioRequest request);
    ItemCardapioResponse toResponse(ItemCardapio itemCardapio);

}
