package br.com.fiap.food.modules.itemcardapio.application.config;

import br.com.fiap.food.modules.itemcardapio.application.usecase.AtualizarItemUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.DeletarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.domain.gateway.ItemCardapioGateway;
import br.com.fiap.food.modules.restaurante.domain.gateway.RestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {

    @Bean
    public CriarItemCardapioUseCase criarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new CriarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(ItemCardapioGateway gateway) {
        return new BuscarItemCardapioPorIdUseCase(gateway);
    }

    @Bean
    public AtualizarItemUseCase atualizarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new AtualizarItemUseCase(
                itemCardapioGateway,
                restauranteGateway
        );
    }

    @Bean
    public DeletarItemCardapioUseCase deletarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway
    ) {
        return new DeletarItemCardapioUseCase(
                itemCardapioGateway
        );
    }
}
