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

@RestController
@RequestMapping("/api/v1/item-cardapio")
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
    public ResponseEntity<ItemCardapioResponse> buscarPorId(@PathVariable Long id) {
        ItemCardapio itemCardapio = buscarItemCardapioPorIdUseCase.execute(id);
        ItemCardapioResponse response = mapper.toResponse(itemCardapio);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criarRestaurante(@RequestBody ItemCardapioRequest request) {
        ItemCardapio itemCardapio = mapper.toDomain(request);
        ItemCardapio criado = criarItemCardapioUseCase.execute(itemCardapio);
        ItemCardapioResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
