package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request;

public record ItemCardapioRequest(
        String nome,
        String descricao,
        double preco,
        boolean somenteNoLocal,
        String caminhoFoto,
        Long restauranteId
) {}
