package br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(description = "Dados de um restaurante")
public record RestauranteResponse(

        @Schema(description = "Identificador único do restaurante", example = "1")
        Long id,

        @Schema(description = "Nome do restaurante", example = "Pizzaria Napoli")
        String nome,

        @Schema(description = "Endereço do restaurante", example = "Rua das Flores, 100 - São Paulo/SP")
        String endereco,

        @Schema(description = "Tipo de cozinha do restaurante", example = "Italiana")
        String tipoCozinha,

        @Schema(description = "Horário de abertura", example = "18:00")
        LocalTime horarioAbertura,

        @Schema(description = "Horário de fechamento", example = "23:30")
        LocalTime horarioFechamento,

        @Schema(description = "Proprietário do restaurante")
        UsuarioResponse dono

) {}