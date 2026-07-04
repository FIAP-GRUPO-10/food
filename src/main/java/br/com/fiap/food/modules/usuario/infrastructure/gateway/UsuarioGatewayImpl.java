package br.com.fiap.food.modules.usuario.infrastructure.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository repository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final UsuarioEntityMapper mapper;

    public UsuarioGatewayImpl(UsuarioRepository repository, TipoUsuarioRepository tipoUsuarioRepository, UsuarioEntityMapper mapper) {
        this.repository = repository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Long tipoUsuarioId = usuario.getTipoUsuario().getId();
        TipoUsuarioEntity tipoEntity = tipoUsuarioRepository.findById(tipoUsuarioId)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(tipoUsuarioId));

        UsuarioEntity entity = mapper.toEntity(usuario);
        entity.setTipoUsuario(tipoEntity);
        return mapper.toDomain(repository.save(entity));
    }


    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
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
        repository.deleteById(id);
    }
}
