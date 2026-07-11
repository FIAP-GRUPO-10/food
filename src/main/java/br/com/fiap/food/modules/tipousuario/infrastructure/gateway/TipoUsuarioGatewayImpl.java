package br.com.fiap.food.modules.tipousuario.infrastructure.gateway;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository.TipoUsuarioRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioRepository repository;
    private final TipoUsuarioEntityMapper mapper;

    public TipoUsuarioGatewayImpl(TipoUsuarioRepository repository, TipoUsuarioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = mapper.toEntity(tipoUsuario);

        Optional<TipoUsuarioEntity> existente = repository.findByNome(entity.getNome());
        if (existente.isPresent()) {
            throw new TipoUsuarioJaCadastradoException(entity.getNome());
        }
        TipoUsuarioEntity save = repository.save(entity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TipoUsuario> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public TipoUsuario atualizar(Long id, TipoUsuario tipoUsuario) {
        tipoUsuario.setId(id);
        TipoUsuarioEntity entity = mapper.toEntity(tipoUsuario);
        TipoUsuarioEntity atualizado = repository.save(entity);
        return mapper.toDomain(atualizado);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
