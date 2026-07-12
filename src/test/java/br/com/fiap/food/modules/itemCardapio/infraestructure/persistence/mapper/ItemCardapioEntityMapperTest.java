package br.com.fiap.food.modules.itemCardapio.infraestructure.persistence.mapper;
import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioSemRestauranteException;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.mapper.ItemCardapioEntityMapper;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.mapper.RestauranteEntityMapper;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioEntityMapperTest {

    private final ItemCardapioEntityMapper mapper =
            Mappers.getMapper(ItemCardapioEntityMapper.class);

    @BeforeEach
    void setUp() {

        TipoUsuarioEntityMapper tipoUsuarioEntityMapper = Mappers.getMapper(TipoUsuarioEntityMapper.class);
        UsuarioEntityMapper usuarioEntityMapper = Mappers.getMapper(UsuarioEntityMapper.class);
        RestauranteEntityMapper restauranteEntityMapper = Mappers.getMapper(RestauranteEntityMapper.class);
        ReflectionTestUtils.setField(usuarioEntityMapper, "tipoUsuarioEntityMapper", tipoUsuarioEntityMapper);
        ReflectionTestUtils.setField(restauranteEntityMapper, "usuarioEntityMapper", usuarioEntityMapper);
        ReflectionTestUtils.setField(mapper, "restauranteEntityMapper", restauranteEntityMapper);
    }

    @Test
    void deveConverterDomainParaEntity() {
        TipoUsuario tipoUsuario = new TipoUsuario(
                1L,
                "ADMIN",
                "Administrador"
        );

        Usuario dono = new Usuario(
                1L,
                "Luiz",
                "luiz@email.com",
                tipoUsuario
        );

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        ItemCardapio itemCardapio = new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                restaurante
        );

        ItemCardapioEntity entity = mapper.toEntity(itemCardapio);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Pizza Portuguesa", entity.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                entity.getDescricao()
        );
        assertEquals(49.90, entity.getPreco(), 0.001);
        assertFalse(entity.isSomenteNoLocal());
        assertEquals("/img/pizza.png", entity.getCaminhoFoto());

        assertNotNull(entity.getRestaurante());
        assertEquals(1L, entity.getRestaurante().getId());
        assertEquals(
                "Restaurante Fazenda",
                entity.getRestaurante().getNome()
        );

        assertNotNull(entity.getRestaurante().getDono());
        assertEquals(
                1L,
                entity.getRestaurante().getDono().getId()
        );
    }

    @Test
    void deveConverterEntityParaDomain() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(
                1L,
                "ADMIN",
                "Administrador"
        );

        UsuarioEntity donoEntity = new UsuarioEntity();
        donoEntity.setId(1L);
        donoEntity.setNome("Luiz");
        donoEntity.setEmail("luiz@email.com");
        donoEntity.setTipoUsuario(tipoUsuarioEntity);

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);
        restauranteEntity.setNome("Restaurante Fazenda");
        restauranteEntity.setEndereco("Rua A");
        restauranteEntity.setTipoCozinha("Italiana");
        restauranteEntity.setHorarioAbertura(LocalTime.of(8, 0));
        restauranteEntity.setHorarioFechamento(LocalTime.of(22, 0));
        restauranteEntity.setDono(donoEntity);

        ItemCardapioEntity entity = new ItemCardapioEntity();
        entity.setId(1L);
        entity.setNome("Pizza Portuguesa");
        entity.setDescricao("Pizza com presunto, ovos e cebola");
        entity.setPreco(49.90);
        entity.setSomenteNoLocal(false);
        entity.setCaminhoFoto("/img/pizza.png");
        entity.setRestaurante(restauranteEntity);

        ItemCardapio domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(1L, domain.getId());
        assertEquals("Pizza Portuguesa", domain.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                domain.getDescricao()
        );
        assertEquals(49.90, domain.getPreco(), 0.001);
        assertFalse(domain.isSomenteNoLocal());
        assertEquals("/img/pizza.png", domain.getCaminhoFoto());

        assertNotNull(domain.getRestaurante());
        assertEquals(1L, domain.getRestaurante().getId());
        assertEquals(
                "Restaurante Fazenda",
                domain.getRestaurante().getNome()
        );

        assertNotNull(domain.getRestaurante().getDono());
        assertEquals(
                1L,
                domain.getRestaurante().getDono().getId()
        );
    }

    @Test
    void deveLancarExcecaoAoConverterEntityComRestauranteNulo() {
        ItemCardapioEntity entity = new ItemCardapioEntity();
        entity.setId(1L);
        entity.setNome("Pizza Portuguesa");
        entity.setDescricao("Pizza com presunto, ovos e cebola");
        entity.setPreco(49.90);
        entity.setSomenteNoLocal(false);
        entity.setCaminhoFoto("/img/pizza.png");
        entity.setRestaurante(null);

        ItemCardapioSemRestauranteException exception = assertThrows(
                ItemCardapioSemRestauranteException.class,
                () -> mapper.toDomain(entity)
        );

        assertEquals(
                "Item do cardápio deve estar associado a um restaurante",
                exception.getMessage()
        );
    }
}