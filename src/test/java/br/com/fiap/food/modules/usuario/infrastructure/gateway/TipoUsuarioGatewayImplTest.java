package br.com.fiap.food.modules.usuario.infrastructure.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.exception.TipoUsuarioJaCadastradoException;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.TipoUsuarioRepository;
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
class TipoUsuarioGatewayImplTest {

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Mock
    private TipoUsuarioEntityMapper mapper;

    private TipoUsuarioGatewayImpl tipoUsuarioGateway;

    @BeforeEach
    void setUp() {
        tipoUsuarioGateway = new TipoUsuarioGatewayImpl(tipoUsuarioRepository, mapper);
    }

    @Test
    void testSalvarTipoUsuarioComSucesso() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(null, "ADMIN", "Administrador");

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setNome("ADMIN");
        tipoEntity.setObservacao("Administrador");

        TipoUsuarioEntity tipoSalvo = new TipoUsuarioEntity();
        tipoSalvo.setId(1L);
        tipoSalvo.setNome("ADMIN");
        tipoSalvo.setObservacao("Administrador");

        when(mapper.toEntity(tipoUsuarioDomain)).thenReturn(tipoEntity);
        when(tipoUsuarioRepository.findByNome("ADMIN")).thenReturn(Optional.empty());
        when(tipoUsuarioRepository.save(tipoEntity)).thenReturn(tipoSalvo);
        when(mapper.toDomain(tipoSalvo)).thenReturn(new TipoUsuario(1L, "ADMIN", "Administrador"));

        TipoUsuario resultado = tipoUsuarioGateway.salvar(tipoUsuarioDomain);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ADMIN", resultado.getNome());
        verify(tipoUsuarioRepository).findByNome("ADMIN");
        verify(tipoUsuarioRepository).save(any(TipoUsuarioEntity.class));
    }

    @Test
    void testSalvarTipoUsuarioJaExistente() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(null, "ADMIN", "Administrador");

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);
        tipoEntity.setNome("ADMIN");

        TipoUsuarioEntity tipoEntityExistente = new TipoUsuarioEntity();
        tipoEntityExistente.setId(1L);
        tipoEntityExistente.setNome("ADMIN");

        when(mapper.toEntity(tipoUsuarioDomain)).thenReturn(tipoEntity);
        when(tipoUsuarioRepository.findByNome("ADMIN")).thenReturn(Optional.of(tipoEntityExistente));

        assertThrows(TipoUsuarioJaCadastradoException.class, () -> {
            tipoUsuarioGateway.salvar(tipoUsuarioDomain);
        });

        verify(tipoUsuarioRepository).findByNome("ADMIN");
        verify(tipoUsuarioRepository, never()).save(any());
    }

    @Test
    void testBuscarTipoUsuarioPorIdComSucesso() {
        Long id = 1L;

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(id);
        tipoEntity.setNome("ADMIN");
        tipoEntity.setObservacao("Administrador");

        TipoUsuario tipoUsuarioDomain = new TipoUsuario(id, "ADMIN", "Administrador");

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(tipoEntity));
        when(mapper.toDomain(tipoEntity)).thenReturn(tipoUsuarioDomain);

        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals("ADMIN", resultado.get().getNome());
        verify(tipoUsuarioRepository).findById(id);
    }

    @Test
    void testBuscarTipoUsuarioPorIdNaoEncontrado() {
        Long id = 999L;

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorId(id);

        assertFalse(resultado.isPresent());
        verify(tipoUsuarioRepository).findById(id);
    }

    @Test
    void testListarTodosTiposUsuariosComSucesso() {
        TipoUsuarioEntity tipoAdmin = new TipoUsuarioEntity();
        tipoAdmin.setId(1L);
        tipoAdmin.setNome("ADMIN");

        TipoUsuarioEntity tipoUser = new TipoUsuarioEntity();
        tipoUser.setId(2L);
        tipoUser.setNome("USER");

        List<TipoUsuarioEntity> entities = Arrays.asList(tipoAdmin, tipoUser);

        TipoUsuario tipoAdminDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
        TipoUsuario tipoUserDomain = new TipoUsuario(2L, "USER", "Usuário Normal");

        when(tipoUsuarioRepository.findAll()).thenReturn(entities);
        when(mapper.toDomain(tipoAdmin)).thenReturn(tipoAdminDomain);
        when(mapper.toDomain(tipoUser)).thenReturn(tipoUserDomain);

        List<TipoUsuario> resultado = tipoUsuarioGateway.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("ADMIN", resultado.get(0).getNome());
        assertEquals("USER", resultado.get(1).getNome());
        verify(tipoUsuarioRepository).findAll();
    }

    @Test
    void testAtualizarTipoUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(null, "ADMIN", "Novo Administrador");

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(id);
        tipoEntity.setNome("ADMIN");
        tipoEntity.setObservacao("Novo Administrador");

        TipoUsuarioEntity tipoAtualizado = new TipoUsuarioEntity();
        tipoAtualizado.setId(id);
        tipoAtualizado.setNome("ADMIN");
        tipoAtualizado.setObservacao("Novo Administrador");

        when(mapper.toEntity(tipoUsuarioDomain)).thenReturn(tipoEntity);
        when(tipoUsuarioRepository.save(tipoEntity)).thenReturn(tipoAtualizado);
        when(mapper.toDomain(tipoAtualizado)).thenReturn(new TipoUsuario(id, "ADMIN", "Novo Administrador"));

        TipoUsuario resultado = tipoUsuarioGateway.atualizar(id, tipoUsuarioDomain);

        assertNotNull(resultado);
        assertEquals("Novo Administrador", resultado.getObservacao());
        verify(tipoUsuarioRepository).save(any(TipoUsuarioEntity.class));
    }

    @Test
    void testDeletarTipoUsuarioComSucesso() {
        Long id = 1L;

        tipoUsuarioGateway.deletar(id);

        verify(tipoUsuarioRepository).deleteById(id);
    }

    @Test
    void testExistsByIdComSucesso() {
        Long id = 1L;

        when(tipoUsuarioRepository.existsById(id)).thenReturn(true);

        boolean resultado = tipoUsuarioGateway.existsById(id);

        assertTrue(resultado);
        verify(tipoUsuarioRepository).existsById(id);
    }

    @Test
    void testExistsByIdNaoEncontrado() {
        Long id = 999L;

        when(tipoUsuarioRepository.existsById(id)).thenReturn(false);

        boolean resultado = tipoUsuarioGateway.existsById(id);

        assertFalse(resultado);
        verify(tipoUsuarioRepository).existsById(id);
    }
}
