package br.com.fiap.food.modules.usuario.application.usecase;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private CriarUsuarioUseCase criarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        criarUsuarioUseCase = new CriarUsuarioUseCase(usuarioGateway);
    }

    @Test
    void testCriarUsuarioComSucesso() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioEntrada = new Usuario(null, "João Silva", "joao@email.com", tipoUsuario);
        Usuario usuarioSaida = new Usuario(1L, "João Silva", "joao@email.com", tipoUsuario);

        when(usuarioGateway.salvar(usuarioEntrada)).thenReturn(usuarioSaida);

        Usuario resultado = criarUsuarioUseCase.execute(usuarioEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        assertEquals(tipoUsuario, resultado.getTipoUsuario());
        verify(usuarioGateway).salvar(usuarioEntrada);
    }

    @Test
    void testCriarUsuarioComNomeNulo() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(null, null, "email@email.com", tipoUsuario);

        when(usuarioGateway.salvar(usuario)).thenReturn(new Usuario(1L, null, "email@email.com", tipoUsuario));

        Usuario resultado = criarUsuarioUseCase.execute(usuario);

        assertNotNull(resultado);
        assertNull(resultado.getNome());
    }

    @Test
    void testCriarUsuarioComEmailNulo() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuario = new Usuario(null, "João Silva", null, tipoUsuario);

        when(usuarioGateway.salvar(usuario)).thenReturn(new Usuario(1L, "João Silva", null, tipoUsuario));

        Usuario resultado = criarUsuarioUseCase.execute(usuario);

        assertNotNull(resultado);
        assertNull(resultado.getEmail());
    }
}
