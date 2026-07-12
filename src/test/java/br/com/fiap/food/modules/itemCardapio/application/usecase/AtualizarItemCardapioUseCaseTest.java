package br.com.fiap.food.modules.itemCardapio.application.usecase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.AtualizarItemUseCase;
import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    private AtualizarItemUseCase atualizarItemCardapioUseCase;

    @BeforeEach
    void setUp() {
        atualizarItemCardapioUseCase =
                new AtualizarItemUseCase(
                        itemCardapioGateway,
                        restauranteGateway
                );
    }

    @Test
    void deveAtualizarItemCardapioQuandoDadosForemValidos() {
        ItemCardapio existente = criarItemExistente();
        ItemCardapio novosDados = criarItemParaAtualizacao();
        Restaurante restauranteExistente = criarRestauranteExistente();

        when(itemCardapioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(existente));

        when(restauranteGateway.buscarPorId(2L))
                .thenReturn(Optional.of(restauranteExistente));

        when(itemCardapioGateway.salvar(any(ItemCardapio.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ItemCardapio resultado =
                atualizarItemCardapioUseCase.execute(1L, novosDados);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pizza Atualizada", resultado.getNome());
        assertEquals("Descrição atualizada", resultado.getDescricao());
        assertEquals(59.90, resultado.getPreco(), 0.001);
        assertTrue(resultado.isSomenteNoLocal());
        assertEquals("/img/pizza-atualizada.png", resultado.getCaminhoFoto());

        assertSame(
                restauranteExistente,
                resultado.getRestaurante()
        );

        verify(itemCardapioGateway).buscarPorId(1L);
        verify(restauranteGateway).buscarPorId(2L);
        verify(itemCardapioGateway)
                .salvar(novosDados);
    }

    @Test
    void deveLancarExcecaoQuandoItemCardapioNaoExistir() {
        ItemCardapio novosDados = criarItemParaAtualizacao();

        when(itemCardapioGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ItemCardapioNaoEncontradoException exception = assertThrows(
                ItemCardapioNaoEncontradoException.class,
                () -> atualizarItemCardapioUseCase.execute(99L, novosDados)
        );

        assertEquals(
                "Item do cardápio não encontrado",
                exception.getMessage()
        );

        verify(itemCardapioGateway).buscarPorId(99L);
        verifyNoInteractions(restauranteGateway);
        verify(itemCardapioGateway, never())
                .salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        ItemCardapio existente = criarItemExistente();
        ItemCardapio novosDados = criarItemParaAtualizacao();

        when(itemCardapioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(existente));

        when(restauranteGateway.buscarPorId(2L))
                .thenReturn(Optional.empty());

        RestauranteNaoEncontradoException exception = assertThrows(
                RestauranteNaoEncontradoException.class,
                () -> atualizarItemCardapioUseCase.execute(1L, novosDados)
        );

        assertEquals(
                "Restaurante com id 2 não encontrado",
                exception.getMessage()
        );

        verify(itemCardapioGateway).buscarPorId(1L);
        verify(restauranteGateway).buscarPorId(2L);
        verify(itemCardapioGateway, never())
                .salvar(any(ItemCardapio.class));
    }

    private ItemCardapio criarItemExistente() {
        return new ItemCardapio(
                1L,
                "Pizza Original",
                "Descrição original",
                49.90,
                false,
                "/img/pizza-original.png",
                Restaurante.referenciaPorId(1L)
        );
    }

    private ItemCardapio criarItemParaAtualizacao() {
        return new ItemCardapio(
                null,
                "Pizza Atualizada",
                "Descrição atualizada",
                59.90,
                true,
                "/img/pizza-atualizada.png",
                Restaurante.referenciaPorId(2L)
        );
    }

    private Restaurante criarRestauranteExistente() {
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

        return new Restaurante(
                2L,
                "Restaurante Atualizado",
                "Rua B",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );
    }
}