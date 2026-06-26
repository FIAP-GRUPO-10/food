package br.com.fiap.food.modules.usuario.infrastructure.persistence.repository;

import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}