package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(

        @Schema(description = "Nome completo do usuário", example = "João da Silva")
        @NotBlank
        String nome,

        @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@email.com")
        @NotBlank
        String email,

        @Schema(description = "ID do tipo/perfil de usuário", example = "1")
        @NotNull
        Long tipoUsuario
) {}