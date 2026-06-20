package br.com.fiap.food.infrastructure.presentation.response;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        TipoUsuarioResponse tipoUsuario
) {}
