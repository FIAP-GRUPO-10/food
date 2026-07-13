package br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.repository;
import br.com.fiap.food.modules.itemcardapio.infrastructure.persistence.entity.ItemCardapioEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.entity.RestauranteEntity;
import br.com.fiap.food.modules.restaurante.infrastructure.persistence.repository.RestauranteRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.entity.UsuarioEntity;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.TipoUsuarioRepository;
import br.com.fiap.food.modules.usuario.infrastructure.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemCardapioRepositoryTest {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    private RestauranteEntity restaurante;

    @BeforeEach
    void setUp() {
        TipoUsuarioEntity tipoUsuario = new TipoUsuarioEntity();
        tipoUsuario.setNome("ADMIN");
        tipoUsuario.setObservacao("Administrador");
        tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Luiz");
        dono.setEmail("luiz@email.com");
        dono.setTipoUsuario(tipoUsuario);
        dono = usuarioRepository.save(dono);

        restaurante = RestauranteEntity.builder()
                .nome("Restaurante Fazenda")
                .endereco("Rua A")
                .tipoCozinha("Italiana")
                .horarioAbertura(LocalTime.of(8, 0))
                .horarioFechamento(LocalTime.of(22, 0))
                .dono(dono)
                .build();

        restaurante = restauranteRepository.save(restaurante);
    }

    @Test
    void deveSalvarItemCardapioAssociadoAoRestaurante() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png"
        );

        ItemCardapioEntity salvo =
                itemCardapioRepository.save(itemCardapio);

        assertNotNull(salvo.getId());
        assertEquals("Pizza Portuguesa", salvo.getNome());
        assertEquals(
                "Pizza com presunto, ovos e cebola",
                salvo.getDescricao()
        );
        assertEquals(49.90, salvo.getPreco(), 0.001);
        assertFalse(salvo.isSomenteNoLocal());
        assertEquals("/img/pizza.png", salvo.getCaminhoFoto());

        assertNotNull(salvo.getRestaurante());
        assertEquals(
                restaurante.getId(),
                salvo.getRestaurante().getId()
        );
    }

    @Test
    void deveBuscarItemCardapioPorId() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Hambúrguer",
                "Hambúrguer artesanal",
                35.90,
                true,
                "/img/hamburguer.png"
        );

        ItemCardapioEntity salvo =
                itemCardapioRepository.save(itemCardapio);

        Optional<ItemCardapioEntity> encontrado =
                itemCardapioRepository.findById(salvo.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Hambúrguer", encontrado.get().getNome());
        assertEquals(
                restaurante.getId(),
                encontrado.get().getRestaurante().getId()
        );
    }

    @Test
    void deveBuscarItemCardapioPorNome() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Pizza Calabresa",
                "Pizza com calabresa e cebola",
                45.90,
                false,
                "/img/calabresa.png"
        );

        itemCardapioRepository.save(itemCardapio);

        Optional<ItemCardapioEntity> encontrado =
                itemCardapioRepository.findByNome("Pizza Calabresa");

        assertTrue(encontrado.isPresent());
        assertEquals("Pizza Calabresa", encontrado.get().getNome());
    }

    @Test
    void deveRetornarVerdadeiroQuandoNomeExistir() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Pizza Margherita",
                "Pizza com tomate e manjericão",
                42.90,
                false,
                "/img/margherita.png"
        );

        itemCardapioRepository.save(itemCardapio);

        boolean existe =
                itemCardapioRepository.existsByNome("Pizza Margherita");

        assertTrue(existe);
    }

    @Test
    void deveRetornarFalsoQuandoNomeNaoExistir() {
        boolean existe =
                itemCardapioRepository.existsByNome("Item inexistente");

        assertFalse(existe);
    }

    @Test
    void deveListarItensCardapio() {
        ItemCardapioEntity item1 = criarItemCardapio(
                "Item 1",
                "Descrição do item 1",
                20.00,
                false,
                "/img/item1.png"
        );

        ItemCardapioEntity item2 = criarItemCardapio(
                "Item 2",
                "Descrição do item 2",
                30.00,
                true,
                "/img/item2.png"
        );

        itemCardapioRepository.saveAll(List.of(item1, item2));

        List<ItemCardapioEntity> itens =
                itemCardapioRepository.findAll();

        assertEquals(2, itens.size());
    }

    @Test
    void deveAtualizarItemCardapio() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Pizza Portuguesa",
                "Descrição original",
                49.90,
                false,
                "/img/pizza.png"
        );

        itemCardapio = itemCardapioRepository.save(itemCardapio);

        itemCardapio.setNome("Pizza Portuguesa Especial");
        itemCardapio.setDescricao("Descrição atualizada");
        itemCardapio.setPreco(59.90);
        itemCardapio.setSomenteNoLocal(true);

        ItemCardapioEntity atualizado =
                itemCardapioRepository.save(itemCardapio);

        assertEquals(itemCardapio.getId(), atualizado.getId());
        assertEquals(
                "Pizza Portuguesa Especial",
                atualizado.getNome()
        );
        assertEquals(
                "Descrição atualizada",
                atualizado.getDescricao()
        );
        assertEquals(59.90, atualizado.getPreco(), 0.001);
        assertTrue(atualizado.isSomenteNoLocal());
    }

    @Test
    void deveDeletarItemCardapio() {
        ItemCardapioEntity itemCardapio = criarItemCardapio(
                "Pizza Portuguesa",
                "Pizza com presunto, ovos e cebola",
                49.90,
                false,
                "/img/pizza.png"
        );

        ItemCardapioEntity salvo =
                itemCardapioRepository.save(itemCardapio);

        itemCardapioRepository.deleteById(salvo.getId());

        assertFalse(
                itemCardapioRepository.findById(salvo.getId()).isPresent()
        );
    }

    private ItemCardapioEntity criarItemCardapio(
            String nome,
            String descricao,
            double preco,
            boolean somenteNoLocal,
            String caminhoFoto
    ) {
        return ItemCardapioEntity.builder()
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .somenteNoLocal(somenteNoLocal)
                .caminhoFoto(caminhoFoto)
                .restaurante(restaurante)
                .build();
    }
}