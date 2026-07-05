package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestauranteRequest(

        @NotBlank
        String nome,

        @NotBlank
        String endereco,

        @NotBlank
        String tipoCozinha,

        @NotNull
        LocalTime horarioAbertura,

        @NotNull
        LocalTime horarioFechamento,

        @NotNull
        Long donoId
) {}
