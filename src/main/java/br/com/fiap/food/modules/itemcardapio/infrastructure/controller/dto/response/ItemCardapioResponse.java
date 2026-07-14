package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um item do cardápio")
public record ItemCardapioResponse(

        @Schema(
                description = "Identificador único do item do cardápio",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do item do cardápio",
                example = "Hambúrguer Artesanal"
        )
        String nome,

        @Schema(
                description = "Descrição do item",
                example = "Hambúrguer de carne bovina com queijo cheddar e bacon"
        )
        String descricao,

        @Schema(
                description = "Preço do item",
                example = "39.90"
        )
        double preco,

        @Schema(
                description = "Indica se o item é exclusivo para consumo no local",
                example = "false"
        )
        boolean somenteNoLocal,

        @Schema(
                description = "URL ou caminho da foto do item",
                example = "https://meusite.com/imagens/hamburguer.jpg"
        )
        String caminhoFoto,

        @Schema(
                description = "Dados do restaurante ao qual o item pertence"
        )
        RestauranteResponse restaurante

) {}