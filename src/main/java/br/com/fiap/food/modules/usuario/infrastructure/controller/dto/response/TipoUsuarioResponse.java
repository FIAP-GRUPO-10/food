package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

public record TipoUsuarioResponse(
        Long id,
        String nome,
        String observacao
) {}
