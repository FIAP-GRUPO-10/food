package br.com.fiap.food.modules.usuario.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioEnumTest {

    @Test
    void testValoresEnumExistem() {
        assertNotNull(TipoUsuarioEnum.CLIENTE);
        assertNotNull(TipoUsuarioEnum.DONO);
    }

    @Test
    void testEnumCliente() {
        TipoUsuarioEnum tipo = TipoUsuarioEnum.CLIENTE;

        assertEquals("CLIENTE", tipo.name());
        assertEquals(0, tipo.ordinal());
    }

    @Test
    void testEnumDono() {
        TipoUsuarioEnum tipo = TipoUsuarioEnum.DONO;

        assertEquals("DONO", tipo.name());
        assertEquals(1, tipo.ordinal());
    }

    @Test
    void testEnumValores() {
        TipoUsuarioEnum[] valores = TipoUsuarioEnum.values();

        assertEquals(2, valores.length);
        assertEquals(TipoUsuarioEnum.CLIENTE, valores[0]);
        assertEquals(TipoUsuarioEnum.DONO, valores[1]);
    }

    @Test
    void testEnumValueOf() {
        TipoUsuarioEnum cliente = TipoUsuarioEnum.valueOf("CLIENTE");
        TipoUsuarioEnum dono = TipoUsuarioEnum.valueOf("DONO");

        assertEquals(TipoUsuarioEnum.CLIENTE, cliente);
        assertEquals(TipoUsuarioEnum.DONO, dono);
    }

    @Test
    void testEnumIgualdade() {
        TipoUsuarioEnum tipo1 = TipoUsuarioEnum.CLIENTE;
        TipoUsuarioEnum tipo2 = TipoUsuarioEnum.CLIENTE;

        assertEquals(tipo1, tipo2);
        assertTrue(tipo1 == tipo2);
    }

    @Test
    void testEnumDiferente() {
        TipoUsuarioEnum cliente = TipoUsuarioEnum.CLIENTE;
        TipoUsuarioEnum dono = TipoUsuarioEnum.DONO;

        assertNotEquals(cliente, dono);
        assertFalse(cliente == dono);
    }

    @Test
    void testEnumToString() {
        TipoUsuarioEnum cliente = TipoUsuarioEnum.CLIENTE;
        TipoUsuarioEnum dono = TipoUsuarioEnum.DONO;

        assertEquals("CLIENTE", cliente.toString());
        assertEquals("DONO", dono.toString());
    }

    @Test
    void testEnumValueOfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            TipoUsuarioEnum.valueOf("INVALIDO");
        });
    }

    @Test
    void testEnumInstancia() {
        TipoUsuarioEnum tipo = TipoUsuarioEnum.CLIENTE;

        assertTrue(tipo instanceof Enum);
        assertTrue(tipo instanceof TipoUsuarioEnum);
    }
}
