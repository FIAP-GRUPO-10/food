package br.com.fiap.food.modules.usuario.application.config;

import br.com.fiap.food.modules.usuario.application.usecase.tipousuario.*;
import br.com.fiap.food.modules.usuario.domain.gateway.TipoUsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TIpoUsuarioConfig {

    @Bean
    public CriarTipoUsuarioUseCase criarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new CriarTipoUsuarioUseCase(gateway);
    }

    @Bean
    public AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new AtualizarTipoUsuarioUseCase(gateway);
    }

    @Bean
    public BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway gateway) {
        return new BuscarTipoUsuarioPorIdUseCase(gateway);
    }

    @Bean
    public ListarTipoUsuariosUseCase listarTipoUsuariosUseCase(TipoUsuarioGateway gateway) {
        return new ListarTipoUsuariosUseCase(gateway);
    }

    @Bean
    public DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new DeletarTipoUsuarioUseCase(gateway);
    }
}
