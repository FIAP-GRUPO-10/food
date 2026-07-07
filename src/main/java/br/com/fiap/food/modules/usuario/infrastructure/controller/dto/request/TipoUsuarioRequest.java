package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TipoUsuarioRequest (
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,
        String observacao
){}
