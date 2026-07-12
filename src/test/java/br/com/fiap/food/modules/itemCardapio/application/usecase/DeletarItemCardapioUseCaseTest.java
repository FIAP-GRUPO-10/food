package br.com.fiap.food.modules.itemCardapio.application.usecase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.DeletarItemCardapioUseCase;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private DeletarItemCardapioUseCase deletarItemCardapioUseCase;

    @Test
    void deveDeletarItemCardapioQuandoExistir() {
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

        doNothing()
                .when(itemCardapioGateway)
                .deletar(1L);

        deletarItemCardapioUseCase.execute(1L);

        verify(itemCardapioGateway).buscarPorId(1L);
        verify(itemCardapioGateway).deletar(1L);
    }

    @Test
    void deveLancarExcecaoQuandoItemCardapioNaoExistir() {
        when(itemCardapioGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ItemCardapioNaoEncontradoException exception = assertThrows(
                ItemCardapioNaoEncontradoException.class,
                () -> deletarItemCardapioUseCase.execute(99L)
        );

        assertEquals(
                "Item do cardápio não encontrado",
                exception.getMessage()
        );

        verify(itemCardapioGateway).buscarPorId(99L);
        verify(itemCardapioGateway, never()).deletar(anyLong());
    }
}