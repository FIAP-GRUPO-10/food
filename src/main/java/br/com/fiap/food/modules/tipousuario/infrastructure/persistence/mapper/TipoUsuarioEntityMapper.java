package br.com.fiap.food.modules.tipousuario.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoUsuarioEntityMapper {

    TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario);
    TipoUsuario toDomain(TipoUsuarioEntity entity);
}