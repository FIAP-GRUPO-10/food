package br.com.fiap.food.modules.usuario.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:test",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class UsuarioEntityMapperTest {

    @Autowired
    private UsuarioEntityMapper mapper;

    private TipoUsuarioEntity tipoUsuarioEntity;
    private TipoUsuario tipoUsuarioDomain;

    @BeforeEach
    void setUp() {
        tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", "Administrador");
        tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador");
    }

    @Test
    void testConverterUsuarioDomainParaEntity() {
        Usuario usuarioDomain = new Usuario(1L, "João Silva", "joao@email.com", tipoUsuarioDomain);

        UsuarioEntity usuarioEntity = mapper.toEntity(usuarioDomain);

        assertNotNull(usuarioEntity);
        assertEquals("João Silva", usuarioEntity.getNome());
        assertEquals("joao@email.com", usuarioEntity.getEmail());
    }

    @Test
    void testConverterUsuarioEntityParaDomain() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "Maria Santos", "maria@email.com", tipoUsuarioEntity);

        Usuario usuarioDomain = mapper.toDomain(usuarioEntity);

        assertNotNull(usuarioDomain);
        assertEquals(1L, usuarioDomain.getId());
        assertEquals("Maria Santos", usuarioDomain.getNome());
        assertEquals("maria@email.com", usuarioDomain.getEmail());
    }

    @Test
    void testConverterUsuarioDomainComNomeVazioParaEntity() {
        Usuario usuarioDomain = new Usuario(1L, "", "email@email.com", tipoUsuarioDomain);

        UsuarioEntity usuarioEntity = mapper.toEntity(usuarioDomain);

        assertEquals("", usuarioEntity.getNome());
    }

    @Test
    void testConverterUsuarioDomainComEmailVazioParaEntity() {
        Usuario usuarioDomain = new Usuario(1L, "João", "", tipoUsuarioDomain);

        UsuarioEntity usuarioEntity = mapper.toEntity(usuarioDomain);

        assertEquals("", usuarioEntity.getEmail());
    }

    @Test
    void testConverterUsuarioEntityComNomeVazioParaDomain() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "", "email@email.com", tipoUsuarioEntity);

        Usuario usuarioDomain = mapper.toDomain(usuarioEntity);

        assertEquals("", usuarioDomain.getNome());
    }

    @Test
    void testConverterUsuarioDomainComIdNuloParaEntity() {
        Usuario usuarioDomain = new Usuario(null, "João Silva", "joao@email.com", tipoUsuarioDomain);

        UsuarioEntity usuarioEntity = mapper.toEntity(usuarioDomain);

        assertNull(usuarioEntity.getId());
        assertEquals("João Silva", usuarioEntity.getNome());
    }

    @Test
    void testConverterUsuarioEntityComTipoNuloParaDomain() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "João Silva", "joao@email.com", null);

        Usuario usuarioDomain = mapper.toDomain(usuarioEntity);

        assertNotNull(usuarioDomain);
        assertNull(usuarioDomain.getTipoUsuario());
    }
}
