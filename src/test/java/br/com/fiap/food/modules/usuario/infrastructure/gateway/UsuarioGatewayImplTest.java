package br.com.fiap.food.modules.usuario.infrastructure.gateway;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.tipousuario.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioGatewayImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Mock
    private UsuarioEntityMapper mapper;

    private UsuarioGatewayImpl usuarioGateway;

    @BeforeEach
    void setUp() {
        usuarioGateway = new UsuarioGatewayImpl(usuarioRepository, tipoUsuarioRepository, mapper);
    }

    @Test
    void testSalvarUsuarioComSucesso() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioDomain = new Usuario(null, "João Silva", "joao@email.com", tipoUsuarioDomain);

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);
        tipoEntity.setNome("ADMIN");

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("João Silva");
        usuarioEntity.setEmail("joao@email.com");
        usuarioEntity.setTipoUsuario(tipoEntity);

        UsuarioEntity usuarioSalvo = new UsuarioEntity();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("João Silva");
        usuarioSalvo.setEmail("joao@email.com");
        usuarioSalvo.setTipoUsuario(tipoEntity);

        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(tipoEntity));
        when(mapper.toEntity(usuarioDomain)).thenReturn(usuarioEntity);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioSalvo);
        when(mapper.toDomain(usuarioSalvo)).thenReturn(new Usuario(1L, "João Silva", "joao@email.com", tipoUsuarioDomain));

        Usuario resultado = usuarioGateway.salvar(usuarioDomain);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        verify(tipoUsuarioRepository).findById(1L);
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

    @Test
    void testSalvarUsuarioComTipoNaoEncontrado() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(999L, "INVALIDO", "Inválido");
        Usuario usuarioDomain = new Usuario(null, "João Silva", "joao@email.com", tipoUsuarioDomain);

        when(tipoUsuarioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> {
            usuarioGateway.salvar(usuarioDomain);
        });

        verify(tipoUsuarioRepository).findById(999L);
    }

    @Test
    void testBuscarUsuarioPorIdComSucesso() {
        Long id = 1L;
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(id);
        usuarioEntity.setNome("João Silva");
        usuarioEntity.setEmail("joao@email.com");
        usuarioEntity.setTipoUsuario(tipoEntity);

        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioDomain = new Usuario(id, "João Silva", "joao@email.com", tipoUsuarioDomain);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        when(mapper.toDomain(usuarioEntity)).thenReturn(usuarioDomain);

        Optional<Usuario> resultado = usuarioGateway.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals("João Silva", resultado.get().getNome());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testBuscarUsuarioPorIdNaoEncontrado() {
        Long id = 999L;

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioGateway.buscarPorId(id);

        assertFalse(resultado.isPresent());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testListarTodosUsuariosComSucesso() {
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);

        UsuarioEntity usuario1 = new UsuarioEntity();
        usuario1.setId(1L);
        usuario1.setNome("João");
        usuario1.setEmail("joao@email.com");
        usuario1.setTipoUsuario(tipoEntity);

        UsuarioEntity usuario2 = new UsuarioEntity();
        usuario2.setId(2L);
        usuario2.setNome("Maria");
        usuario2.setEmail("maria@email.com");
        usuario2.setTipoUsuario(tipoEntity);

        List<UsuarioEntity> entities = Arrays.asList(usuario1, usuario2);

        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioDomain1 = new Usuario(1L, "João", "joao@email.com", tipoUsuarioDomain);
        Usuario usuarioDomain2 = new Usuario(2L, "Maria", "maria@email.com", tipoUsuarioDomain);

        when(usuarioRepository.findAll()).thenReturn(entities);
        when(mapper.toDomain(usuario1)).thenReturn(usuarioDomain1);
        when(mapper.toDomain(usuario2)).thenReturn(usuarioDomain2);

        List<Usuario> resultado = usuarioGateway.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testAtualizarUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
        Usuario usuarioDomain = new Usuario(null, "João Atualizado", "novo@email.com", tipoUsuarioDomain);

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(id);
        usuarioEntity.setNome("João Atualizado");
        usuarioEntity.setEmail("novo@email.com");
        usuarioEntity.setTipoUsuario(tipoEntity);

        UsuarioEntity usuarioAtualizado = new UsuarioEntity();
        usuarioAtualizado.setId(id);
        usuarioAtualizado.setNome("João Atualizado");
        usuarioAtualizado.setEmail("novo@email.com");
        usuarioAtualizado.setTipoUsuario(tipoEntity);

        when(mapper.toEntity(usuarioDomain)).thenReturn(usuarioEntity);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioAtualizado);
        when(mapper.toDomain(usuarioAtualizado)).thenReturn(new Usuario(id, "João Atualizado", "novo@email.com", tipoUsuarioDomain));

        Usuario resultado = usuarioGateway.atualizar(id, usuarioDomain);

        assertNotNull(resultado);
        assertEquals("João Atualizado", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

    @Test
    void testDeletarUsuarioComSucesso() {
        Long id = 1L;

        usuarioGateway.deletar(id);

        verify(usuarioRepository).deleteById(id);
    }
}
