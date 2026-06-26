package br.com.fiap.food.modules.restaurante.domain.entity;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class Restaurante {

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private LocalTime horarioFuncionamento;
    private Usuario dono;

}

