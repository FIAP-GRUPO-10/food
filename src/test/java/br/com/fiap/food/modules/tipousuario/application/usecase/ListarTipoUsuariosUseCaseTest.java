package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarTipoUsuariosUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private ListarTipoUsuariosUseCase listarTipoUsuariosUseCase;

    @BeforeEach
    void setUp() {
        listarTipoUsuariosUseCase = new ListarTipoUsuariosUseCase(tipoUsuarioGateway);
    }

    @Test
    void testListarTiposUsuariosComSucesso() {
        TipoUsuario tipoAdmin = new TipoUsuario(1L, "ADMIN", "Administrador");
        TipoUsuario tipoUser = new TipoUsuario(2L, "USER", "Usuário Normal");

        List<TipoUsuario> tiposEsperados = Arrays.asList(tipoAdmin, tipoUser);

        when(tipoUsuarioGateway.listarTodos()).thenReturn(tiposEsperados);

        List<TipoUsuario> resultado = listarTipoUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("ADMIN", resultado.get(0).getNome());
        assertEquals("USER", resultado.get(1).getNome());
        verify(tipoUsuarioGateway).listarTodos();
    }

    @Test
    void testListarTiposUsuariosVazio() {
        when(tipoUsuarioGateway.listarTodos()).thenReturn(Collections.emptyList());

        List<TipoUsuario> resultado = listarTipoUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(tipoUsuarioGateway).listarTodos();
    }

    @Test
    void testListarTiposUsuariosComUmItem() {
        TipoUsuario tipoAdmin = new TipoUsuario(1L, "ADMIN", "Administrador");

        List<TipoUsuario> tiposEsperados = Collections.singletonList(tipoAdmin);

        when(tipoUsuarioGateway.listarTodos()).thenReturn(tiposEsperados);

        List<TipoUsuario> resultado = listarTipoUsuariosUseCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ADMIN", resultado.get(0).getNome());
        verify(tipoUsuarioGateway).listarTodos();
    }
}
