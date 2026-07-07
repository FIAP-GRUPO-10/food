package br.com.fiap.food.modules.itemcardapio.infrastructure.controller;

import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper.ItemCardapioApiMapper;
import br.com.fiap.food.modules.itemcardapio.application.usecase.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/item-cardapio")
@Tag(name = "ItemCardapio", description = "Operações relacionadas aos itens do cardápio")
public class ItemCardapioController {

    private final ItemCardapioApiMapper mapper;
    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    public ItemCardapioController(ItemCardapioApiMapper mapper,
                                  CriarItemCardapioUseCase criarItemCardapioUseCase,
                                  BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase) {
        this.mapper = mapper;
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item por ID", description = "Retorna um item do cardápio pelo ID")
    public ResponseEntity<ItemCardapioResponse> buscarPorId(@Parameter(description = "ID do item", required = true) @PathVariable Long id) {
        ItemCardapio itemCardapio = buscarItemCardapioPorIdUseCase.execute(id);
        ItemCardapioResponse response = mapper.toResponse(itemCardapio);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Criar item do cardápio", description = "Cria um novo item do cardápio")
    public ResponseEntity<ItemCardapioResponse> criarRestaurante(@RequestBody ItemCardapioRequest request) {
        ItemCardapio itemCardapio = mapper.toDomain(request);
        ItemCardapio criado = criarItemCardapioUseCase.execute(itemCardapio);
        ItemCardapioResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
