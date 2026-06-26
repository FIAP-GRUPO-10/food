package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.usuario.domain.enums.TipoUsuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        TipoUsuario tipoUsuario
) {}