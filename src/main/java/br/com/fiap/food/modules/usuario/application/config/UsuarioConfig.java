package br.com.fiap.food.modules.usuario.application.config;

import br.com.fiap.food.modules.usuario.application.usecase.BuscarUsuarioPorIdUseCase;
import br.com.fiap.food.modules.usuario.application.usecase.CriarUsuarioUseCase;
import br.com.fiap.food.modules.usuario.domain.gateway.UsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(UsuarioGateway gateway) {
        return new CriarUsuarioUseCase(gateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(UsuarioGateway gateway) {
        return new BuscarUsuarioPorIdUseCase(gateway);
    }
}
