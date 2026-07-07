package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponse(
        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "João Silva")
        String nome,

        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email,

        @Schema(description = "Tipo do usuário")
        TipoUsuario tipoUsuario
) {}