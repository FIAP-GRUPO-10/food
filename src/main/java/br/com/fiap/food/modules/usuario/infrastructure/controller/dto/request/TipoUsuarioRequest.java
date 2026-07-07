package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public record TipoUsuarioRequest (
        @NotBlank(message = "O nome não pode estar vazio")
        @Schema(description = "Nome do tipo de usuário", example = "ADMIN")
        String nome,

        @Schema(description = "Observação ou descrição do tipo")
        String observacao
){}
