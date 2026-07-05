package br.com.fiap.food.modules.restaurante.domain.exception;

public class RestauranteSemDonoException extends RuntimeException {
    public RestauranteSemDonoException() {
        super("O Restaurante deve possuir um dono");
    }
}
