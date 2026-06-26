package br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity;

import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item_cardapio")
public class ItemCardapioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private boolean somenteNoLocal;
    private String caminhoFoto;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restaurante;
}
