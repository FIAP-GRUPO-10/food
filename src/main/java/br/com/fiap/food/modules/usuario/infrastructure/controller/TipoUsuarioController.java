package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.application.usecase.tipousuario.*;
import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.TipoUsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.TipoUsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.TipoUsuarioApiMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-usuario")
@Tag(name = "TipoUsuario", description = "Operações relacionadas a tipos de usuário")
public class TipoUsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(TipoUsuarioController.class);

    private final TipoUsuarioApiMapper mapper;
    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;
    private final ListarTipoUsuariosUseCase listarTipoUsuariosUseCase;
    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    private final DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    public TipoUsuarioController(
            TipoUsuarioApiMapper mapper,
            CriarTipoUsuarioUseCase criarTipoUsuarioUseCase,
            BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase,
            ListarTipoUsuariosUseCase listarTipoUsuariosUseCase,
            AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase,
            DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase) {
        this.mapper = mapper;
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.buscarTipoUsuarioPorIdUseCase = buscarTipoUsuarioPorIdUseCase;
        this.listarTipoUsuariosUseCase = listarTipoUsuariosUseCase;
        this.atualizarTipoUsuarioUseCase = atualizarTipoUsuarioUseCase;
        this.deletarTipoUsuarioUseCase = deletarTipoUsuarioUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar tipo de usuário", description = "Cria um novo tipo de usuário")
    public ResponseEntity<TipoUsuarioResponse> criar(@Valid @RequestBody TipoUsuarioRequest request) {
        logger.info("Iniciando criação de TipoUsuario: {}", request);
        TipoUsuario tipoUsuario = mapper.toDomain(request);
        TipoUsuario criado = criarTipoUsuarioUseCase.execute(tipoUsuario);
        logger.info("TipoUsuario criado com ID: {}", criado.getId());
        TipoUsuarioResponse response = mapper.toResponse(criado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo por ID", description = "Retorna um tipo de usuário pelo seu ID")
    public ResponseEntity<TipoUsuarioResponse> buscarPorId(@Parameter(description = "ID do tipo de usuário", required = true) @PathVariable Long id) {
        logger.info("Buscando TipoUsuario por ID: {}", id);
        TipoUsuario tipoUsuario = buscarTipoUsuarioPorIdUseCase.execute(id);
        logger.info("TipoUsuario encontrado: {}", tipoUsuario);
        TipoUsuarioResponse response = mapper.toResponse(tipoUsuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar tipos de usuário", description = "Retorna todos os tipos de usuário cadastrados")
    public ResponseEntity<List<TipoUsuarioResponse>> listar() {
        logger.info("Listando todos os TipoUsuarios");
        List<TipoUsuario> tipoUsuarios = listarTipoUsuariosUseCase.execute();
        logger.info("Total de TipoUsuarios encontrados: {}", tipoUsuarios.size());
        List<TipoUsuarioResponse> responses = tipoUsuarios.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo de usuário", description = "Atualiza um tipo de usuário existente")
    public ResponseEntity<TipoUsuarioResponse> atualizar(@Parameter(description = "ID do tipo de usuário", required = true) @PathVariable Long id, @RequestBody TipoUsuarioRequest request) {
        logger.info("Atualizando TipoUsuario ID: {} com dados: {}", id, request);
        TipoUsuario tipoUsuario = mapper.toDomain(request);
        TipoUsuario atualizado = atualizarTipoUsuarioUseCase.execute(id, tipoUsuario);
        logger.info("TipoUsuario atualizado: {}", atualizado);
        TipoUsuarioResponse response = mapper.toResponse(atualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tipo de usuário", description = "Remove um tipo de usuário pelo ID")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do tipo de usuário", required = true) @PathVariable Long id) {
        logger.info("Deletando TipoUsuario ID: {}", id);
        deletarTipoUsuarioUseCase.execute(id);
        logger.info("TipoUsuario ID: {} deletado com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
