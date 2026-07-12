package br.com.fiap.food.modules.restaurante.application.usecase;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteNaoEncontradoException;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private AtualizarRestauranteUseCase useCase;

    @Test
    void deveAtualizarRestaurante() {

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", null);
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Fazenda",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        Restaurante atualizacao = new Restaurante(
                1L,
                "Restaurante Atualizado",
                "Rua B",
                "Japonesa",
                LocalTime.of(12, 0),
                LocalTime.of(23, 30),
                dono
        );

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(restaurante));

        when(usuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(dono));

        when(restauranteGateway.atualizar(any(Restaurante.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Restaurante resultado = useCase.execute(1L, atualizacao);

        assertEquals("Restaurante Atualizado", resultado.getNome());
        assertEquals("Rua B", resultado.getEndereco());
        assertEquals("Japonesa", resultado.getTipoCozinha());
        assertEquals(LocalTime.of(12, 0), resultado.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 30), resultado.getHorarioFechamento());
        assertEquals(dono, resultado.getDono());

        verify(restauranteGateway).buscarPorId(1L);
        verify(usuarioGateway).buscarPorId(1L);
        verify(restauranteGateway).atualizar(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RestauranteNaoEncontradoException.class,
                () -> useCase.execute(1L, mock(Restaurante.class))
        );

        verify(restauranteGateway).buscarPorId(1L);
        verifyNoInteractions(usuarioGateway);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", null);
        Usuario dono = new Usuario(1L, "Thiago", "thiago@email.com", tipoUsuario);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante",
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                dono
        );

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(restaurante));

        when(usuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> useCase.execute(1L, restaurante)
        );

        verify(restauranteGateway).buscarPorId(1L);
        verify(usuarioGateway).buscarPorId(1L);
        verify(restauranteGateway, never()).atualizar(any());
    }
}
