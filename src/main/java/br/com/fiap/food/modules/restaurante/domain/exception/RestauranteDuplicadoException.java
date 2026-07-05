package br.com.fiap.food.modules.restaurante.domain.exception;

public class RestauranteDuplicadoException extends RuntimeException {
    public RestauranteDuplicadoException(String nome, String endereco) {
        super("Restaurante: " + nome + " já cadastrado no endereço: " + endereco);
    }
}
