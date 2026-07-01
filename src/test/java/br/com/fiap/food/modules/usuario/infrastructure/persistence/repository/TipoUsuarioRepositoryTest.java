package br.com.fiap.food.modules.usuario.infrastructure.persistence.repository;

import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TipoUsuarioRepositoryTest {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.clear();
    }

    @Test
    void testSalvarTipoUsuarioComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("ADMIN")
                .observacao("Administrador do Sistema")
                .build();

        TipoUsuarioEntity tipoSalvo = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        assertNotNull(tipoSalvo.getId());
        assertEquals("ADMIN", tipoSalvo.getNome());
        assertEquals("Administrador do Sistema", tipoSalvo.getObservacao());
    }

    @Test
    void testBuscarTipoUsuarioPorIdComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("USER")
                .observacao("Usuário Normal")
                .build();
        tipoUsuarioEntity = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        Optional<TipoUsuarioEntity> tipoEncontrado = tipoUsuarioRepository.findById(tipoUsuarioEntity.getId());

        assertTrue(tipoEncontrado.isPresent());
        assertEquals("USER", tipoEncontrado.get().getNome());
    }

    @Test
    void testBuscarTipoUsuarioPorIdNaoEncontrado() {
        Optional<TipoUsuarioEntity> tipoEncontrado = tipoUsuarioRepository.findById(999L);

        assertFalse(tipoEncontrado.isPresent());
    }

    @Test
    void testBuscarTipoUsuarioPorNomeComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("GUEST")
                .observacao("Convidado")
                .build();
        tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        Optional<TipoUsuarioEntity> tipoEncontrado = tipoUsuarioRepository.findByNome("GUEST");

        assertTrue(tipoEncontrado.isPresent());
        assertEquals("GUEST", tipoEncontrado.get().getNome());
    }

    @Test
    void testBuscarTipoUsuarioPorNomeNaoEncontrado() {
        Optional<TipoUsuarioEntity> tipoEncontrado = tipoUsuarioRepository.findByNome("INEXISTENTE");

        assertFalse(tipoEncontrado.isPresent());
    }

    @Test
    void testListarTodosTiposUsuarios() {
        TipoUsuarioEntity tipo1 = TipoUsuarioEntity.builder()
                .nome("ADMIN")
                .observacao("Administrador")
                .build();
        TipoUsuarioEntity tipo2 = TipoUsuarioEntity.builder()
                .nome("USER")
                .observacao("Usuário Normal")
                .build();

        tipoUsuarioRepository.save(tipo1);
        tipoUsuarioRepository.save(tipo2);
        entityManager.flush();

        List<TipoUsuarioEntity> tipos = tipoUsuarioRepository.findAll();

        assertNotNull(tipos);
        assertTrue(tipos.size() >= 2);
    }

    @Test
    void testAtualizarTipoUsuarioComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("MODERATOR")
                .observacao("Moderador")
                .build();
        tipoUsuarioEntity = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        tipoUsuarioEntity.setObservacao("Moderador Atualizado");
        TipoUsuarioEntity tipoAtualizado = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        assertEquals("Moderador Atualizado", tipoAtualizado.getObservacao());
    }

    @Test
    void testDeletarTipoUsuarioComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("TEMP")
                .observacao("Tipo Temporário")
                .build();
        tipoUsuarioEntity = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        Long tipoId = tipoUsuarioEntity.getId();
        tipoUsuarioRepository.deleteById(tipoId);
        entityManager.flush();

        Optional<TipoUsuarioEntity> tipoEncontrado = tipoUsuarioRepository.findById(tipoId);

        assertFalse(tipoEncontrado.isPresent());
    }

    @Test
    void testExistsByIdComSucesso() {
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome("ADMIN")
                .observacao("Administrador")
                .build();
        tipoUsuarioEntity = tipoUsuarioRepository.save(tipoUsuarioEntity);
        entityManager.flush();

        boolean existe = tipoUsuarioRepository.existsById(tipoUsuarioEntity.getId());

        assertTrue(existe);
    }

    @Test
    void testExistsByIdNaoEncontrado() {
        boolean existe = tipoUsuarioRepository.existsById(999L);

        assertFalse(existe);
    }

    @Test
    void testSalvarMultiplosTiposUsuarios() {
        TipoUsuarioEntity tipo1 = TipoUsuarioEntity.builder()
                .nome("TIPO1")
                .observacao("Tipo 1")
                .build();
        TipoUsuarioEntity tipo2 = TipoUsuarioEntity.builder()
                .nome("TIPO2")
                .observacao("Tipo 2")
                .build();
        TipoUsuarioEntity tipo3 = TipoUsuarioEntity.builder()
                .nome("TIPO3")
                .observacao("Tipo 3")
                .build();

        tipoUsuarioRepository.saveAll(List.of(tipo1, tipo2, tipo3));
        entityManager.flush();

        List<TipoUsuarioEntity> tipos = tipoUsuarioRepository.findAll();

        assertTrue(tipos.size() >= 3);
    }
}
