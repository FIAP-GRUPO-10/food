package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request;

import java.time.LocalTime;

public record RestauranteRequest(
        String nome,
        String endereco,
        String tipoCozinha,
        LocalTime horarioFuncionamento,
        Long donoId
) {}
