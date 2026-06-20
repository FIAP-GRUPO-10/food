package br.com.fiap.food.infrastructure.gateways.mappers;

import br.com.fiap.food.infrastructure.persistence.entities.TipoUsuario;
import br.com.fiap.food.infrastructure.presentation.request.TipoUsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.TipoUsuarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {

    TipoUsuario toEntity(TipoUsuarioRequest request);
    TipoUsuarioResponse toResponse(TipoUsuario tipoUsuario);
    List<TipoUsuarioResponse> toResponseList(List<TipoUsuario> tipos);
}
