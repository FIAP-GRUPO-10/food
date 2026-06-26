package br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request;

import br.com.fiap.food.modules.usuario.domain.enums.TipoUsuario;

public record UsuarioRequest(
        String nome,
        String email,
        TipoUsuario tipoUsuario
) {}
