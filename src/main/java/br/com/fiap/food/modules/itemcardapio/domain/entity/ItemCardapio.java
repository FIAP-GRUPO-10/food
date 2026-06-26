package br.com.fiap.food.modules.itemcardapio.domain.entity;

import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import lombok.*;

@Getter
@AllArgsConstructor
public class ItemCardapio {

    private String nome;
    private String descricao;
    private double preco;
    private boolean somenteNoLocal;
    private String caminhoFoto;
    private Restaurante restaurante;
}