package br.com.fiap.food.infrastructure.presentation.controller;

import br.com.fiap.food.application.usercases.usuario.*;
import br.com.fiap.food.infrastructure.presentation.request.UsuarioRequest;
import br.com.fiap.food.infrastructure.presentation.response.UsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final CriarUsuarioUserCase criarUsuarioUserCase;
    private  final RecuperarPorIdUsuarioUserCase recuperarPorIdUsuarioUserCase;
    private final AtualizarUsuarioUserCase atualizarUsuarioUserCase;
    private final ListarUsuarioUserCase listarUsuarioUserCase;
    private  final DeletarUsuarioUserCase deletarUsuarioUserCase;

    public UsuarioController(CriarUsuarioUserCase criarUsuarioUserCase, RecuperarPorIdUsuarioUserCase recuperarPorIdUsuarioUserCase, AtualizarUsuarioUserCase atualizarUsuarioUserCase, ListarUsuarioUserCase listarUsuarioUserCase, DeletarUsuarioUserCase deletarUsuarioUserCase) {
        this.criarUsuarioUserCase = criarUsuarioUserCase;
        this.recuperarPorIdUsuarioUserCase = recuperarPorIdUsuarioUserCase;
        this.atualizarUsuarioUserCase = atualizarUsuarioUserCase;
        this.listarUsuarioUserCase = listarUsuarioUserCase;
        this.deletarUsuarioUserCase = deletarUsuarioUserCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarUsuarioUserCase.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(recuperarPorIdUsuarioUserCase.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(listarUsuarioUserCase.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(atualizarUsuarioUserCase.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUserCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

