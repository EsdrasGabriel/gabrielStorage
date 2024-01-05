package io.github.gabrielfps.api.controller;

import io.github.gabrielfps.api.dto.AtualizacaoStatusPedidoDTO;
import io.github.gabrielfps.api.dto.InformacaoItemPedidoDTO;
import io.github.gabrielfps.api.dto.InformacoesPedidosDTO;
import io.github.gabrielfps.api.dto.PedidoDTO;
import io.github.gabrielfps.domain.entity.ItemPedido;
import io.github.gabrielfps.domain.entity.Pedido;
import io.github.gabrielfps.domain.enums.StatusPedido;
import io.github.gabrielfps.service.impl.PedidoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    private final PedidoServiceImpl service;
    public PedidoController(PedidoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDto){
        Pedido pedido = service.salvar(pedidoDto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidosDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }
    private InformacoesPedidosDTO converter(Pedido pedido) {
        return InformacoesPedidosDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItems()))
                .build();
    }
    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens
                .stream()
                .map(item -> {
                    return InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody @Valid AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
