package br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository;

import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}