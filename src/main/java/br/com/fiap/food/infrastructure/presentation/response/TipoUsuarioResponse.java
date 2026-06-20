package br.com.fiap.food.infrastructure.presentation.response;

public record TipoUsuarioResponse(
        Long id,
        String nome,
        String observacao
) {}