package br.com.fiap.food.modules.tipousuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;

    @BeforeEach
    void setUp() {
        criarTipoUsuarioUseCase = new CriarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Test
    void testCriarTipoUsuarioComSucesso() {
        TipoUsuario tipoUsuarioEntrada = new TipoUsuario(null, "ADMIN", "Administrador do Sistema");
        TipoUsuario tipoUsuarioSaida = new TipoUsuario(1L, "ADMIN", "Administrador do Sistema");

        when(tipoUsuarioGateway.salvar(tipoUsuarioEntrada)).thenReturn(tipoUsuarioSaida);

        TipoUsuario resultado = criarTipoUsuarioUseCase.execute(tipoUsuarioEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ADMIN", resultado.getNome());
        assertEquals("Administrador do Sistema", resultado.getObservacao());
        verify(tipoUsuarioGateway).salvar(tipoUsuarioEntrada);
    }

    @Test
    void testCriarTipoUsuarioUSER() {
        TipoUsuario tipoUsuarioEntrada = new TipoUsuario(null, "USER", "Usuário Normal");
        TipoUsuario tipoUsuarioSaida = new TipoUsuario(2L, "USER", "Usuário Normal");

        when(tipoUsuarioGateway.salvar(tipoUsuarioEntrada)).thenReturn(tipoUsuarioSaida);

        TipoUsuario resultado = criarTipoUsuarioUseCase.execute(tipoUsuarioEntrada);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("USER", resultado.getNome());
        verify(tipoUsuarioGateway).salvar(tipoUsuarioEntrada);
    }

    @Test
    void testCriarTipoUsuarioComObservacaoVazia() {
        TipoUsuario tipoUsuarioEntrada = new TipoUsuario(null, "GUEST", "");
        TipoUsuario tipoUsuarioSaida = new TipoUsuario(3L, "GUEST", "");

        when(tipoUsuarioGateway.salvar(tipoUsuarioEntrada)).thenReturn(tipoUsuarioSaida);

        TipoUsuario resultado = criarTipoUsuarioUseCase.execute(tipoUsuarioEntrada);

        assertNotNull(resultado);
        assertEquals("", resultado.getObservacao());
    }
}
