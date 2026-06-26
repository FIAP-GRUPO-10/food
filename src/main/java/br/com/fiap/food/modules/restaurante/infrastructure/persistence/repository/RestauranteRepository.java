package br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    Optional<Restaurante> findByNome(String nome);
    List<Restaurante> findByTipoCozinhaContainingIgnoreCase(String tipoCozinha);
    List<Restaurante> findByEnderecoContainingIgnoreCase(String endereco);
    boolean existsByNome(String nome);
}