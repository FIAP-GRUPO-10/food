package br.com.fiap.food.infrastructure.presentation.controller;

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

    private final ItemCardapioService service;

    public ItemCardapioController(ItemCardapioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criar(@RequestBody ItemCardapioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> atualizar(@PathVariable Long id, @RequestBody ItemCardapioRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

