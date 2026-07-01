package br.com.fiap.food.modules.usuario.application.usecase.tipousuario;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    private AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;

    @BeforeEach
    void setUp() {
        atualizarTipoUsuarioUseCase = new AtualizarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Test
    void testAtualizarTipoUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuarioAtualizado = new TipoUsuario(id, "ADMIN", "Administrador do Sistema Atualizado");
        TipoUsuario tipoUsuarioRetorno = new TipoUsuario(id, "ADMIN", "Administrador do Sistema Atualizado");

        when(tipoUsuarioGateway.atualizar(id, tipoUsuarioAtualizado)).thenReturn(tipoUsuarioRetorno);

        TipoUsuario resultado = atualizarTipoUsuarioUseCase.execute(id, tipoUsuarioAtualizado);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Administrador do Sistema Atualizado", resultado.getObservacao());
        verify(tipoUsuarioGateway).atualizar(id, tipoUsuarioAtualizado);
    }

    @Test
    void testAtualizarTipoUsuarioComObservacaoNova() {
        Long id = 2L;
        TipoUsuario tipoUsuarioAtualizado = new TipoUsuario(id, "USER", "Novo Usuário Comum");
        TipoUsuario tipoUsuarioRetorno = new TipoUsuario(id, "USER", "Novo Usuário Comum");

        when(tipoUsuarioGateway.atualizar(id, tipoUsuarioAtualizado)).thenReturn(tipoUsuarioRetorno);

        TipoUsuario resultado = atualizarTipoUsuarioUseCase.execute(id, tipoUsuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("Novo Usuário Comum", resultado.getObservacao());
        verify(tipoUsuarioGateway).atualizar(id, tipoUsuarioAtualizado);
    }

    @Test
    void testAtualizarTipoUsuarioComNomeNovo() {
        Long id = 3L;
        TipoUsuario tipoUsuarioAtualizado = new TipoUsuario(id, "SUPER_ADMIN", "Super Administrador");
        TipoUsuario tipoUsuarioRetorno = new TipoUsuario(id, "SUPER_ADMIN", "Super Administrador");

        when(tipoUsuarioGateway.atualizar(id, tipoUsuarioAtualizado)).thenReturn(tipoUsuarioRetorno);

        TipoUsuario resultado = atualizarTipoUsuarioUseCase.execute(id, tipoUsuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("SUPER_ADMIN", resultado.getNome());
        verify(tipoUsuarioGateway).atualizar(id, tipoUsuarioAtualizado);
    }
}
