
package br.com.fiap.food.modules.itemCardapio.domain.entity;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioInvalidoException;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioSemRestauranteException;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioQuandoDadosForemValidos() {
        Restaurante restaurante = Restaurante.referenciaPorId(1L);

        ItemCardapio itemCardapio = new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                restaurante
        );

        assertNotNull(itemCardapio);
        assertEquals(1L, itemCardapio.getId());
        assertEquals("Pizza Portuguesa", itemCardapio.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                itemCardapio.getDescricao()
        );
        assertEquals(49.90, itemCardapio.getPreco(), 0.001);
        assertFalse(itemCardapio.isSomenteNoLocal());
        assertEquals("/img/pizza.png", itemCardapio.getCaminhoFoto());
        assertSame(restaurante, itemCardapio.getRestaurante());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        null,
                        "Descrição válida",
                        49.90,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Nome do item é obrigatório",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "   ",
                        "Descrição válida",
                        49.90,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Nome do item é obrigatório",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForNula() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        null,
                        49.90,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Descrição do item é obrigatória",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForVazia() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        "   ",
                        49.90,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Descrição do item é obrigatória",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForZero() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        "Descrição válida",
                        0.0,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Preço deve ser maior que zero",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNegativo() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        "Descrição válida",
                        -10.0,
                        "/img/pizza.png"
                )
        );

        assertEquals(
                "Preço deve ser maior que zero",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoCaminhoFotoForNulo() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        "Descrição válida",
                        49.90,
                        null
                )
        );

        assertEquals(
                "Caminho da foto é obrigatório",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoCaminhoFotoForVazio() {
        ItemCardapioInvalidoException exception = assertThrows(
                ItemCardapioInvalidoException.class,
                () -> criarItemCardapio(
                        "Pizza Portuguesa",
                        "Descrição válida",
                        49.90,
                        "   "
                )
        );

        assertEquals(
                "Caminho da foto é obrigatório",
                exception.getMessage()
        );
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteForNulo() {
        ItemCardapioSemRestauranteException exception = assertThrows(
                ItemCardapioSemRestauranteException.class,
                () -> new ItemCardapio(
                        null,
                        "Pizza Portuguesa",
                        "Descrição válida",
                        49.90,
                        false,
                        "/img/pizza.png",
                        null
                )
        );

        assertEquals(
                "Item do cardápio deve estar associado a um restaurante",
                exception.getMessage()
        );
    }

    private ItemCardapio criarItemCardapio(
            String nome,
            String descricao,
            double preco,
            String caminhoFoto
    ) {
        return new ItemCardapio(
                null,
                nome,
                descricao,
                preco,
                false,
                caminhoFoto,
                Restaurante.referenciaPorId(1L)
        );
    }
}