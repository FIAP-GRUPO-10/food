package br.com.fiap.food.modules.usuario.infrastructure.controller;

import br.com.fiap.food.modules.usuario.application.usecase.tipousuario.*;
import br.com.fiap.food.modules.usuario.domain.entity.TipoUsuario;
import br.com.fiap.food.modules.usuario.infrastructure.controller.docs.TipoUsuarioControllerDocs;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.TipoUsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.TipoUsuarioResponse;
import br.com.fiap.food.modules.usuario.infrastructure.controller.mapper.TipoUsuarioApiMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-usuario")
public class TipoUsuarioController implements TipoUsuarioControllerDocs {

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
    public ResponseEntity<TipoUsuarioResponse> criar(@Valid @RequestBody TipoUsuarioRequest request) {
        logger.info("Iniciando criação de TipoUsuario: {}", request);
        TipoUsuario tipoUsuario = mapper.toDomain(request);
        TipoUsuario criado = criarTipoUsuarioUseCase.execute(tipoUsuario);
        logger.info("TipoUsuario criado com ID: {}", criado.getId());
        TipoUsuarioResponse response = mapper.toResponse(criado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando TipoUsuario por ID: {}", id);
        TipoUsuario tipoUsuario = buscarTipoUsuarioPorIdUseCase.execute(id);
        logger.info("TipoUsuario encontrado: {}", tipoUsuario);
        TipoUsuarioResponse response = mapper.toResponse(tipoUsuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping
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
    public ResponseEntity<TipoUsuarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid TipoUsuarioRequest request) {
        logger.info("Atualizando TipoUsuario ID: {} com dados: {}", id, request);
        TipoUsuario tipoUsuario = mapper.toDomain(request);
        TipoUsuario atualizado = atualizarTipoUsuarioUseCase.execute(id, tipoUsuario);
        logger.info("TipoUsuario atualizado: {}", atualizado);
        TipoUsuarioResponse response = mapper.toResponse(atualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        logger.info("Deletando TipoUsuario ID: {}", id);
        deletarTipoUsuarioUseCase.execute(id);
        logger.info("TipoUsuario ID: {} deletado com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
