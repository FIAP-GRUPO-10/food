package br.com.fiap.food.modules.usuario.infrastructure.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final UsuarioEntityMapper mapper;

    public UsuarioGatewayImpl(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository, UsuarioEntityMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository
                .findById(usuario.getTipoUsuario().getId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(usuario.getTipoUsuario().getId()));

        UsuarioEntity entity = mapper.toEntity(usuario);
        entity.setTipoUsuario(tipoUsuario);
        UsuarioEntity salvo = usuarioRepository.save(entity);

        return mapper.toDomain(salvo);
    }


    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity atualizado = usuarioRepository.save(entity);
        return mapper.toDomain(atualizado);
    }

    @Override
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
