package br.com.fiap.food.modules.usuario.domain.entity;

import br.com.fiap.food.modules.usuario.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
}
