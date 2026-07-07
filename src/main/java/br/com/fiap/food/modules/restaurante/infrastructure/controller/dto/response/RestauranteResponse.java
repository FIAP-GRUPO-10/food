package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

public record RestauranteResponse(
        @Schema(description = "ID do restaurante", example = "1")
        Long id,

        @Schema(description = "Nome do restaurante", example = "Pizzaria Central")
        String nome,

        @Schema(description = "Endereço do restaurante", example = "Rua A, 123")
        String endereco,

        @Schema(description = "Tipo de cozinha", example = "Italiana")
        String tipoCozinha,

        @Schema(description = "Horário de abertura", example = "10:00:00")
        LocalTime horarioAbertura,

        @Schema(description = "Horário de fechamento", example = "22:00:00")
        LocalTime horarioFechamento,

        @Schema(description = "Dados do dono do restaurante")
        UsuarioResponse dono
) {}