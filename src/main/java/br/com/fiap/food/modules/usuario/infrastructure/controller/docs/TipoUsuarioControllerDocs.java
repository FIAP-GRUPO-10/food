package br.com.fiap.food.modules.usuario.infrastructure.controller.docs;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.TipoUsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.TipoUsuarioResponse;
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

@Tag(name = "Tipos de Usuário", description = "Endpoints responsáveis pelo gerenciamento de tipos/perfis de usuários")
public interface TipoUsuarioControllerDocs {

    @Operation(summary = "Cadastrar tipo de usuário", description = "Cria um novo tipo/perfil de usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tipo de usuário criado com sucesso", content = @Content(schema = @Schema(implementation = TipoUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou requisição malformada")
    })
    ResponseEntity<TipoUsuarioResponse> criar(
            @Valid TipoUsuarioRequest request
    );

    @Operation(summary = "Buscar tipo de usuário por ID", description = "Retorna os dados de um tipo de usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado", content = @Content(schema = @Schema(implementation = TipoUsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    ResponseEntity<TipoUsuarioResponse> buscarPorId(
            @Parameter(description = "ID do tipo de usuário", example = "1") Long id
    );

    @Operation(summary = "Listar tipos de usuário", description = "Retorna todos os tipos de usuários cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = @Content(schema = @Schema(implementation = TipoUsuarioResponse.class)))
    })
    ResponseEntity<List<TipoUsuarioResponse>> listar();

    @Operation(summary = "Atualizar tipo de usuário", description = "Atualiza os dados de um tipo de usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = TipoUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    ResponseEntity<TipoUsuarioResponse> atualizar(
            @Parameter(description = "ID do tipo de usuário", example = "1") Long id,
            @Valid TipoUsuarioRequest request
    );

    @Operation(summary = "Excluir tipo de usuário", description = "Remove um tipo de usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do tipo de usuário", example = "1") Long id
    );
}