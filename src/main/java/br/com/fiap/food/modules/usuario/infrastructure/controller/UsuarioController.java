package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.application.usecase.usuario.*;
import br.com.fiap.food.modules.usuario.domain.entity.Usuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.UsuarioApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

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
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        logger.info("Iniciando criação de usuário: {}", request);
        Usuario usuario = mapper.toDomain(request);
        Usuario criado = criarUsuarioUseCase.execute(usuario);
        logger.info("Usuário criado com ID: {}", criado.getId());
        UsuarioResponse response = mapper.toResponse(criado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário pelo seu ID")
    public ResponseEntity<UsuarioResponse> buscarPorId(@Parameter(description = "ID do usuário", required = true) @PathVariable Long id) {
        logger.info("Buscando usuário por ID: {}", id);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        logger.info("Usuário encontrado: {}", usuario);
        UsuarioResponse response = mapper.toResponse(usuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna a lista de todos os usuários")
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        logger.info("Listando todos os usuários");
        List<Usuario> usuarios = listarUsuariosUseCase.execute();
        logger.info("Total de usuários encontrados: {}", usuarios.size());
        List<UsuarioResponse> responses = usuarios.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@Parameter(description = "ID do usuário", required = true) @PathVariable Long id,
                                                            @Valid @RequestBody UsuarioRequest request) {
        logger.info("Atualizando usuário ID: {} com dados: {}", id, request);
        Usuario usuario = mapper.toDomain(request);
        Usuario atualizado = atualizarUsuarioUseCase.execute(id, usuario);
        logger.info("Usuário atualizado: {}", atualizado);
        UsuarioResponse response = mapper.toResponse(atualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo ID")
    public ResponseEntity<Void> deletarUsuario(@Parameter(description = "ID do usuário", required = true) @PathVariable Long id) {
        logger.info("Deletando usuário ID: {}", id);
        deletarUsuarioUseCase.execute(id);
        logger.info("Usuário ID: {} deletado com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
