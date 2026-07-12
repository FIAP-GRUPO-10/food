package br.com.fiap.food.modules.tipousuario.infrastructure.persistence.repository;

import br.com.fiap.food.modules.tipousuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    Optional<TipoUsuarioEntity> findByNome(String nome);
}
