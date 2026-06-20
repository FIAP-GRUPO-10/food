package br.com.fiap.food.infrastructure.persistence.repositories;

import br.com.fiap.food.infrastructure.persistence.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    // READ - Buscar por nome
    Optional<Restaurante> findByNome(String nome);

    // READ - Buscar todos por tipo de cozinha
    List<Restaurante> findByTipoCozinhaContainingIgnoreCase(String tipoCozinha);

    // READ - Buscar todos por endereço aproximado
    List<Restaurante> findByEnderecoContainingIgnoreCase(String endereco);

    // Verificar se já existe restaurante com nome
    boolean existsByNome(String nome);
}