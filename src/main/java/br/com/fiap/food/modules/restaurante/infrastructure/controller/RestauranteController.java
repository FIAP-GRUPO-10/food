package br.com.fiap.food.modules.restaurante.infrastructure.controller;

import br.com.fiap.food.modules.restaurante.application.usecase.BuscarRestaurantePorIdUseCase;
import br.com.fiap.food.modules.restaurante.application.usecase.CriarRestauranteUseCase;
import br.com.fiap.food.modules.restaurante.domain.entity.Restaurante;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.request.RestauranteRequest;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.dto.response.RestauranteResponse;
import br.com.fiap.food.modules.restaurante.infrastructure.controller.mapper.RestauranteApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurante")
public class RestauranteController {


    private final RestauranteApiMapper mapper;
    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    public RestauranteController(RestauranteApiMapper mapper,
                                 CriarRestauranteUseCase criarRestauranteUseCase,
                                 BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase) {
        this.mapper = mapper;
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = buscarRestaurantePorIdUseCase.execute(id);
        RestauranteResponse response = mapper.toResponse(restaurante);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> criarRestaurante(@RequestBody RestauranteRequest request) {
        Restaurante restaurante = mapper.toDomain(request);
        Restaurante criado = criarRestauranteUseCase.execute(restaurante);
        RestauranteResponse response = mapper.toResponse(criado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
