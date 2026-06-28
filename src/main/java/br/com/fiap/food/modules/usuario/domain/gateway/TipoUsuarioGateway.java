package br.com.fiap.food.modules.usuario.domain.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);
    Optional<TipoUsuario> buscarPorId(Long id);
    List<TipoUsuario> listarTodos();
    TipoUsuario atualizar(Long id, TipoUsuario tipoUsuario);
    void deletar(Long id);
    boolean existsById(Long id);
}
