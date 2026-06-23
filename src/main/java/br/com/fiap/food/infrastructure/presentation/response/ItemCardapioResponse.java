package br.com.fiap.food.infrastructure.presentation.response;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
        boolean somenteNoLocal,
        String caminhoFoto,
        RestauranteResponse restaurante
) {}