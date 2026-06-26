package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request;

import java.time.LocalDateTime;

public record RestauranteRequest(
        String nome,
        String endereco,
        String tipoCozinha,
        LocalDateTime horarioFuncionamento,
        Long donoId
) {}
