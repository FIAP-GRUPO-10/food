package br.com.fiap.food.modules.itemcardapio.domain.exception;

public class ItemCardapioSemRestauranteException extends RuntimeException {

    public ItemCardapioSemRestauranteException() {
        super("Item do cardápio deve estar associado a um restaurante");
    }
}