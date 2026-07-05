package br.com.fiap.food.modules.restaurante.domain.exception;

public class RestauranteNaoEncontradoException extends RuntimeException {

    public RestauranteNaoEncontradoException(Long id) {

        super("Restaurante com id " + id + " não encontrado");
    }
}
