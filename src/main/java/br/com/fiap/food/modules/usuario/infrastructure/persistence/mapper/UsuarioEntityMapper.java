package br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TipoUsuarioEntityMapper.class)
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(Usuario usuario);
    Usuario toDomain(UsuarioEntity entity);
}