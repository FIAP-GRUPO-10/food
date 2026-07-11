package br.com.fiap.food.modules.tipousuario.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TipoUsuarioRequest(
        @Schema(description = "Nome do tipo/perfil de usuário", example = "CLIENTE")
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,

        @Schema(description = "Observações ou descrição das permissões do tipo de usuário", example = "Usuário padrão que realiza pedidos na plataforma")
        String observacao
) {}