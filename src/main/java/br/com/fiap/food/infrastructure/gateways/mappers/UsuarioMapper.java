package br.com.fiap.food.infrastructure.gateways.mappers;

import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import br.com.fiap.food.infrastructure.presentation.request.UsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);
    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);
}
