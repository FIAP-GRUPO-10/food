package br.com.fiap.food.modules.restaurante.domain.exception;

public class RestauranteNaoEncontradoException extends RuntimeException {

    public RestauranteNaoEncontradoException(String msg) {
        super(msg);
    }
}
