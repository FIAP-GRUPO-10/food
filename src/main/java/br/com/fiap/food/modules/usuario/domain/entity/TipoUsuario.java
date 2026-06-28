package br.com.fiap.food.modules.usuario.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TipoUsuario {
    private Long id;
    private String nome;
    private String observacao;

    public void setId(Long id) {
        this.id = id;
    }
}
