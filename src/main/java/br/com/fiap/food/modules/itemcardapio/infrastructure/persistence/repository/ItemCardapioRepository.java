package br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.repository;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {

    Optional<ItemCardapioEntity> findByNome(String nome);
    boolean existsByNome(String nome);
    List<ItemCardapioEntity> findByRestauranteId(Long restauranteId);
}