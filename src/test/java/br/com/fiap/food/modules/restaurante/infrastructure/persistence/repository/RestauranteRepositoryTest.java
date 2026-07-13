package br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository;

import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RestauranteRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Test
    void deveSalvarRestauranteComDono() {
        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setNome("ADMIN");
        tipo.setObservacao("Admin");
        tipo = tipoUsuarioRepository.save(tipo);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Thiago");
        dono.setEmail("thiago@email.com");
        dono.setTipoUsuario(tipo);
        dono = usuarioRepository.save(dono);

        RestauranteEntity restaurante = RestauranteEntity.builder()
                .nome("Fazenda")
                .endereco("Rua A")
                .tipoCozinha("Mexicana")
                .horarioAbertura(LocalTime.of(10, 0))
                .horarioFechamento(LocalTime.of(22, 0))
                .dono(dono)
                .build();

        RestauranteEntity salvo = restauranteRepository.save(restaurante);

        assertNotNull(salvo.getId());
        assertEquals("Fazenda", salvo.getNome());
        assertEquals(dono.getId(), salvo.getDono().getId());
    }

    @Test
    void deveAtualizarRestaurante() {

        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setNome("ADMIN");
        tipo.setObservacao("Admin");
        tipo = tipoUsuarioRepository.save(tipo);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Thiago");
        dono.setEmail("thiago@email.com");
        dono.setTipoUsuario(tipo);
        dono = usuarioRepository.save(dono);

        RestauranteEntity restaurante = RestauranteEntity.builder()
                .nome("Fazenda")
                .endereco("Rua A")
                .tipoCozinha("Mexicana")
                .horarioAbertura(LocalTime.of(10, 0))
                .horarioFechamento(LocalTime.of(22, 0))
                .dono(dono)
                .build();

        restaurante = restauranteRepository.save(restaurante);

        restaurante.setNome("Fazenda Atualizada");
        restaurante.setHorarioFechamento(LocalTime.of(23, 30));

        RestauranteEntity atualizado = restauranteRepository.save(restaurante);

        assertEquals(restaurante.getId(), atualizado.getId());
        assertEquals("Fazenda Atualizada", atualizado.getNome());
        assertEquals(LocalTime.of(23, 30), atualizado.getHorarioFechamento());
    }

    @Test
    void deveBuscarRestaurantePorId() {
        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setNome("ADMIN");
        tipo.setObservacao("Admin");
        tipo = tipoUsuarioRepository.save(tipo);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Thiago");
        dono.setEmail("thiago@email.com");
        dono.setTipoUsuario(tipo);
        dono = usuarioRepository.save(dono);

        RestauranteEntity restaurante = RestauranteEntity.builder()
                .nome("Fazenda")
                .dono(dono)
                .build();

        RestauranteEntity salvo = restauranteRepository.save(restaurante);

        var encontrado = restauranteRepository.findById(salvo.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Fazenda", encontrado.get().getNome());
    }

    @Test
    void deveListarRestaurantes() {
        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setNome("ADMIN");
        tipo.setObservacao("Admin");
        tipo = tipoUsuarioRepository.save(tipo);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Thiago");
        dono.setEmail("thiago@email.com");
        dono.setTipoUsuario(tipo);
        dono = usuarioRepository.save(dono);

        RestauranteEntity r1 = RestauranteEntity.builder()
                .nome("R1")
                .dono(dono)
                .build();

        RestauranteEntity r2 = RestauranteEntity.builder()
                .nome("R2")
                .dono(dono)
                .build();

        restauranteRepository.saveAll(List.of(r1, r2));

        List<RestauranteEntity> lista = restauranteRepository.findAll();

        assertEquals(2, lista.size());
    }

    @Test
    void deveDeletarRestaurante() {
        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setNome("ADMIN");
        tipo.setObservacao("Admin");
        tipo = tipoUsuarioRepository.save(tipo);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Thiago");
        dono.setEmail("thiago@email.com");
        dono.setTipoUsuario(tipo);
        dono = usuarioRepository.save(dono);

        RestauranteEntity restaurante = RestauranteEntity.builder()
                .nome("Fazenda")
                .dono(dono)
                .build();

        RestauranteEntity salvo = restauranteRepository.save(restaurante);

        restauranteRepository.deleteById(salvo.getId());

        assertFalse(restauranteRepository.findById(salvo.getId()).isPresent());
    }
}