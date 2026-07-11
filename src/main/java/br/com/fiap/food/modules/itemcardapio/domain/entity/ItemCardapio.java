package br.com.fiap.food.modules.itemcardapio.domain.entity;

import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioInvalidoException;
import br.com.fiap.food.modules.itemcardapio.domain.exception.ItemCardapioSemRestauranteException;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private boolean somenteNoLocal;
    private String caminhoFoto;
    private Restaurante restaurante;

    public ItemCardapio(
            Long id,
            String nome,
            String descricao,
            double preco,
            boolean somenteNoLocal,
            String caminhoFoto,
            Restaurante restaurante
    ) {
        if (nome == null || nome.isBlank()) {
            throw new ItemCardapioInvalidoException("Nome do item é obrigatório");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new ItemCardapioInvalidoException("Descrição do item é obrigatória");
        }

        if (preco <= 0) {
            throw new ItemCardapioInvalidoException("Preço deve ser maior que zero");
        }

        if (caminhoFoto == null || caminhoFoto.isBlank()) {
            throw new ItemCardapioInvalidoException("Caminho da foto é obrigatório");
        }

        if (restaurante == null) {
            throw new ItemCardapioSemRestauranteException();
        }

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.somenteNoLocal = somenteNoLocal;
        this.caminhoFoto = caminhoFoto;
        this.restaurante = restaurante;
    }
}