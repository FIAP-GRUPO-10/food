package br.com.fiap.food.modules.usuario.infrastructure.controller.mapper;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioApiMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toDomain(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);

    default TipoUsuario toTipoUsuario(Long id) {
        if (id == null) {
            return null;
        }
        return new TipoUsuario(id, null, null);
    }

}
