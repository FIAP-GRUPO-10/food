package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        double preco,
        boolean somenteNoLocal,
        String caminhoFoto,
        RestauranteResponse restaurante
) {}