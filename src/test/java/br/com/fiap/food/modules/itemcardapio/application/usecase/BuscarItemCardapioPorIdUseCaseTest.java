package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarItemCardapioPorIdUseCaseTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    @Test
    void deveRetornarItemCardapioQuandoIdExistir() {
        ItemCardapio itemCardapio = new ItemCardapio(
                1L,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                Restaurante.referenciaPorId(1L)
        );

        when(itemCardapioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(itemCardapio));

        ItemCardapio resultado =
                buscarItemCardapioPorIdUseCase.execute(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pizza Portuguesa", resultado.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                resultado.getDescricao()
        );
        assertEquals(49.90, resultado.getPreco(), 0.001);
        assertFalse(resultado.isSomenteNoLocal());
        assertEquals("/img/pizza.png", resultado.getCaminhoFoto());
        assertEquals(1L, resultado.getRestaurante().getId());

        verify(itemCardapioGateway).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoItemCardapioNaoExistir() {
        when(itemCardapioGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ItemCardapioNaoEncontradoException exception = assertThrows(
                ItemCardapioNaoEncontradoException.class,
                () -> buscarItemCardapioPorIdUseCase.execute(99L)
        );

        assertEquals(
                "Item do cardapio não encontrado",
                exception.getMessage()
        );

        verify(itemCardapioGateway).buscarPorId(99L);
    }
}