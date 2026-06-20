package br.com.fiap.food.infrastructure.gateways;

import br.com.fiap.food.application.gateways.TipoUsuarioGatewaySpec;
import br.com.fiap.food.infrastructure.gateways.mappers.TipoUsuarioMapper;
import br.com.fiap.food.infrastructure.persistence.entities.TipoUsuario;
import br.com.fiap.food.infrastructure.persistence.repositories.TipoUsuarioRepository;
import br.com.fiap.food.infrastructure.presentation.request.TipoUsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.TipoUsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioGateway implements TipoUsuarioGatewaySpec {

    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final TipoUsuarioMapper tipoUsuarioMapper;

    public TipoUsuarioGateway(TipoUsuarioRepository tipoUsuarioRepository,
                              TipoUsuarioMapper tipoUsuarioMapper) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.tipoUsuarioMapper = tipoUsuarioMapper;
    }

    @Override
    public TipoUsuarioResponse criar(TipoUsuarioRequest request) {
        TipoUsuario tipoUsuario = tipoUsuarioMapper.toEntity(request);
        TipoUsuario salvo = tipoUsuarioRepository.save(tipoUsuario);
        return tipoUsuarioMapper.toResponse(salvo);
    }

    @Override
    public TipoUsuarioResponse buscarPorId(Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));
        return tipoUsuarioMapper.toResponse(tipoUsuario);
    }

    @Override
    public List<TipoUsuarioResponse> listarTodos() {
        return tipoUsuarioMapper.toResponseList(tipoUsuarioRepository.findAll());
    }

    @Override
    public TipoUsuarioResponse atualizar(Long id, TipoUsuarioRequest request) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        tipoUsuario.setNome(request.nome());
        tipoUsuario.setObservacao(request.observacao());

        TipoUsuario atualizado = tipoUsuarioRepository.save(tipoUsuario);
        return tipoUsuarioMapper.toResponse(atualizado);
    }

    @Override
    public void deletar(Long id) {
        tipoUsuarioRepository.deleteById(id);
    }
}
