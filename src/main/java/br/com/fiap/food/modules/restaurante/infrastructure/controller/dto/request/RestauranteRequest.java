package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestauranteRequest(

        @Schema(description = "Nome do restaurante", example = "Pizzaria Napoli")
        @NotBlank
        String nome,

        @Schema(description = "Endereço do restaurante", example = "Rua das Flores, 100")
        @NotBlank
        String endereco,

        @Schema(description = "Tipo de cozinha", example = "Italiana")
        @NotBlank
        String tipoCozinha,

        @Schema(description = "Horário de abertura", example = "18:00")
        @NotNull
        LocalTime horarioAbertura,

        @Schema(description = "Horário de fechamento", example = "23:30")
        @NotNull
        LocalTime horarioFechamento,

        @Schema(description = "ID do proprietário", example = "1")
        @NotNull
        Long donoId
) {}
