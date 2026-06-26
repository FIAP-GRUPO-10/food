package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.UsuarioApiMapper;
import br.com.fiap.food.modules.usuario.application.usecase.BuscarUsuarioPorIdUseCase;
import br.com.fiap.food.modules.usuario.application.usecase.CriarUsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {


    private final UsuarioApiMapper mapper;
    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public UsuarioController(UsuarioApiMapper mapper,
                             CriarUsuarioUseCase criarUsuarioUseCase,
                             BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.mapper = mapper;
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        UsuarioResponse response = mapper.toResponse(usuario);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioRequest request) {
        Usuario usuario = mapper.toDomain(request);
        Usuario criado = criarUsuarioUseCase.execute(usuario);
        UsuarioResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
