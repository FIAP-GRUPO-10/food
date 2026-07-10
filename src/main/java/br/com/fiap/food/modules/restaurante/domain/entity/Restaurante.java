package br.com.fiap.food.modules.restaurante.domain.entity;

import br.com.fiap.food.modules.restaurante.domain.exception.HorarioInvalidoException;
import br.com.fiap.food.modules.restaurante.domain.exception.RestauranteSemDonoException;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class Restaurante {

    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Usuario dono;

    public Restaurante(Long id, String nome, String endereco, String tipoCozinha, LocalTime horarioAbertura, LocalTime horarioFechamento, Usuario dono) {

        if (!horarioAbertura.isBefore(horarioFechamento)) {
            throw new HorarioInvalidoException("Horário de abertura deve ser anterior ao horário de fechamento");
        }

        if (dono == null) {
            throw new RestauranteSemDonoException();
        }

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.dono = dono;
    }

    private Restaurante(Long id) {
        this.id = id;
    }

    public static Restaurante referenciaPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "O id do restaurante é obrigatório"
            );
        }

        return new Restaurante(id);
    }
}

