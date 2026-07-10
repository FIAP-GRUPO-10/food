package br.com.fiap.food.modules.usuario.infrastructure.controller.docs;

import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.request.UsuarioRequest;
import br.com.fiap.food.modules.usuario.infrastructure.controller.dto.response.UsuarioResponse;
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

@Tag(name = "Usuários", description = "Endpoints responsáveis pelo gerenciamento de usuários")
public interface UsuarioControllerDocs {

    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou requisição malformada")
    })
    ResponseEntity<UsuarioResponse> criarUsuario(
            @Valid UsuarioRequest request
    );

    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<UsuarioResponse> buscarPorId(
            @Parameter(description = "ID do usuário", example = "1") Long id
    );

    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class)))
    })
    ResponseEntity<List<UsuarioResponse>> listarTodos();

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<UsuarioResponse> atualizarUsuario(
            @Parameter(description = "ID do usuário", example = "1") Long id,
            @Valid UsuarioRequest request
    );

    @Operation(summary = "Excluir usuário", description = "Remove um usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário", example = "1") Long id
    );
}