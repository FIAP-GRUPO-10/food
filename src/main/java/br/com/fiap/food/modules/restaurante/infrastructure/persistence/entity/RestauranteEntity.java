package br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity;

import br.com.fiap.food.infrastructure.persistence.entities.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "restaurante", schema = "fiap_food")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private LocalTime horarioFuncionamento;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Usuario dono;
}
