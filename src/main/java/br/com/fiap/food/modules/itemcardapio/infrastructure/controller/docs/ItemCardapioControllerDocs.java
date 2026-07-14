package br.com.fiap.food.modules.itemcardapio.infrastructure.controller.docs;

import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@Tag(name = "Itens do Cardápio", description = "Endpoints responsáveis pelo gerenciamento dos itens do cardápio")
public interface ItemCardapioControllerDocs {

    @Operation(
            summary = "Buscar item do cardápio por ID",
            description = "Retorna os dados de um item do cardápio pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Item do cardápio encontrado",
                    content = @Content(schema = @Schema(implementation = ItemCardapioResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item do cardápio não encontrado"
            )
    })
    ResponseEntity<ItemCardapioResponse> buscarPorId(
            @Parameter(description = "ID do item do cardápio", example = "1")
            Long id
    );

    @Operation(
            summary = "Cadastrar item do cardápio",
            description = "Cria um novo item do cardápio."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Item do cardápio criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ItemCardapioResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado"
            )
    })
    ResponseEntity<ItemCardapioResponse> criarItemCardapio(
            @Valid ItemCardapioRequest request
    );

    @Operation(
            summary = "Atualizar item do cardápio",
            description = "Atualiza os dados de um item do cardápio existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Item do cardápio atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ItemCardapioResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item do cardápio não encontrado"
            )
    })
    ResponseEntity<ItemCardapioResponse> atualizarItemCardapio(
            @Parameter(description = "ID do item do cardápio", example = "1")
            Long id,

            @Valid ItemCardapioRequest request
    );

    @Operation(
            summary = "Excluir item do cardápio",
            description = "Remove um item do cardápio pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Item do cardápio removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item do cardápio não encontrado"
            )
    })
    ResponseEntity<Void> deletarItemCardapio(
            @Parameter(description = "ID do item do cardápio", example = "1")
            Long id
    );
}