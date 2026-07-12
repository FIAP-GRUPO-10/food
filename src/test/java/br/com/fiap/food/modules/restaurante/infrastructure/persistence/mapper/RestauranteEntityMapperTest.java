package br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteSemDonoException;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteEntityMapperTest {

    private final RestauranteEntityMapper mapper =
            Mappers.getMapper(RestauranteEntityMapper.class);

    @BeforeEach
    void setUp() {
        UsuarioEntityMapper usuarioMapper = Mappers.getMapper(UsuarioEntityMapper.class);
        ReflectionTestUtils.setField(mapper, "usuarioEntityMapper", usuarioMapper);
    }

    @Test
    void deveConverterDomainParaEntity() {

        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipo);

        Restaurante restaurante = new Restaurante(
                1L,
                "Fazenda",
                "Rua A",
                "Mexicana",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                dono
        );

        RestauranteEntity entity = mapper.toEntity(restaurante);

        assertNotNull(entity);
        assertEquals("Fazenda", entity.getNome());
        assertEquals("Rua A", entity.getEndereco());
        assertEquals("Mexicana", entity.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), entity.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), entity.getHorarioFechamento());

        assertNotNull(entity.getDono());
        assertEquals(1L, entity.getDono().getId());
    }

    @Test
    void deveConverterEntityParaDomain() {

        TipoUsuarioEntity tipoEntity =
                new TipoUsuarioEntity(1L, "ADMIN", "Administrador");

        UsuarioEntity donoEntity = new UsuarioEntity();
        donoEntity.setId(1L);
        donoEntity.setNome("Thiago");
        donoEntity.setEmail("thiago@email.com");
        donoEntity.setTipoUsuario(tipoEntity);

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(1L);
        entity.setNome("Fazenda");
        entity.setEndereco("Rua A");
        entity.setTipoCozinha("Mexicana");
        entity.setHorarioAbertura(LocalTime.of(10, 0));
        entity.setHorarioFechamento(LocalTime.of(22, 0));
        entity.setDono(donoEntity);

        Restaurante domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(1L, domain.getId());
        assertEquals("Fazenda", domain.getNome());
        assertEquals("Rua A", domain.getEndereco());
        assertEquals("Mexicana", domain.getTipoCozinha());

        assertNotNull(domain.getDono());
        assertEquals(1L, domain.getDono().getId());
    }

    @Test
    void deveLancarExcecaoAoConverterComDonoNulo() {

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(1L);
        entity.setNome("Fazenda");
        entity.setDono(null); // Cenário inválido para o domínio
        entity.setHorarioAbertura(LocalTime.of(8, 0));
        entity.setHorarioFechamento(LocalTime.of(23, 0));

        assertThrows(RestauranteSemDonoException.class, () -> {
            mapper.toDomain(entity);
        });
    }
}