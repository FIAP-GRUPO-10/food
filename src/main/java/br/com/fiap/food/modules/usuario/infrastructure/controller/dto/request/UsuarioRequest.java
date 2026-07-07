package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioRequest(
        @Schema(description = "Nome do usuário", example = "João Silva")
        String nome,

        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email,

        @Schema(description = "ID do tipo de usuário", example = "1")
        Long tipoUsuario
) {}
