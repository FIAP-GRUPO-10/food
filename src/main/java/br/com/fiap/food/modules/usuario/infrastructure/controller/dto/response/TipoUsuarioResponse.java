package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record TipoUsuarioResponse(
        @Schema(description = "Identificador único do tipo de usuário", example = "1")
        Long id,

        @Schema(description = "Nome do tipo/perfil de usuário", example = "CLIENTE")
        String nome,

        @Schema(description = "Observações ou descrição do tipo de usuário", example = "Usuário padrão que realiza pedidos na plataforma")
        String observacao
) {}