package br.com.fiap.food.infrastructure.persistence.repositories;

import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
