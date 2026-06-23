package br.com.fiap.food.infrastructure.presentation.request;

public record ItemCardapioRequest(
        String nome,
        String descricao,
        Double preco,
        boolean somenteNoLocal,
        String caminhoFoto,
        Long restauranteId
) {}
