package br.com.fiap.food.modules.itemcardapio.infrastructure.controller;

import br.com.fiap.food.modules.itemcardapio.application.usecase.AtualizarItemUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.DeletarItemCardapioUseCase;
import br.com.fiap.food.modules.itemcardapio.domain.entity.ItemCardapio;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.docs.ItemCardapioControllerDocs;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.request.ItemCardapioRequest;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.dto.response.ItemCardapioResponse;
import br.com.fiap.food.modules.itemcardapio.infrastructure.controller.mapper.ItemCardapioApiMapper;
import br.com.fiap.food.modules.itemcardapio.application.usecase.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.food.modules.itemcardapio.application.usecase.CriarItemCardapioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item-cardapio")
public class ItemCardapioController implements ItemCardapioControllerDocs {

    private final ItemCardapioApiMapper mapper;
    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;
    private final AtualizarItemUseCase atualizarItemCardapioUseCase;
    private final DeletarItemCardapioUseCase deletarItemCardapioUseCase;

    public ItemCardapioController(
            ItemCardapioApiMapper mapper,
            CriarItemCardapioUseCase criarItemCardapioUseCase,
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase,
            AtualizarItemUseCase atualizarItemCardapioUseCase,
            DeletarItemCardapioUseCase deletarItemCardapioUseCase
    ) {
        this.mapper = mapper;
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.deletarItemCardapioUseCase = deletarItemCardapioUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> buscarPorId(@PathVariable Long id) {
        ItemCardapio itemCardapio = buscarItemCardapioPorIdUseCase.execute(id);
        ItemCardapioResponse response = mapper.toResponse(itemCardapio);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criarItemCardapio(@RequestBody @Valid ItemCardapioRequest request) {
        ItemCardapio itemCardapio = mapper.toDomain(request);
        ItemCardapio criado = criarItemCardapioUseCase.execute(itemCardapio);
        ItemCardapioResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> atualizarItemCardapio(
            @PathVariable Long id,
            @RequestBody @Valid ItemCardapioRequest request
    ) {
        ItemCardapio itemCardapio = mapper.toDomain(request);

        ItemCardapio atualizado =
                atualizarItemCardapioUseCase.execute(id, itemCardapio);

        ItemCardapioResponse response =
                mapper.toResponse(atualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemCardapio(
            @PathVariable Long id
    ) {
        deletarItemCardapioUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
