package br.com.fiap.food.infrastructure.presentation.controller;

import br.com.fiap.food.infrastructure.gateways.TipoUsuarioGateway;
import br.com.fiap.food.infrastructure.presentation.request.TipoUsuarioRequest;

import br.com.fiap.food.infrastructure.presentation.response.TipoUsuarioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tipos-usuario")
public class TipoUsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(TipoUsuarioController.class);

    private final TipoUsuarioGateway service;

    public TipoUsuarioController(TipoUsuarioGateway service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponse> criar(@RequestBody TipoUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> atualizar(@PathVariable Long id, @RequestBody TipoUsuarioRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}



