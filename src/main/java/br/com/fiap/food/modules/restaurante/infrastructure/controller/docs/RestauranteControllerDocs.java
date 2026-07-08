package br.com.fiap.food.modules.restaurante.infrastructure.controller.docs;

import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Restaurantes", description = "Endpoints responsáveis pelo gerenciamento de restaurantes")
public interface RestauranteControllerDocs {

    @Operation(summary = "Buscar restaurante por ID", description = "Retorna os dados de um restaurante pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado", content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    ResponseEntity<RestauranteResponse> buscarPorId(
            @Parameter(description = "ID do restaurante", example = "1") Long id
    );

    @Operation(summary = "Listar restaurantes", description = "Retorna todos os restaurantes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponse.class)))
    })
    ResponseEntity<List<RestauranteResponse>> buscarTodos();

    @Operation(summary = "Cadastrar restaurante", description = "Cria um novo restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário dono não encontrado")
    })
    ResponseEntity<RestauranteResponse> criarRestaurante(
            @Valid RestauranteRequest request
    );

    @Operation(summary = "Atualizar restaurante", description = "Atualiza os dados de um restaurante existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado"),
            @ApiResponse(responseCode = "404", description = "Novo dono não encontrado")
    })
    ResponseEntity<RestauranteResponse> atualizarRestaurante(
            @Parameter(description = "ID do restaurante", example = "1") Long id,
            @Valid RestauranteRequest request
    );

    @Operation(summary = "Excluir restaurante", description = "Remove um restaurante pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    ResponseEntity<Void> deletarRestaurante(
            @Parameter(description = "ID do restaurante", example = "1") Long id
    );
}