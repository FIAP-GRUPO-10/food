package br.com.fiap.food.modules.restaurante.domain.entity;

import br.com.fiap.food.modules.restaurante.domain.exception.HorarioInvalidoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteSemDonoException;
import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Usuario criarUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        return new Usuario(1L, "João Silva", "joao@email.com", tipoUsuario);
    }

    @Test
    void deveCriarRestauranteComTodosDados() {

        Usuario usuario = criarUsuario();

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante da Fazenda",
                "Rua dos Restaurantes, 123",
                "Australiana",
                LocalTime.of(10, 0),
                LocalTime.of(23, 30),
                usuario
        );

        assertEquals(1L, restaurante.getId());
        assertEquals("Restaurante da Fazenda", restaurante.getNome());
        assertEquals("Rua dos Restaurantes, 123", restaurante.getEndereco());
        assertEquals("Australiana", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 30), restaurante.getHorarioFechamento());
        assertEquals(usuario, restaurante.getDono());
    }

    @Test
    void deveLancarExcecaoQuandoHorarioAberturaForMaiorQueHorarioFechamento() {

        Usuario usuario = criarUsuario();

        HorarioInvalidoException exception = assertThrows(
                HorarioInvalidoException.class,
                () -> new Restaurante(
                        1L,
                        "Restaurante",
                        "Rua A",
                        "Italiana",
                        LocalTime.of(22, 0),
                        LocalTime.of(10, 0),
                        usuario
                )
        );

        assertEquals(
                "Horário de abertura deve ser anterior ao horário de fechamento",
                exception.getMessage()
        );
    }

    @Test
    void devePermitirHorarioAberturaAnteriorAoFechamento() {

        Usuario usuario = criarUsuario();

        assertDoesNotThrow(() -> new Restaurante(
                1L,
                "Restaurante",
                "Rua A",
                "Japonesa",
                LocalTime.of(8, 0),
                LocalTime.of(18, 0),
                usuario
        ));
    }

    @Test
    void deveLancarExcecaoQuandoDonoForNulo() {

        RestauranteSemDonoException exception = assertThrows(
                RestauranteSemDonoException.class,
                () -> new Restaurante(
                        1L,
                        "Restaurante",
                        "Rua A",
                        "Mexicana",
                        LocalTime.of(10, 0),
                        LocalTime.of(22, 0),
                        null
                )
        );

        assertEquals("O Restaurante deve possuir um dono", exception.getMessage());
    }

    @Test
    void naoDevePermitirHorarioAberturaIgualAoHorarioFechamento() {
        Usuario usuario = criarUsuario();

        assertThrows(HorarioInvalidoException.class, () -> new Restaurante(
                1L,
                "Restaurante 24h",
                "Rua A",
                "Brasileira",
                LocalTime.of(10, 0),
                LocalTime.of(10, 0),
                usuario
        ));
    }
}
