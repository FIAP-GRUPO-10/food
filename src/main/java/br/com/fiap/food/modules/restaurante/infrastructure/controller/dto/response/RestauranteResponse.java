package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response;

import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;

import java.time.LocalDateTime;

public record RestauranteResponse(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        LocalDateTime horarioFuncionamento,
        UsuarioResponse dono
) {}