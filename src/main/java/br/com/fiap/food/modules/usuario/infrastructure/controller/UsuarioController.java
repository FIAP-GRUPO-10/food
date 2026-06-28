package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.application.usecase.usuario.*;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.UsuarioApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioApiMapper mapper;
    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public UsuarioController(UsuarioApiMapper mapper,
                             CriarUsuarioUseCase criarUsuarioUseCase,
                             BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                             ListarUsuariosUseCase listarUsuariosUseCase,
                             AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.mapper = mapper;
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }


    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioRequest request) {
        Usuario usuario = mapper.toDomain(request);
        Usuario criado = criarUsuarioUseCase.execute(usuario);
        UsuarioResponse response = mapper.toResponse(criado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        UsuarioResponse response = mapper.toResponse(usuario);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        List<Usuario> usuarios = listarUsuariosUseCase.execute();
        List<UsuarioResponse> responses = usuarios.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long id,
                                                            @RequestBody UsuarioRequest request) {
        Usuario usuario = mapper.toDomain(request);
        Usuario atualizado = atualizarUsuarioUseCase.execute(id, usuario);
        UsuarioResponse response = mapper.toResponse(atualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        deletarUsuarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
