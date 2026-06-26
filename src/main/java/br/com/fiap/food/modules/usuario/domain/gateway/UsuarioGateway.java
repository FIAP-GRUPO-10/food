package br.com.fiap.food.modules.usuario.domain.gateway;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
    void deletar(Long id);
}