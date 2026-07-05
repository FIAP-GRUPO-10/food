package br.com.fiap.food.modules.restaurante.domain.exception;

public class HorarioInvalidoException extends RuntimeException {
    public HorarioInvalidoException(String message) {
        super(message);
    }
}
