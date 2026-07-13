package br.com.fiap.food.modules.itemcardapio.infrastructure.gateway;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.mapper.ItemCardapioEntityMapper;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.repository.ItemCardapioRepository;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioGatewayImplTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @Mock
    private ItemCardapioEntityMapper mapper;

    private ItemCardapioGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        gateway = new ItemCardapioGatewayImpl(
                itemCardapioRepository,
                mapper
        );
    }

    private ItemCardapio buildDomain() {
        return new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                Restaurante.referenciaPorId(1L)
        );
    }

    private ItemCardapioEntity buildEntity() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Fazenda")
                .build();

        return ItemCardapioEntity.builder()
                .id(1L)
                .nome("Pizza Portuguesa")
                .descricao("Pizza com presunto, ovos e cebola")
                .preco(49.90)
                .somenteNoLocal(false)
                .caminhoFoto("/img/pizza.png")
                .restaurante(restaurante)
                .build();
    }

    @Test
    void deveSalvarItemCardapioComSucesso() {

        ItemCardapio domain = buildDomain();
        ItemCardapioEntity entity = buildEntity();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(itemCardapioRepository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        ItemCardapio resultado = gateway.salvar(domain);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pizza Portuguesa", resultado.getNome());
        assertEquals(49.90, resultado.getPreco(), 0.001);

        verify(mapper).toEntity(domain);
        verify(itemCardapioRepository).save(entity);
        verify(mapper).toDomain(entity);
    }

    @Test
    void deveBuscarItemCardapioPorIdComSucesso() {

        ItemCardapioEntity entity = buildEntity();
        ItemCardapio domain = buildDomain();

        when(itemCardapioRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        when(mapper.toDomain(entity))
                .thenReturn(domain);

        Optional<ItemCardapio> resultado =
                gateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Pizza Portuguesa", resultado.get().getNome());

        verify(itemCardapioRepository).findById(1L);
        verify(mapper).toDomain(entity);
    }

    @Test
    void deveRetornarVazioQuandoItemCardapioNaoForEncontrado() {

        when(itemCardapioRepository.findById(1L))
                .thenReturn(Optional.empty());

        Optional<ItemCardapio> resultado =
                gateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());

        verify(itemCardapioRepository).findById(1L);
        verifyNoInteractions(mapper);
    }

    @Test
    void deveListarTodosItensCardapio() {

        ItemCardapioEntity entity1 = buildEntity();

        ItemCardapioEntity entity2 = buildEntity();
        entity2.setId(2L);
        entity2.setNome("Hambúrguer Artesanal");

        ItemCardapio domain1 = buildDomain();

        ItemCardapio domain2 = new ItemCardapio(
                2L,
                "Hambúrguer Artesanal",
                "Hambúrguer artesanal",
                35.90,
                true,
                "/img/hamburguer.png",
                Restaurante.referenciaPorId(1L)
        );

        when(itemCardapioRepository.findAll())
                .thenReturn(List.of(entity1, entity2));

        when(mapper.toDomain(entity1))
                .thenReturn(domain1);

        when(mapper.toDomain(entity2))
                .thenReturn(domain2);

        List<ItemCardapio> resultado =
                gateway.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Pizza Portuguesa", resultado.get(0).getNome());
        assertEquals("Hambúrguer Artesanal", resultado.get(1).getNome());

        verify(itemCardapioRepository).findAll();
        verify(mapper).toDomain(entity1);
        verify(mapper).toDomain(entity2);
    }

    @Test
    void deveDeletarItemCardapio() {

        Long id = 1L;

        gateway.deletar(id);

        verify(itemCardapioRepository).deleteById(id);
    }
}