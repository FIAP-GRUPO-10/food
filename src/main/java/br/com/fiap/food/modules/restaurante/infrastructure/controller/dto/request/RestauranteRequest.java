package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

public record RestauranteRequest(

        @NotBlank
        @Schema(description = "Nome do restaurante", example = "Pizzaria Central")
        String nome,

        @NotBlank
        @Schema(description = "Endereço do restaurante", example = "Rua A, 123")
        String endereco,

        @NotBlank
        @Schema(description = "Tipo de cozinha", example = "Italiana")
        String tipoCozinha,

        @NotNull
        @Schema(description = "Horário de abertura", example = "10:00:00")
        LocalTime horarioAbertura,

        @NotNull
        @Schema(description = "Horário de fechamento", example = "22:00:00")
        LocalTime horarioFechamento,

        @NotNull
        @Schema(description = "ID do dono do restaurante", example = "1")
        Long donoId
) {}
