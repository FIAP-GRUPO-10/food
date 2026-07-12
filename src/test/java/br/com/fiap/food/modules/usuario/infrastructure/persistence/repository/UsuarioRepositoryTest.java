package br.com.fiap.food.modules.usuario.infrastructure.persistence.repository;

import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    private TipoUsuarioEntity tipoUsuarioEntity;

    @BeforeEach
    void setUp() {
        tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("ADMIN")
                .observacao("Administrador")
                .build();
        tipoUsuarioEntity = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();
    }

    @Test
    void testSalvarUsuarioComSucesso() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
        entityManager.flush();

        assertNotNull(usuarioSalvo.getId());
        assertEquals("João Silva", usuarioSalvo.getNome());
        assertEquals("joao@email.com", usuarioSalvo.getEmail());
    }

    @Test
    void testBuscarUsuarioPorIdComSucesso() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nome("Maria Santos")
                .email("maria@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        entityManager.flush();

        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(usuarioEntity.getId());

        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("Maria Santos", usuarioEncontrado.get().getNome());
    }

    @Test
    void testBuscarUsuarioPorIdNaoEncontrado() {
        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(999L);

        assertFalse(usuarioEncontrado.isPresent());
    }

    @Test
    void testListarTodosOsUsuarios() {
        UsuarioEntity usuario1 = UsuarioEntity.builder()
                .nome("João")
                .email("joao@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        UsuarioEntity usuario2 = UsuarioEntity.builder()
                .nome("Maria")
                .email("maria@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        entityManager.flush();

        List<UsuarioEntity> usuarios = usuarioRepository.findAll();

        assertNotNull(usuarios);
        assertTrue(usuarios.size() >= 2);
    }

    @Test
    void testAtualizarUsuarioComSucesso() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nome("João Original")
                .email("joao@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        entityManager.flush();

        usuarioEntity.setNome("João Atualizado");
        usuarioEntity.setEmail("novo@email.com");
        UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioEntity);
        entityManager.flush();

        assertEquals("João Atualizado", usuarioAtualizado.getNome());
        assertEquals("novo@email.com", usuarioAtualizado.getEmail());
    }

    @Test
    void testDeletarUsuarioComSucesso() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nome("Pedro Silva")
                .email("pedro@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        entityManager.flush();

        Long usuarioId = usuarioEntity.getId();
        usuarioRepository.deleteById(usuarioId);
        entityManager.flush();

        Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(usuarioId);

        assertFalse(usuarioEncontrado.isPresent());
    }

    @Test
    void testSalvarMultiplosUsuarios() {
        UsuarioEntity usuario1 = UsuarioEntity.builder()
                .nome("Usuário 1")
                .email("usuario1@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        UsuarioEntity usuario2 = UsuarioEntity.builder()
                .nome("Usuário 2")
                .email("usuario2@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();
        UsuarioEntity usuario3 = UsuarioEntity.builder()
                .nome("Usuário 3")
                .email("usuario3@email.com")
                .tipoUsuario(tipoUsuarioEntity)
                .build();

        usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3));
        entityManager.flush();

        List<UsuarioEntity> usuarios = usuarioRepository.findAll();

        assertTrue(usuarios.size() >= 3);
    }
}
