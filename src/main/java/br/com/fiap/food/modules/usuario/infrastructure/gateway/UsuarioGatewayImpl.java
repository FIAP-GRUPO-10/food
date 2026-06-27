package br.com.fiap.food.modules.usuario.infrastructure.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository repository;
    private final UsuarioEntityMapper mapper;

    public UsuarioGatewayImpl(UsuarioRepository repository, UsuarioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity save = repository.save(entity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return List.of();
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity atualizado = repository.save(entity);
        return mapper.toDomain(atualizado);
    }

    @Override
    public void deletar(Long id) {

    }
}
