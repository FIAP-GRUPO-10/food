package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ItemCardapioRequest(
        @Schema(description = "Nome do item", example = "Pizza Margherita")
        String nome,

        @Schema(description = "Descrição do item")
        String descricao,

        @Schema(description = "Preço do item", example = "29.90")
        double preco,

        @Schema(description = "Indica se o item é servido somente no local")
        boolean somenteNoLocal,

        @Schema(description = "Caminho/URL da foto do item")
        String caminhoFoto,

        @Schema(description = "ID do restaurante ao qual o item pertence", example = "1")
        Long restauranteId
) {}
