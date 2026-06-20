package br.com.fiap.food.infrastructure.persistence.repositories;

import br.com.fiap.food.infrastructure.persistence.entities.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    Optional<TipoUsuario> findByNome(String nome);
    boolean existsByNome(String nome);
}
