package br.com.fiap.food.modules.usuario.application.config;

import br.com.fiap.food.modules.tipousuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.tipousuario.domain.gateway.TipoUsuarioGateway;
import br.com.fiap.food.modules.usuario.application.usecase.*;
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

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        return new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway gateway) {
        return new ListarUsuariosUseCase(gateway);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(UsuarioGateway gateway) {
        return new DeletarUsuarioUseCase(gateway);
    }

    @Bean
    public AtualizarTipoUsuarioDoUsuarioUseCase atualizarTipoUsuarioDoUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        return new AtualizarTipoUsuarioDoUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

}
