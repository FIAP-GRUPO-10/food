package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;

import java.time.LocalTime;

public record RestauranteResponse(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        LocalTime horarioFuncionamento,
        UsuarioResponse dono
) {}