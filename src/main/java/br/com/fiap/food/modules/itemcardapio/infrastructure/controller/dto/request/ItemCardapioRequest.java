package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemCardapioRequest(

        @Schema(
                description = "Nome do item do cardápio",
                example = "Hambúrguer Artesanal"
        )
        @NotBlank(message = "Nome do item é obrigatório")
        String nome,

        @Schema(
                description = "Descrição do item",
                example = "Hambúrguer de carne bovina com queijo cheddar e bacon"
        )
        @NotBlank(message = "Descrição do item é obrigatória")
        String descricao,

        @Schema(
                description = "Preço do item",
                example = "39.90"
        )
        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser maior que zero")
        Double preco,

        @Schema(
                description = "Indica se o item é exclusivo para consumo no local",
                example = "false"
        )
        @NotNull(message = "Informe se o item é somente para consumo no local")
        Boolean somenteNoLocal,

        @Schema(
                description = "URL ou caminho da foto do item",
                example = "https://meusite.com/imagens/hamburguer.jpg"
        )
        @NotBlank(message = "Caminho da foto é obrigatório")
        String caminhoFoto,

        @Schema(
                description = "ID do restaurante ao qual o item pertence",
                example = "1"
        )
        @NotNull(message = "Restaurante é obrigatório")
        Long restauranteId

) {}