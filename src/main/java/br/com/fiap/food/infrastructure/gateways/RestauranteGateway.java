package br.com.fiap.food.infrastructure.gateways;

import br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper.RestauranteApiMapper;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository.RestauranteRepository;
import br.com.fiap.food.infrastructure.persistence.repositories.UsuarioRepository;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteGateway implements br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteApiMapper restauranteApiMapper;

    public RestauranteGateway(RestauranteRepository restauranteRepository,
                              UsuarioRepository usuarioRepository,
                              RestauranteApiMapper restauranteApiMapper) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteApiMapper = restauranteApiMapper;
    }

    @Override
    public RestauranteResponse criar(RestauranteRequest request) {
        Usuario dono = usuarioRepository.findById(request.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Restaurante restaurante = restauranteApiMapper.toDomain(request);
        restaurante.setDono(dono);

        Restaurante salvo = restauranteRepository.save(restaurante);
        return restauranteApiMapper.toResponse(salvo);
    }

    @Override
    public RestauranteResponse buscarPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        return restauranteApiMapper.toResponse(restaurante);
    }

    @Override
    public List<RestauranteResponse> listarTodos() {
        return restauranteApiMapper.toResponseList(restauranteRepository.findAll());
    }

    @Override
    public RestauranteResponse atualizar(Long id, RestauranteRequest request) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restaurante.setNome(request.nome());
        restaurante.setEndereco(request.endereco());
        restaurante.setTipoCozinha(request.tipoCozinha());
        restaurante.setHorarioFuncionamento(request.horarioFuncionamento());

        Usuario dono = usuarioRepository.findById(request.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));
        restaurante.setDono(dono);

        Restaurante atualizado = restauranteRepository.save(restaurante);
        return restauranteApiMapper.toResponse(atualizado);
    }

    @Override
    public void deletar(Long id) {
        restauranteRepository.deleteById(id);
    }
}
