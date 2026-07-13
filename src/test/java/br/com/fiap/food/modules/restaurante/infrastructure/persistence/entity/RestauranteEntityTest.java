package br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity;

import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteEntityTest {

    @Test
    void testCriarRestauranteEntityComBuilder() {
        UsuarioEntity dono = criarDono();

        RestauranteEntity restaurante = RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Sabor")
                .endereco("Rua das Flores, 123")
                .tipoCozinha("Italiana")
                .horarioAbertura(LocalTime.of(8, 0))
                .horarioFechamento(LocalTime.of(22, 0))
                .dono(dono)
                .build();

        assertNotNull(restaurante);
        assertEquals(1L, restaurante.getId());
        assertEquals("Restaurante Sabor", restaurante.getNome());
        assertEquals("Rua das Flores, 123", restaurante.getEndereco());
        assertEquals("Italiana", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(8, 0), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 0), restaurante.getHorarioFechamento());
        assertEquals(dono, restaurante.getDono());
    }

    @Test
    void testCriarRestauranteEntityComConstrutorVazio() {
        RestauranteEntity restaurante = new RestauranteEntity();

        assertNull(restaurante.getId());
        assertNull(restaurante.getNome());
        assertNull(restaurante.getEndereco());
        assertNull(restaurante.getTipoCozinha());
        assertNull(restaurante.getHorarioAbertura());
        assertNull(restaurante.getHorarioFechamento());
        assertNull(restaurante.getDono());
    }

    @Test
    void testCriarRestauranteEntityComConstrutorCompleto() {
        UsuarioEntity dono = criarDono();

        RestauranteEntity restaurante = new RestauranteEntity(
                2L,
                "Cantina Bella",
                "Av. Paulista, 1000",
                "Italiana",
                LocalTime.of(11, 30),
                LocalTime.of(23, 0),
                dono
        );

        assertEquals(2L, restaurante.getId());
        assertEquals("Cantina Bella", restaurante.getNome());
        assertEquals("Av. Paulista, 1000", restaurante.getEndereco());
        assertEquals("Italiana", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(11, 30), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 0), restaurante.getHorarioFechamento());
        assertEquals(dono, restaurante.getDono());
    }

    @Test
    void testSetarPropriedadesRestauranteEntity() {
        UsuarioEntity dono = criarDono();

        RestauranteEntity restaurante = new RestauranteEntity();

        restaurante.setId(3L);
        restaurante.setNome("Burger House");
        restaurante.setEndereco("Rua A, 500");
        restaurante.setTipoCozinha("Hamburgueria");
        restaurante.setHorarioAbertura(LocalTime.of(10, 0));
        restaurante.setHorarioFechamento(LocalTime.of(23, 30));
        restaurante.setDono(dono);

        assertEquals(3L, restaurante.getId());
        assertEquals("Burger House", restaurante.getNome());
        assertEquals("Rua A, 500", restaurante.getEndereco());
        assertEquals("Hamburgueria", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(10, 0), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 30), restaurante.getHorarioFechamento());
        assertEquals(dono, restaurante.getDono());
    }

    @Test
    void testRestauranteEntityComNomeVazio() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .nome("")
                .build();

        assertEquals("", restaurante.getNome());
    }

    @Test
    void testRestauranteEntityComEnderecoVazio() {
        RestauranteEntity restaurante = RestauranteEntity.builder()
                .endereco("")
                .build();

        assertEquals("", restaurante.getEndereco());
    }

    private UsuarioEntity criarDono() {
        TipoUsuarioEntity tipoUsuario = new TipoUsuarioEntity(
                1L,
                "DONO",
                "Dono do restaurante"
        );

        return UsuarioEntity.builder()
                .id(10L)
                .nome("Carlos")
                .email("carlos@email.com")
                .tipoUsuario(tipoUsuario)
                .build();
    }
}