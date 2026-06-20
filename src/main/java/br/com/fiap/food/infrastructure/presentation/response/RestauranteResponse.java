package br.com.fiap.food.infrastructure.presentation.response;

import java.time.LocalDateTime;

public record RestauranteResponse(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        LocalDateTime horarioFuncionamento,
        UsuarioResponse dono
) {}