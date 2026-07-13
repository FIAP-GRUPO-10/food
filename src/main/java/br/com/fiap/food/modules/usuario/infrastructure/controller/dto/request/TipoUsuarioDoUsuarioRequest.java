package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record TipoUsuarioDoUsuarioRequest(@NotNull Long tipoUsuarioId) {
}
