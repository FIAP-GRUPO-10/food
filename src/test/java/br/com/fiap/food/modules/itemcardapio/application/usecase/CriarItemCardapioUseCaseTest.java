package br.com.fiap.food.modules.itemcardapio.application.usecase;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarItemCardapioUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @Test
    void deveCriarItemCardapioQuandoDadosForemValidos() {
        TipoUsuario tipoUsuario = new TipoUsuario(
                1L,
                "ADMIN",
                null
        );

        Usuario dono = new Usuario(
                1L,
                "Luiz",
                "luiz@email.com",
                tipoUsuario
        );

        Restaurante restauranteExistente = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        ItemCardapio itemCardapio = new ItemCardapio(
                null,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                Restaurante.referenciaPorId(1L)
        );

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(restauranteExistente));

        when(itemCardapioGateway.salvar(any(ItemCardapio.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ItemCardapio resultado =
                criarItemCardapioUseCase.execute(itemCardapio);

        assertNotNull(resultado);
        assertEquals("Pizza Portuguesa", resultado.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                resultado.getDescricao()
        );
        assertEquals(49.90, resultado.getPreco(), 0.001);
        assertFalse(resultado.isSomenteNoLocal());
        assertEquals("/img/pizza.png", resultado.getCaminhoFoto());

        assertNotNull(resultado.getRestaurante());
        assertSame(restauranteExistente, resultado.getRestaurante());
        assertEquals(1L, resultado.getRestaurante().getId());
        assertEquals(
                "Restaurante Fazenda",
                resultado.getRestaurante().getNome()
        );

        verify(restauranteGateway).buscarPorId(1L);
        verify(itemCardapioGateway).salvar(itemCardapio);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        ItemCardapio itemCardapio = new ItemCardapio(
                null,
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png",
                Restaurante.referenciaPorId(99L)
        );

        when(restauranteGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        RestauranteNaoEncontradoException exception = assertThrows(
                RestauranteNaoEncontradoException.class,
                () -> criarItemCardapioUseCase.execute(itemCardapio)
        );

        assertEquals(
                "Restaurante com id 99 não encontrado",
                exception.getMessage()
        );

        verify(restauranteGateway).buscarPorId(99L);

        verify(itemCardapioGateway, never())
                .salvar(any(ItemCardapio.class));
    }
}