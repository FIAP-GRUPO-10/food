package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponse(
        @Schema(description = "Identificador único do usuário", example = "1")
        Long id,

        @Schema(description = "Nome completo do usuário", example = "João da Silva")
        String nome,

        @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@email.com")
        String email,

        @Schema(description = "Dados detalhados do tipo/perfil do usuário")
        TipoUsuario tipoUsuario
) {}