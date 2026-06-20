package br.com.fiap.food.infrastructure.presentation.request;

public record UsuarioRequest(
        String nome,
        String email,
        Long tipoUsuarioId
) {}
