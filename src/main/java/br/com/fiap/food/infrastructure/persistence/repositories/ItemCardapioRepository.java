package br.com.fiap.food.infrastructure.persistence.repositories;

import br.com.fiap.food.infrastructure.persistence.entities.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
}
