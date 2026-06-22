package br.com.fiap.food.infrastructure.gateways;

import br.com.fiap.food.application.gateways.RestauranteGatewaySpec;
import br.com.fiap.food.infrastructure.gateways.mappers.RestauranteMapper;
import br.com.fiap.food.infrastructure.persistence.entities.Restaurante;
import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import br.com.fiap.food.infrastructure.persistence.repositories.RestauranteRepository;
import br.com.fiap.food.infrastructure.persistence.repositories.UsuarioRepository;
import br.com.fiap.food.infrastructure.presentation.request.RestauranteRequest;
import br.com.fiap.food.infrastructure.presentation.response.RestauranteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteGateway implements RestauranteGatewaySpec {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteMapper restauranteMapper;

    public RestauranteGateway(RestauranteRepository restauranteRepository,
                              UsuarioRepository usuarioRepository,
                              RestauranteMapper restauranteMapper) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteMapper = restauranteMapper;
    }

    @Override
    public RestauranteResponse criar(RestauranteRequest request) {
        Usuario dono = usuarioRepository.findById(request.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Restaurante restaurante = restauranteMapper.toEntity(request);
        restaurante.setDono(dono);

        Restaurante salvo = restauranteRepository.save(restaurante);
        return restauranteMapper.toResponse(salvo);
    }

    @Override
    public RestauranteResponse buscarPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        return restauranteMapper.toResponse(restaurante);
    }

    @Override
    public List<RestauranteResponse> listarTodos() {
        return restauranteMapper.toResponseList(restauranteRepository.findAll());
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
        return restauranteMapper.toResponse(atualizado);
    }

    @Override
    public void deletar(Long id) {
        restauranteRepository.deleteById(id);
    }
}
