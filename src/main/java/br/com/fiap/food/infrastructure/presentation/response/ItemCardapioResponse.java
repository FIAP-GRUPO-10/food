package br.com.fiap.food.infrastructure.presentation.response;

import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
        boolean somenteNoLocal,
        String caminhoFoto,
        RestauranteResponse restaurante
) {}