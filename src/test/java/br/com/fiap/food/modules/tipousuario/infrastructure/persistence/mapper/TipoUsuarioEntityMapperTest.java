package br.com.fiap.food.modules.tipousuario.infrastructure.persistence.mapper;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
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
class TipoUsuarioEntityMapperTest {

    @Autowired
    private TipoUsuarioEntityMapper mapper;

    @Test
    void testConverterTipoUsuarioDomainParaEntity() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", "Administrador do Sistema");

        TipoUsuarioEntity tipoUsuarioEntity = mapper.toEntity(tipoUsuarioDomain);

        assertNotNull(tipoUsuarioEntity);
        assertEquals(1L, tipoUsuarioEntity.getId());
        assertEquals("ADMIN", tipoUsuarioEntity.getNome());
        assertEquals("Administrador do Sistema", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testConverterTipoUsuarioEntityParaDomain() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(2L, "USER", "Usuário Normal");

        TipoUsuario tipoUsuarioDomain = mapper.toDomain(tipoUsuarioEntity);

        assertNotNull(tipoUsuarioDomain);
        assertEquals(2L, tipoUsuarioDomain.getId());
        assertEquals("USER", tipoUsuarioDomain.getNome());
        assertEquals("Usuário Normal", tipoUsuarioDomain.getObservacao());
    }

    @Test
    void testConverterTipoUsuarioDomainComIdNuloParaEntity() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(null, "GUEST", "Convidado");

        TipoUsuarioEntity tipoUsuarioEntity = mapper.toEntity(tipoUsuarioDomain);

        assertNull(tipoUsuarioEntity.getId());
        assertEquals("GUEST", tipoUsuarioEntity.getNome());
        assertEquals("Convidado", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testConverterTipoUsuarioDomainComObservacaoVaziaParaEntity() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "TEST", "");

        TipoUsuarioEntity tipoUsuarioEntity = mapper.toEntity(tipoUsuarioDomain);

        assertEquals("", tipoUsuarioEntity.getObservacao());
    }

    @Test
    void testConverterTipoUsuarioDomainComNomeVazioParaEntity() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "", "Tipo com nome vazio");

        TipoUsuarioEntity tipoUsuarioEntity = mapper.toEntity(tipoUsuarioDomain);

        assertEquals("", tipoUsuarioEntity.getNome());
    }

    @Test
    void testConverterTipoUsuarioEntityComObservacaoNulaParaDomain() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, "ADMIN", null);

        TipoUsuario tipoUsuarioDomain = mapper.toDomain(tipoUsuarioEntity);

        assertNotNull(tipoUsuarioDomain);
        assertNull(tipoUsuarioDomain.getObservacao());
    }

    @Test
    void testConverterTipoUsuarioEntityComNomeNuloParaDomain() {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity(1L, null, "Tipo com nome nulo");

        TipoUsuario tipoUsuarioDomain = mapper.toDomain(tipoUsuarioEntity);

        assertNotNull(tipoUsuarioDomain);
        assertNull(tipoUsuarioDomain.getNome());
    }

    @Test
    void testConverterTipoUsuarioDomainComObservacaoNulaParaEntity() {
        TipoUsuario tipoUsuarioDomain = new TipoUsuario(1L, "ADMIN", null);

        TipoUsuarioEntity tipoUsuarioEntity = mapper.toEntity(tipoUsuarioDomain);

        assertNull(tipoUsuarioEntity.getObservacao());
    }
}
