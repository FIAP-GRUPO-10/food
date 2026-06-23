package br.com.fiap.food.infrastructure.presentation.request;

import java.time.LocalDateTime;

public record RestauranteRequest(
        String nome,
        String endereco,
        String tipoCozinha,
        LocalDateTime horarioFuncionamento,
        Long donoId
) {}
