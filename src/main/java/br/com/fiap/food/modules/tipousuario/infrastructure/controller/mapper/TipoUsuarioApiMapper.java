package br.com.fiap.food.modules.tipousuario.infrastructure.controller.mapper;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.controller.dto.request.TipoUsuarioRequest;
import br.com.fiap.food.modules.tipousuario.infrastructure.controller.dto.response.TipoUsuarioResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoUsuarioApiMapper {

    @Mapping(target = "id", ignore = true)
    TipoUsuario toDomain(TipoUsuarioRequest request);
    TipoUsuarioResponse toResponse(TipoUsuario tipoUsuario);
}
