package br.com.fiap.food.infrastructure.presentation.controller;

import br.com.fiap.food.application.gateways.ItemCardapioGatewaySpec;
import br.com.fiap.food.application.usercases.ItemCardapio.*;
import br.com.fiap.food.infrastructure.presentation.request.ItemCardapioRequest;
import br.com.fiap.food.infrastructure.presentation.response.ItemCardapioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cardapios")
public class ItemCardapioController {

    private static final Logger logger = LoggerFactory.getLogger(ItemCardapioController.class);

    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;
    private final RecuperarPorIdItemCardapioUseCase recuperarPorIdItemCardapioUseCase;
    private final ListarItemCardapioUserCase listarItemCardapioUserCase;
    private final DeletarIntemCardapioUseCase deletarIntemCardapioUseCase;


    public ItemCardapioController(CriarItemCardapioUseCase criarItemCardapioUseCase, AtualizarItemCardapioUseCase atualizarItemCardapioUseCase,
                                  RecuperarPorIdItemCardapioUseCase recuperarPorIdItemCardapioUseCase,
                                  ListarItemCardapioUserCase listarItemCardapioUserCase,
                                  DeletarIntemCardapioUseCase deletarIntemCardapioUseCase) {
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.recuperarPorIdItemCardapioUseCase = recuperarPorIdItemCardapioUseCase;
        this.listarItemCardapioUserCase = listarItemCardapioUserCase;
        this.deletarIntemCardapioUseCase = deletarIntemCardapioUseCase;
    }


    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criar(@RequestBody ItemCardapioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarItemCardapioUseCase.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(recuperarPorIdItemCardapioUseCase.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioResponse>> listar() {
        return ResponseEntity.ok(listarItemCardapioUserCase.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> atualizar(@PathVariable Long id, @RequestBody ItemCardapioRequest request) {
        return ResponseEntity.ok(atualizarItemCardapioUseCase.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarIntemCardapioUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

