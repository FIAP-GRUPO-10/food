package br.com.fiap.food.infrastructure.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record TipoUsuarioRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        String observacao
) {}

