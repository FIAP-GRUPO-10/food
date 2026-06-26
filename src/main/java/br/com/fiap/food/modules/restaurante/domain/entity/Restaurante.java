package br.com.fiap.food.modules.restaurante.domain.entity;

import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import java.time.LocalTime;

public class Restaurante {

    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private LocalTime horarioFuncionamento;
    private Usuario dono;
}

