package br.com.fiap.food.modules.restaurante.infrastructure.gateway;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper.RestauranteEntityMapper;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository.RestauranteRepository;
import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteGatewayImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteEntityMapper mapper;

    private RestauranteGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        gateway = new RestauranteGatewayImpl(restauranteRepository, mapper);
    }

    private Usuario buildDono() {
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN", "Administrador");
        return new Usuario(1L, "Thiago", "thiago@email.com", tipo);
    }

    private Restaurante buildDomain() {
        return new Restaurante(
                1L,
                "Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                buildDono()
        );
    }

    private RestauranteEntity buildEntity() {
        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(1L);
        entity.setNome("Fazenda");
        entity.setEndereco("Rua A");
        entity.setTipoCozinha("Mexicana");
        return entity;
    }

    @Test
    void deveSalvarRestauranteComSucesso() {
        Restaurante domain = buildDomain();
        RestauranteEntity entity = buildEntity();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(restauranteRepository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        Restaurante resultado = gateway.salvar(domain);

        assertNotNull(resultado);
        assertEquals("Fazenda", resultado.getNome());

        verify(restauranteRepository).save(any(RestauranteEntity.class));
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        RestauranteEntity entity = buildEntity();
        Restaurante domain = buildDomain();

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        Optional<Restaurante> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Fazenda", resultado.get().getNome());

        verify(restauranteRepository).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorId() {
        when(restauranteRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurante> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());

        verify(restauranteRepository).findById(1L);
    }

    @Test
    void deveListarTodosRestaurantes() {
        RestauranteEntity e1 = buildEntity();
        RestauranteEntity e2 = buildEntity();
        e2.setId(2L);
        e2.setNome("Restaurante 2");

        Restaurante r1 = buildDomain();
        Restaurante r2 = buildDomain();

        when(restauranteRepository.findAll()).thenReturn(Arrays.asList(e1, e2));
        when(mapper.toDomain(e1)).thenReturn(r1);
        when(mapper.toDomain(e2)).thenReturn(r2);

        List<Restaurante> resultado = gateway.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Fazenda", resultado.get(0).getNome());

        verify(restauranteRepository).findAll();
    }

    @Test
    void deveAtualizarRestaurante() {
        Restaurante domain = buildDomain();
        RestauranteEntity entity = buildEntity();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(restauranteRepository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        Restaurante resultado = gateway.atualizar(domain);

        assertNotNull(resultado);
        assertEquals("Fazenda", resultado.getNome());

        verify(restauranteRepository).save(any(RestauranteEntity.class));
    }

    @Test
    void deveDeletarRestaurante() {
        Long id = 1L;

        gateway.deletar(id);

        verify(restauranteRepository).deleteById(id);
    }

    @Test
    void deveVerificarSeExisteRestaurantePorNomeEEndereco() {
        when(restauranteRepository.existsByNomeAndEndereco("Fazenda", "Rua A"))
                .thenReturn(true);

        boolean resultado = gateway.existePorNomeEEndereco("Fazenda", "Rua A");

        assertTrue(resultado);

        verify(restauranteRepository).existsByNomeAndEndereco("Fazenda", "Rua A");
    }

    @Test
    void deveRetornarFalseQuandoRestauranteNaoExistir() {
        when(restauranteRepository.existsByNomeAndEndereco("Fazenda", "Rua A"))
                .thenReturn(false);

        boolean resultado = gateway.existePorNomeEEndereco("Fazenda", "Rua A");

        assertFalse(resultado);

        verify(restauranteRepository).existsByNomeAndEndereco("Fazenda", "Rua A");
    }
}