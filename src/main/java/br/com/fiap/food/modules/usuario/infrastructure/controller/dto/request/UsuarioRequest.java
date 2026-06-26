package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

public record UsuarioRequest(
        String nome,
        String email,
        Long tipoUsuarioId
) {}
