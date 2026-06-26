package br.com.fiap.food.infrastructure.presentation.controller;

import br.com.fiap.food.infrastructure.gateways.RestauranteGateway;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
public class RestauranteController {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);
    private final RestauranteGateway service;

    public RestauranteController(RestauranteGateway service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> criar(@RequestBody RestauranteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(@PathVariable Long id, @RequestBody RestauranteRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
