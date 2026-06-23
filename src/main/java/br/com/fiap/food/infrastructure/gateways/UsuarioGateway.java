package br.com.fiap.food.infrastructure.gateways;

import br.com.fiap.food.application.gateways.UsuarioGatewaySpec;
import br.com.fiap.food.infrastructure.gateways.mappers.UsuarioMapper;
import br.com.fiap.food.infrastructure.persistence.entities.TipoUsuario;
import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import br.com.fiap.food.infrastructure.persistence.repositories.TipoUsuarioRepository;
import br.com.fiap.food.infrastructure.persistence.repositories.UsuarioRepository;
import br.com.fiap.food.infrastructure.presentation.request.UsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioGateway  implements UsuarioGatewaySpec {

    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioGateway(UsuarioRepository usuarioRepository,
                          TipoUsuarioRepository tipoUsuarioRepository,
                          UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponse criar(UsuarioRequest request) {
        TipoUsuario tipo = tipoUsuarioRepository.findById(request.tipoUsuarioId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setTipoUsuario(tipo);

        Usuario salvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(salvo);
    }

    @Override
    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listarTodos() {
        return usuarioMapper.toResponseList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        TipoUsuario tipo = tipoUsuarioRepository.findById(request.tipoUsuarioId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        usuario.setTipoUsuario(tipo);

        Usuario atualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(atualizado);
    }

    @Override
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}

