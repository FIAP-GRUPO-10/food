package br.com.fiap.food.modules.restaurante.infrastructure.controller;

import br.com.fiap.food.modules.restaurante.application.usecase.*;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper.RestauranteApiMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/restaurante")
@Tag(name = "Restaurante", description = "Operações relacionadas a restaurantes")
public class RestauranteController {

    private final RestauranteApiMapper mapper;
    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;
    private final ListarRestaurantesUseCase listarRestaurantesUseCase;

    public RestauranteController(RestauranteApiMapper mapper,
                                 CriarRestauranteUseCase criarRestauranteUseCase,
                                 BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase, AtualizarRestauranteUseCase atualizarRestauranteUseCase, DeletarRestauranteUseCase deletarRestauranteUseCase, ListarRestaurantesUseCase listarRestaurantesUseCase) {
        this.mapper = mapper;
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.deletarRestauranteUseCase = deletarRestauranteUseCase;
        this.listarRestaurantesUseCase = listarRestaurantesUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Retorna os dados de um restaurante pelo ID")
    public ResponseEntity<RestauranteResponse> buscarPorId(@Parameter(description = "ID do restaurante", required = true) @PathVariable Long id) {
        Restaurante restaurante = buscarRestaurantePorIdUseCase.execute(id);
        RestauranteResponse response = mapper.toResponse(restaurante);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar restaurantes", description = "Retorna a lista de restaurantes")
    public ResponseEntity<List<RestauranteResponse>> buscarTodos() {
        List<Restaurante> restaurantes = listarRestaurantesUseCase.execute();
        List<RestauranteResponse> responses = restaurantes.stream().map(mapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    @Operation(summary = "Criar restaurante", description = "Cria um novo restaurante")
    public ResponseEntity<RestauranteResponse> criarRestaurante(@RequestBody @Valid RestauranteRequest request) {
        Restaurante restaurante = mapper.toDomain(request);
        Restaurante criado = criarRestauranteUseCase.execute(restaurante);
        RestauranteResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante", description = "Atualiza os dados de um restaurante existente")
    public ResponseEntity<RestauranteResponse> atualizarRestaurante(@Parameter(description = "ID do restaurante", required = true) @PathVariable Long id, @RequestBody @Valid RestauranteRequest request) {
        Restaurante domain = mapper.toDomain(request);
        Restaurante restaurante = atualizarRestauranteUseCase.execute(id, domain);
        RestauranteResponse response = mapper.toResponse(restaurante);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar restaurante", description = "Remove um restaurante pelo ID")
    public ResponseEntity<Void> deletarRestaurante(@Parameter(description = "ID do restaurante", required = true) @PathVariable Long id) {
        deletarRestauranteUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
