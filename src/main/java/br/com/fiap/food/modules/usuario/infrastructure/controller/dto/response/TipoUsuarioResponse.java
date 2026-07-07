package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record TipoUsuarioResponse(
        @Schema(description = "ID do tipo de usuário", example = "1")
        Long id,

        @Schema(description = "Nome do tipo de usuário", example = "ADMIN")
        String nome,

        @Schema(description = "Observação ou descrição do tipo")
        String observacao
) {}
