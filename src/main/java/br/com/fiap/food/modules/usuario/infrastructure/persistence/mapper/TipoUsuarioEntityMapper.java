package br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoUsuarioEntityMapper {

    TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario);
    TipoUsuario toDomain(TipoUsuarioEntity entity);

}
