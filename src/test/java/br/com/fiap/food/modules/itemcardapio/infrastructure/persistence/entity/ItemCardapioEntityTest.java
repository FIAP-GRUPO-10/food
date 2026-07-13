package br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioEntityTest {

    @Test
    void deveCriarItemCardapioEntityComBuilder() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Fazenda")
                .build();

        ItemCardapioEntity entity = ItemCardapioEntity.builder()
                .id(1L)
                .nome("Pizza Portuguesa")
                .descricao("Pizza com presunto, ovos e cebola")
                .preco(49.90)
                .somenteNoLocal(false)
                .caminhoFoto("/img/pizza.png")
                .restaurante(restaurante)
                .build();

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
    }

    @Test
    void deveCriarItemCardapioEntityComConstrutorCompleto() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Fazenda")
                .build();

        ItemCardapioEntity entity = new ItemCardapioEntity(
                1L,
                "Hambúrguer Artesanal",
                "Hambúrguer com carne artesanal",
                35.90,
                true,
                "/img/hamburguer.png",
                restaurante
        );

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Hambúrguer Artesanal", entity.getNome());
        assertEquals(
                "Hambúrguer com carne artesanal",
                entity.getDescricao()
        );
        assertEquals(35.90, entity.getPreco(), 0.001);
        assertTrue(entity.isSomenteNoLocal());
        assertEquals("/img/hamburguer.png", entity.getCaminhoFoto());
        assertSame(restaurante, entity.getRestaurante());
    }

    @Test
    void deveCriarItemCardapioEntityComConstrutorVazioESetters() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Fazenda")
                .build();

        ItemCardapioEntity entity = new ItemCardapioEntity();

        entity.setId(1L);
        entity.setNome("Pizza Margherita");
        entity.setDescricao("Pizza com tomate e manjericão");
        entity.setPreco(42.90);
        entity.setSomenteNoLocal(false);
        entity.setCaminhoFoto("/img/margherita.png");
        entity.setRestaurante(restaurante);

        assertEquals(1L, entity.getId());
        assertEquals("Pizza Margherita", entity.getNome());
        assertEquals(
                "Pizza com tomate e manjericão",
                entity.getDescricao()
        );
        assertEquals(42.90, entity.getPreco(), 0.001);
        assertFalse(entity.isSomenteNoLocal());
        assertEquals("/img/margherita.png", entity.getCaminhoFoto());
        assertSame(restaurante, entity.getRestaurante());
    }

    @Test
    void deveAtualizarDadosDoItemCardapioEntity() {
        ItemCardapioEntity entity = ItemCardapioEntity.builder()
                .id(1L)
                .nome("Nome original")
                .descricao("Descrição original")
                .preco(20.00)
                .somenteNoLocal(false)
                .caminhoFoto("/img/original.png")
                .build();

        entity.setNome("Nome atualizado");
        entity.setDescricao("Descrição atualizada");
        entity.setPreco(30.00);
        entity.setSomenteNoLocal(true);
        entity.setCaminhoFoto("/img/atualizada.png");

        assertEquals("Nome atualizado", entity.getNome());
        assertEquals("Descrição atualizada", entity.getDescricao());
        assertEquals(30.00, entity.getPreco(), 0.001);
        assertTrue(entity.isSomenteNoLocal());
        assertEquals("/img/atualizada.png", entity.getCaminhoFoto());
    }
}